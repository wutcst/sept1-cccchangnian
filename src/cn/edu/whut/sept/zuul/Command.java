/**
 * 该类Command是“World of Zuul”应用程序的一部分。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 该类保存关于用户发出的命令的信息。
 *
 * 目前版本1.0的命令 由两个字符串组成:命令字和第二个字符串
 * (例如，如果命令是“go east”，那么两个字符串显然是“go”和“east”)。
 *
 * 使用的方式是:命令已经被检查是否是有效命令字。
 * 如果用户输入了一个无效的命令(unknown)则命令字为<null>。
 * 如果命令只有一个单词，那么第二个单词是<null>
 * @author  林日奋 软件2002 WHUT
 * @version 1.1 2023.01.02
 */


package cn.edu.whut.sept.zuul;

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * 创建命令对象。第一个和第二个词必须提供，但是其中一个(或两个)都可以为空。
     * @param firstWord 命令的第一个词。如果命令为 Null 则没有被认出来。
     * @param secondWord 命令的第二个词.
     */
    public Command(String firstWord, String secondWord) {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * 返回命令的控制词（两个词中的第一个）
     * 如果命令未被识别，则返回结果为 NULL
     * @return 命令的控制词（两个词中的第一个）.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * @return 命令的第二个词
     * 如果命令没有第二个词，则返回结果为 NULL
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * @return 如果命令被正确识别了，返回 true,否则返回 false
     */
    public boolean isUnknown() {
        return (commandWord == null);
    }

    /**
     * @return 如果命令有第二个词，则返回 true，否则返回 false.
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
