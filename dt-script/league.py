
from crawlerBase import CrawlBase


class League(CrawlBase):
    def __init__(self):
        self.country = "country"

    def handle(self):
        print("aa" + self.country)
