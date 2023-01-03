package cn.edu.whut.sept.zuul.test;

import cn.edu.whut.sept.zuul.Command;
import cn.edu.whut.sept.zuul.Game;
import cn.edu.whut.sept.zuul.Item;
import cn.edu.whut.sept.zuul.Room;
import cn.edu.whut.sept.zuul.exceptions.ItemNotFoundException;
import org.junit.Test;

public class testRoom {
    /**
     * 测试 Room类的putItem方法.
     */
    @Test
    public void testPutItem(){
        Room room = new Room("outside the main entrance of the university");
        Item item = new Item("item_1","This is item_1",10.0);
        room.putItem(item);
    }

    /**
     * 测试 Room类的removeItem方法.
     */
    @Test
    public void testRemoveItem() throws ItemNotFoundException {
        Game game = new Game();
        Command take = new Command("take","item_1");
        //game.processCommand(take);
        String itemFromCommand = take.getSecondWord();
        Item newItem = game.getPlayer().getCurrentRoom().removeItem(itemFromCommand);
    }

    /**
     * 测试 Room类的lookDescription方法.
     */
    @Test
    public void testLookDescription(){
        Room room = new Room("outside the main entrance of the university");
        room.lookDescription();
    }

    /**
     * 测试 Room类的showItemsInCurrentRoom方法.
     */
    @Test
    public void testShowItemsInCurrentRoom(){
        Room room = new Room("outside the main entrance of the university");
        room.showItemsInCurrentRoom();
    }
}
