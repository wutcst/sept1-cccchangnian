package cn.edu.whut.sept.zuul.exceptions;

public class ItemNotFoundException extends Exception {

    /**
     * 构造函数
     * 目前无动作
     */
    public ItemNotFoundException() {

    }

    /**
     * 构造函数
     */
    public ItemNotFoundException(String s) {
        super(s);
    }
}
