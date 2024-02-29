import requests
from bs4 import BeautifulSoup

from constant import HEADERS


class Game:

    def crawler(self):
        url = "https://www.espn.com/soccer/teams"

        response = requests.get(url, headers=HEADERS)
        print(response.ok)
        print(response.encoding)

        content = response.text

        soup = BeautifulSoup(content, "html.parser")

        h2s = soup.find_all("option", attrs={"class", "dropdown__option"})

        for h2 in h2s:
            print(h2.string)

