/**
 * 该类 CommandWords是“World of Zuul”应用程序的一部分。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 这个类包含游戏中已知的所有命令字的枚举。
 *
 * 主要是用来识别输入的命令
 * 目前版本1.0下的字符串数组validCommands包含go,quit,help三条命令
 *
 * @author  林日奋 软件2002 WHUT
 * @version 1.1 2023.01.02
 */


package cn.edu.whut.sept.zuul;

public class CommandWords
{
    // 保存所有有效命令字的常量数组
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "take", "drop","back","items","eatCookie","backStep"
    };

    /**
     * 构造函数，初始化该类
     * 目前无动作
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * 检查给定的字符串是否是有效的命令字.
     * @return 如果命令是合法的，返回 true，否则返回 false
     * 遍历匹配有效的字符数组 validCommands 即可
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // 此处代表未匹配到任何有效字符
        return false;
    }

    /**
     * 打印所有的有效命令.
     * 遍历并打印有效的字符数组 validCommands 即可
     */
    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
