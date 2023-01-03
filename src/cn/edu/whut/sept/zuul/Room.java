/**
 * 该类 Room是“World of Zuul”应用程序的一部分。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * “房间”代表游戏场景中的一个地点。它通过出口与其他房间相连。
 * 出口被标上了北，东，南，西。对于每个方向，房间存储一个参考到隔壁房间，如果那个方向没有出口则为空。
 *
 * @author  林日奋 软件2002 WHUT
 * @version 2.2 2023.01.02
 */

package cn.edu.whut.sept.zuul;

import cn.edu.whut.sept.zuul.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;

    private HashMap <String, Item> items;

    /**
     * 创建一个描述为 “description” 的房间。最初，它没有出口。
     * “description”类似于"outside the main entrance of the university"或"in a lecture theater"。
     * @param description 房间的描述.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<String, Item>();
    }

    /**
     * 确定这个房间的出口，每个方向都有可能.
     * @ param north 北出口.
     * @ param east 东出口.
     * @ param south 南出口.
     * @ param west 西出口.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return 返回这个房间的短描述.
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * @return 返回这个房间的长描述.
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }


    /**
     * @return 返回这个房间的出口描述.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * @return 返回这个房间的出口对应的房间.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }


    /**
     * 将某件物品Item放进该房间.
     */
    public void putItem(Item item) {
        items.put(item.getName(), new Item(item.getName(),item.getDescription(), item.getWeight()));
    }

    /**
     * 从房间中移除某物品（被捡起等等）。
     * @return 返回从这个房间移除的room.
     */
    public Item removeItem(String name) throws ItemNotFoundException {
        if (items.containsKey(name)) {
            Item returnItem = items.get(name);
            items.remove(name);
            return returnItem;
        } else {
            throw new ItemNotFoundException("This item does not exist!");
        }
    }

    /**
     * @return 返回look命令所需的字符串描述.
     */
    public String lookDescription() {
        StringBuilder itemsToString = new StringBuilder("");
        for (Item value : items.values()) {
            itemsToString.append("- " + value.getName() + ", " + value.getDescription() + ", " + value.getWeight() + "kg"
                    + "\n");
        }

        return "You are " + description + ".\n" + getExitString() + "\n"
                + "Items in this room: \n" + itemsToString;
    }


    /**
     * 打印当前所处房间的所有物品及物品总重量.
     */
    public void showItemsInCurrentRoom(){
        StringBuilder itemsToString = new StringBuilder("");
        double totalWeight = 0;
        for (Item value : items.values()) {
            totalWeight = totalWeight + value.getWeight();
            itemsToString.append("- " + value.getName() + ", " + value.getDescription() + ", " + value.getWeight() + "kg");
        }
        String printString = "Items in this room: \n" + itemsToString;
        System.out.println(printString);
        System.out.println("This room has the total weight of " + totalWeight + "kg");
        System.out.println();
    }
}


