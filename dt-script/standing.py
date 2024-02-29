import re

import requests
import pymysql
from bs4 import BeautifulSoup

from constant import HEADERS, EVENT_BASE_URL


class Standing:
    def crawl(self):
        serialA = "{}/ita.1".format(EVENT_BASE_URL)
        self.crawlerStanding(serialA, 9, 108)

        premierLeague = "{}/eng.1".format(EVENT_BASE_URL)
        self.crawlerStanding(premierLeague, 2, 100)

        laliga = "{}/esp.1".format(EVENT_BASE_URL)
        self.crawlerStanding(laliga, 5, 102)

        bundesliga = "{}/ger.1".format(EVENT_BASE_URL)
        self.crawlerStanding(bundesliga, 8, 104)

        ligue1 = "{}/fra.1".format(EVENT_BASE_URL)
        self.crawlerStanding(ligue1, 4, 110)

    def crawlerStanding(self, url, country, event_id):
        response = requests.get(url, headers=HEADERS)
        print(response.ok)
        print(response.encoding)

        content = response.text

        soup = BeautifulSoup(content, "html.parser")

        standings = []

        names = soup.find_all("span", attrs={"class", "hide-mobile"})
        for name in names:
            standing = [name.string]
            standings.append(standing)
            print(name)

        print(standings)

        index = 0

        trs = soup.find_all("tr", attrs={"class", re.compile("Table__TR Table__TR--sm Table__even")})
        for tr in trs:
            div = tr.find("div", attrs={"class", "team-link flex items-center clr-gray-03"})
            if div is not None:
                continue

            standing = standings[index]
            index += 1
            tds = tr.find_all("td")
            for td in tds:
                standing.append(td.string)

        print(standings)

        self.db_handle(standings, country, event_id)

    def db_handle(self, standings, country, event_id):
        # 创建数据库连接
        conn = pymysql.connect(host='192.168.86.128', port=3306, user='root', password='12345678', database='pedia',
                               charset='utf8')
        # 获取游标对象
        cursor = conn.cursor()

        existNames = {}

        # SQL语句
        sql = "select * from fb_team where country_id = {};".format(country)

        print(sql)
        # 执行SQL语句，返回结果是SQL语句影响的行数
        cursor.execute(sql)

        # 获取所有数据
        for line in cursor.fetchall():
            existNames[line[1]] = line[0]

        print("existNames: ", existNames)

        existStandings = {}

        existSql = "select * from fb_standings where event_id = {};".format(event_id)

        cursor.execute(existSql)
        for line in cursor.fetchall():
            existStandings[line[4]] = line

        addSql = "insert into fb_standings (`team_id`,`event_id`,`country_id`,`team_name`,`game_play`,`win`,`draw`,`loss`,`goals_for`,`goals_against`,`goals_differential`,`points`) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
        updateSql = "update fb_standings set `game_play`=%s, `win`=%s, `draw`=%s, `loss`=%s, `goals_for`=%s, `goals_against`=%s, `goals_differential`=%s, `points`=%s where team_name=%s"

        addTuple = []
        updateTuple = []

        for standing in standings:
            if standing[0] not in existNames:
                raise NameError("球队名错误")

            teamId = existNames[standing[0]]

            if standing[0] not in existStandings:
                addTuple.append((teamId, event_id, country, *standing))
            else:
                updateTuple.append((*standing[1:], standing[0]))

        print(addTuple)
        print(updateTuple)

        # 对数据库执行增删改查的时候，默认会在事务环境中进行操作，操作完成后要进行手动提交
        # 如果不提交，程序默认操作为回滚，即更改的操作不被记录，事务提交操作由数据库连接对象来完成
        if len(addTuple) > 0:
            # 执行SQL语句，返回结果是SQL语句影响的行数
            row_count = cursor.executemany(addSql, addTuple)
            print("insert: ", row_count)

            conn.commit()
        elif len(updateTuple) > 0:
            row_count = cursor.executemany(updateSql, updateTuple)
            print("update: ", row_count)

            conn.commit()

        # 关闭游标
        cursor.close()
        # 关闭连接
        conn.close()
