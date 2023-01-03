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

    public double getCapacity(){
        return this.loadCapacity;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room curRoom){
        this.currentRoom = curRoom;
    }

    public Room getLastRoom(){
        return lastRoom;
    }
    public void setLastRoom(Room lastRoom){
        this.lastRoom = lastRoom;
    }

    public double getMAXHOLD(){
        return this.MAXHOLD;
    }
    public void goTo(Room newRoom) {
        currentRoom = newRoom;
    }

    public void takeItem(Item item) throws ItemTooHeavyException {
        if (isTakePossible(item)) {
            inventory.add(item);
        } else {
            //throw new ItemTooHeavyException("Too heavy to take");
        }
    }

    private boolean isTakePossible(Item item) {
        return calculateWeight() + item.getWeight() <= this.MAXHOLD;
    }

    private double calculateWeight() {
        double inventoryWeight = 0;
        if (inventory.size() != 0) {
            for (int i = 0; i <= inventory.size()-1; i++) {
                inventoryWeight += inventory.get(i).getWeight();
            }
        }
        return inventoryWeight;
    }

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

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void increaseCapacity(double num){
        this.loadCapacity = this.loadCapacity + num;
    }
    public void decreaseCapacity(double num){
        this.loadCapacity = this.loadCapacity - num;
    }

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

