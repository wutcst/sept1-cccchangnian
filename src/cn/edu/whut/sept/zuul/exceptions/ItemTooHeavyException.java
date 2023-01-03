package cn.edu.whut.sept.zuul.exceptions;

public class ItemTooHeavyException extends Exception {

    /**
     * 构造函数
     * 目前无动作
     */
    public ItemTooHeavyException() {

    }

    /**
     * 构造函数
     */
    public ItemTooHeavyException(String s) {
        super(s);
    }
}
