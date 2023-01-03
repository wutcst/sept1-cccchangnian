/**
 * 该类Parser是“World of Zuul”应用程序的一部分。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 该解析器读取用户输入，并试图将其解释为“冒险命令”。
 * 每次调用它时，它都会从终端读取一行，并尝试将该行解释为两个字的命令。
 * 将命令作为command类的对象返回。
 *
 * 解析器有一组已知的命令字。
 * 它根据已知命令检查用户输入，如果输入不是已知命令之一，则返回标记为未知命令的命令对象。
 *
 * @author  林日奋 软件2002 WHUT
 * @version 2.2 2023.01.02
 */

package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;
    private Scanner reader;

    /**
     * 创建一个从终端窗口读取的解析器.
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 对输入的命令进行分词解析。得到一个或两个词
     * @return 将命令作为command类的对象返回.
     */
    public Command getCommand()
    {
        String inputLine; // 会包含整行字符串
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next(); //获取第一个词
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); //获取第二个词
            }
        }

        // 检查一下这个单词是否合法。
        // 如果是，就用它创建一个命令。如果不是，则创建一个“null”命令(用于未知命令)。
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * 对所有的合法命令进行展示
     * 主要是调用Command类的 showAll
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
