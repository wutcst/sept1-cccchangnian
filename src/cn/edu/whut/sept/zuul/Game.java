/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  林日奋 软件2002 WHUT
 * @version 1.1 2023.01.02
 */
package cn.edu.whut.sept.zuul;

import cn.edu.whut.sept.zuul.exceptions.ItemNotFoundException;
import cn.edu.whut.sept.zuul.exceptions.ItemTooHeavyException;

import java.util.ArrayList;
import java.util.Random;

public class Game
{
    private Parser parser;
    //private Room currentRoom;

    private Player player;
    Room outside, theater, pub, lab, office;

    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game()
    {
        createRooms();
        createItems();
        randomCreateCookie();
        parser = new Parser();
        player = new Player("player_1",outside,0);
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */
    private void createRooms()
    {
        //Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);


        //currentRoom = outside;  // start game outside

    }

    /**
     *  游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * 执行用户输入的游戏指令.
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        //需要修改的部分代码
         /* if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }*/

        //将多if条件判断改为switch语句
        switch (commandWord){
            case "help": printHelp(); break;
            case "go": goRoom(command); break;
            case "quit": wantToQuit = quit(command); break;
            case "look": look();break;
            case "back": back();break;
            case "backStep": backStep();break;
            case "items": printItems();break;
            case "eatCookie":
                try {
                    //randomCreateCookie();
                    eatCookie(command);
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(player.getCurrentRoom().lookDescription());
                break;
            case "take":
                try {
                    takeItem(command);
                } catch (ItemTooHeavyException | ItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(player.getCurrentRoom().lookDescription());
                break;
            case "drop":
                try {
                    dropItem(command);
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(player.getCurrentRoom().lookDescription());
                break;
        }

        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setLastRoom(player.getCurrentRoom());
            player.roomPath.add(player.getCurrentRoom());//添加到访问路径中
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            //判断是否触发随机传送
            if (player.getCurrentRoom() == lab){
                send();
            }
        }
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }


    private void createItems(){
        Item item1 = new Item("item_1","This is item_1",10.0);
        Item item2 = new Item("item_2","This is item_2",20.0);
        Item item3 = new Item("item_3","This is item_3",30.0);
        Item item4 = new Item("item_4","This is item_4",40.0);
        Item item5 = new Item("item_5","This is item_5",50.0);
        Item item6 = new Item("item_6","This is item_6",60.0);
        Item item7 = new Item("item_7","This is item_7",70.0);
        Item item8 = new Item("item_8","This is item_8",80.0);
        Item item9 = new Item("item_9","This is item_9",90.0);
        Item item10 = new Item("item_10","This is item_10",100.0);

        outside.putItem(item1);
        outside.putItem(item2);
        theater.putItem(item3);
        theater.putItem(item4);
        pub.putItem(item5);
        pub.putItem(item6);
        lab.putItem(item7);
        lab.putItem(item8);
        office.putItem(item9);
        office.putItem(item10);
    }


    /**
     * 执行look指令，查看目前所处房间内的物品信息。
     */
    private void look() {
        System.out.println(player.getCurrentRoom().lookDescription());
    }

    /**
     * 执行back指令，从当前房间返回上一个所处房间。
     */
    private void back(){
        Room nextRoom = player.getLastRoom();
        player.setLastRoom(player.getCurrentRoom());
        player.roomPath.add(player.getCurrentRoom());//添加到访问路径中
        player.setCurrentRoom(nextRoom);
        System.out.println(player.getCurrentRoom().getLongDescription());
        //判断是否触发随机传送
        if (player.getCurrentRoom() == lab){
            send();
        }
    }


    /**
     * 执行take指令，从当前房间捡起物品并放入玩家背包。
     */
    private void takeItem(Command command) throws ItemNotFoundException, ItemTooHeavyException {
        String itemFromCommand = command.getSecondWord();
        Item newItem = player.getCurrentRoom().removeItem(itemFromCommand);
        if ((newItem != null) && !(player.getCapacity() + newItem.getWeight() > player.getMAXHOLD())) {
            player.takeItem(newItem);
            player.increaseCapacity(newItem.getWeight());
        } else {
            System.out.println("takeItem Error: item not exist or too heavey");
            player.getCurrentRoom().putItem(newItem);
        }
    }

    /**
     * 执行drop指令，从当玩家背包丢弃物品到当前房间内。
     */
    private void dropItem(Command command) throws ItemNotFoundException {
        String itemFromCommand = command.getSecondWord();
        Item newItem = null;
        newItem = player.dropItem(itemFromCommand);
        if (newItem != null) {
            player.getCurrentRoom().putItem(newItem);
            player.decreaseCapacity(newItem.getWeight());
        } else {
            System.out.println("dropItem Error: item not exist");
        }
    }

    /**
     * 执行items指令，打印显示当前房间内和玩家背包中所有物件及总重量。
     */
    private void printItems(){
        //打印当前房间内所有的物件及总重量
        player.getCurrentRoom().showItemsInCurrentRoom();
        //打印玩家随身携带的所有物件及总重量
        player.printItemsInBags();
    }

    /**
     * 在某些房间中随机增加cookie物件
     */
    private void randomCreateCookie(){
        Item item11 = new Item("cookie_1","This is a cookie named cookie_1",5.0);
        Item item12 = new Item("cookie_2","This is a cookie named cookie_2",5.0);
        Random random = new Random();
        int randNum1 = random.nextInt(5);
        int randNum2 = random.nextInt(5);
        //System.out.println(randNum1);
        //System.out.println(randNum2);
        Room[] places = {outside,theater,pub,lab,office};
        places[randNum1].putItem(item11);
        places[randNum2].putItem(item12);
    }

    /**
     * 执行eatCookie指令，将所在房间中的cookie吃掉并增加背包容量。
     */
    private void eatCookie(Command command)throws ItemNotFoundException{
        String itemFromCommand = command.getSecondWord();
        Item newItem = player.getCurrentRoom().removeItem(itemFromCommand);
        if(newItem.getName().contains("cookie")) {
            player.increaseCapacity(20);
            System.out.println("You have ate the cookie, your capacity increases 10 kg.");
        }
    }

    //这个方法还有问题
    private void backStep(){
        for(int i=player.roomPath.size()-1; i>=0; i--){
            Room nextroom = player.roomPath.get(i);
            player.setLastRoom(player.getCurrentRoom());
            player.setCurrentRoom(nextroom);
            if (player.roomPath.get(i).getShortDescription().contains("outside")){
                System.out.println(player.getCurrentRoom().getLongDescription());
                player.roomPath.clear();
                break;
            }
            else{
                System.out.println(player.getCurrentRoom().getLongDescription());
                //break;
            }
        }
    }

    /**
     * 实现房间随机传输功能。
     */
    private void send(){
        Random random = new Random();
        int randNum = random.nextInt(3);
        Room[] sendToPlaces = {theater,pub,office};
        Room nextRoom = sendToPlaces[randNum];
        String roomString = new String();
        if(randNum == 0){
            roomString = "theater";
        }
        else if (randNum == 1){
            roomString = "pub";
        }
        else {
            roomString = "office";
        }
        player.setLastRoom(player.getCurrentRoom());
        player.setCurrentRoom(nextRoom);
        player.roomPath.add(player.getCurrentRoom());//添加到访问路径中
        System.out.println("You have been randomly sent to " + roomString);
    }
}