/**
 * 该类main是“World of Zuul”应用程序的一部分。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 这是项目启动类，从此处运行即可。
 *
 * @author  林日奋 软件2002 WHUT
 * @version 2.2 2023.01.02
 */

package cn.edu.whut.sept.zuul;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
