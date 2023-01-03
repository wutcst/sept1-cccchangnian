package cn.edu.whut.sept.zuul.test;

import cn.edu.whut.sept.zuul.Command;
import cn.edu.whut.sept.zuul.Game;
import cn.edu.whut.sept.zuul.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class testGame {

    /**
     * 测试Game类的back方法.
     */
    @Test
    public void backTest(){
        Game game = new Game();
        Command back = new Command("back",null);
        game.processCommand(back);
    }

    /**
     * 测试Game类的look方法.
     */
    @Test
    public void lookTest(){
        Game game = new Game();
        Command look = new Command("look",null);
        game.processCommand(look);
    }

    /**
     * 测试Game类的backStep方法.
     */
    @Test
    public void backStepTest(){
        Game game = new Game();
        Command backStep = new Command("backStep",null);
        game.processCommand(backStep);
    }

    /**
     * 测试Game类的back方法.
     */
    @Test
    public void takeTest(){
        Game game = new Game();
        Command take = new Command("take","item_1");
        game.processCommand(take);
    }

    /**
     * 测试Game类的drop方法.
     */
    @Test
    public void dropTest(){
        Game game = new Game();
        Command take = new Command("take","item_1");
        game.processCommand(take);
        Command drop = new Command("drop","item_1");
        game.processCommand(drop);
    }

    /**
     * 测试Game类的eatCookie方法.
     */
    @Test
    public void eatCookieTest(){
        Game game = new Game();
        Command eatCookie = new Command("eat","cookie_1");
        game.processCommand(eatCookie);
    }
}
