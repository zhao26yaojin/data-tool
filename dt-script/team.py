import requests
import pymysql
from bs4 import BeautifulSoup

from constant import HEADERS


class Team:
    def crawl(self):
        serialA = "https://www.espn.com/soccer/teams/_/league/ITA.1/italian-serie-a"
        self.crawlerLeague(serialA, 9)

        premierLeague = "https://www.espn.com/soccer/teams/_/league/ENG.1/english-premier-league"
        self.crawlerLeague(premierLeague, 2)

        laliga = "https://www.espn.com/soccer/teams/_/league/ESP.1/spanish-laliga"
        self.crawlerLeague(laliga, 5)

        bundesliga = "https://www.espn.com/soccer/teams/_/league/GER.1/german-bundesliga"
        self.crawlerLeague(bundesliga, 8)

        ligue1 = "https://www.espn.com/soccer/teams/_/league/FRA.1/french-ligue-1"
        self.crawlerLeague(ligue1, 4)

    def crawlerLeague(self, url, country):
        response = requests.get(url, headers=HEADERS)
        print(response.ok)
        print(response.encoding)

        content = response.text

        soup = BeautifulSoup(content, "html.parser")

        names = []
        h2s = soup.find_all("h2", attrs={"class", "di clr-gray-01 h5"})

        for h2 in h2s:
            names.append(h2.string)
            print(h2)

        print(names)

        self.db_handle(names, country)

    def db_handle(self, names, country):
        nameStr = "\",\"".join(names)
        # 创建数据库连接
        conn = pymysql.connect(host='192.168.86.128', port=3306, user='root', password='12345678', database='pedia',
                               charset='utf8')
        # 获取游标对象
        cursor = conn.cursor()

        existNames = []

        # SQL语句
        sql = "select * from fb_team where name in (\"{}\");".format(nameStr)
        # sql = "select * from tb_country"
        print(sql)
        # 执行SQL语句，返回结果是SQL语句影响的行数
        row_count = cursor.execute(sql)
        print(row_count)
        # 取出结果的一行数据
        # print(cursor.fetchone())
        # 获取指定条数的数据
        # for t in cursor.fetchmany(2):
        #     print(t)
        # 获取所有数据
        for line in cursor.fetchall():
            existNames.append(line[1])

        print(existNames)

        addSql = ""

        for name in names:
            if name not in existNames:
                addSql += "(null, \"{}\", {}),".format(name, country)

        # 对数据库执行增删改查的时候，默认会在事务环境中进行操作，操作完成后要进行手动提交
        # 如果不提交，程序默认操作为回滚，即更改的操作不被记录，事务提交操作由数据库连接对象来完成
        if len(addSql) > 0:
            addSql = "insert into fb_team values {}".format(addSql[0:-1])

            print("addSql: " + addSql)

            # 执行SQL语句，返回结果是SQL语句影响的行数
            row_count = cursor.execute(addSql)
            print(row_count)

            conn.commit()

        # 关闭游标
        cursor.close()
        # 关闭连接
        conn.close()
