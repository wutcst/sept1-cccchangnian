/**
 * 该类Player是“World of Zuul”应用程序的一部分。
 *《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 * 这个类用于设置玩家的名字。
 * 该名称用于祝贺用户完成任务。还用于添加特性功能。
 *
 * @author  林日奋 软件2002 WHUT
 * @version 2.2 2023.01.02
 */

package cn.edu.whut.sept.zuul;

import cn.edu.whut.sept.zuul.exceptions.ItemNotFoundException;
import cn.edu.whut.sept.zuul.exceptions.ItemTooHeavyException;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {

    private String name; //player的姓名
    private Room currentRoom; //player的目前所处房间
    private Room lastRoom; //player的上一个所处房间
    public ArrayList<Room> roomPath; //player所有经过的room路径
    private double loadCapacity; //player目前的背包重量
    private double MAXHOLD = 20; //player目前背包所能容纳的最大重量
    LinkedList<Item> inventory = new LinkedList(); //player目前背包携带的物品


    /**
     * 构造并初始化Player.
     */
    public Player(String name,Room curRoom,double capacity) {
        this.name = name;
        this.currentRoom = curRoom;
        this.loadCapacity = capacity;
        this.lastRoom = this.currentRoom;
        this.roomPath = new ArrayList<Room>();
    }

    /**
     * @return 返回当前玩家的背包重量.
     */
    public double getCapacity(){
        return this.loadCapacity;
    }

    /**
     * @return 返回当前玩家的所处房间.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @return 设置当前玩家的所处房间.
     */
    public void setCurrentRoom(Room curRoom){
        this.currentRoom = curRoom;
    }

    /**
     * @return 返回当前玩家上一次的所处房间.
     */
    public Room getLastRoom(){
        return lastRoom;
    }

    /**
     * 设置当前玩家上一次的所处房间.
     */
    public void setLastRoom(Room lastRoom){
        this.lastRoom = lastRoom;
    }

    /**
     * @return 返回当前玩家的背包最大可容纳重量.
     */
    public double getMAXHOLD(){
        return this.MAXHOLD;
    }

    /**
     * 当前玩家捡起房间内的某物品并放入自己背包.
     */
    public void takeItem(Item item) throws ItemTooHeavyException {
        if (isTakePossible(item)) {
            inventory.add(item);
        } else {
            //throw new ItemTooHeavyException("Too heavy to take");
        }
    }

    /**
     * 判断当前玩家能否捡起某物品
     * @return 可以捡起返回 true,否则返回 false.
     */
    private boolean isTakePossible(Item item) {
        return calculateWeight() + item.getWeight() <= this.MAXHOLD;
    }

    /**
     * 计算当前玩家背包重量
     * @return 返回当前玩家的背包重量.
     */
    private double calculateWeight() {
        double inventoryWeight = 0;
        if (inventory.size() != 0) {
            for (int i = 0; i <= inventory.size()-1; i++) {
                inventoryWeight += inventory.get(i).getWeight();
            }
        }
        return inventoryWeight;
    }

    /**
     * 丢弃当前玩家背包中的某物品
     * @return 返回被玩家丢弃的物品.
     */
    public Item dropItem(String name) throws ItemNotFoundException {
        Item dropItem = null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                dropItem = inventory.get(i);
                inventory.remove(i);
            } else if (dropItem == null){
                throw new ItemNotFoundException("You don't own this item!");
            }
        }

        return dropItem;
    }

    /**
     * 将物品放入背包后增加重量
     */
    public void increaseCapacity(double num){
        this.loadCapacity = this.loadCapacity + num;
    }

    /**
     * 将物品从背包丢弃后减少重量
     */
    public void decreaseCapacity(double num){
        this.loadCapacity = this.loadCapacity - num;
    }

    /**
     * 打印输出当前玩家背包中的物品信息及总重量
     */
    public void printItemsInBags() {
        System.out.println( "Items in player's bag : ");
        double totalWeight = 0;
        for (int i = 0; i < inventory.size(); i++){
            totalWeight += inventory.get(i).getWeight();
            System.out.println(inventory.get(i).getName() + ", " + inventory.get(i).getDescription() + ", " + Double.toString(inventory.get(i).getWeight()) + "kg");
        }
        System.out.println("Player's bag has the total weight of " + totalWeight + "kg");
    }
}

