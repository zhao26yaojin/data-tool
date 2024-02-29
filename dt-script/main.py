# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

import re
from team import Team
from game import Game
from standing import Standing


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

    # string = "apple, banana, cherry"
    # pattern = r"\w+"
    # reult = re.finditer(pattern, string)
    #
    # for match in reult:
    #     print(match.group())

    # team = Team()
    # team.crawl()

    # game = Game()
    # game.crawler()

    standing = Standing()
    standing.crawl()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
