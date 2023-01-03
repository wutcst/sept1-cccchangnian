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


    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }
}
