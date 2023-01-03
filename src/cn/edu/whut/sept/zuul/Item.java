/**
 * 该类Item 物品-冒险游戏中的物品.
 *
 * 这个类用于处理游戏中使用的所有物品。
 * 主要声明并处理物品的相关逻辑
 *
 * @author  林日奋 软件2002 WHUT
 * @version 2.2 2023.01.02
 */

package cn.edu.whut.sept.zuul;

public class Item {
    private String name;
    private String description;
    private double weight;

    /**
     * 构造并初始化Item.
     */
    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * 返回Item的名字.
     */
    public String getName() {
        return name;
    }

    /**
     * 返回Item的描述.
     */
    public String getDescription() {
        return description;
    }


    /**
     * 返回Item的重量.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * 返回Item的整体介绍字符串.
     */
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }
}
