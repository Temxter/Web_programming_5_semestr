package Model;

import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable {
    private String name;
    private double weight;
    private String type;
    private String consist;
    public enum CompareByField{
        name, weight, type
    }

    static private CompareByField compareBy;


    public Item(String name, double weight, String type, String consist) {
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.consist = consist;
        compareBy = CompareByField.weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public String getConsist() {
        return consist;
    }


    public static void setCompareBy(CompareByField compareBy) {
        if (compareBy == null)
            return;
        Item.compareBy = compareBy;
    }

    @Override
    public int compareTo(Item o) {
        if (compareBy == CompareByField.weight){
            return (int) (this.weight - o.weight);
        }
        if (compareBy == CompareByField.name){
            return this.name.compareTo(o.name);
        }
        else
            return this.type.compareTo(o.type);
    }

    @Override
    public String toString() {
        return "Type: " + this.type + "\n" +
                "Name: " + this.name + "\n" +
                "Weigth: " + this.weight + "\n" +
                "Consist: " + this.consist + "\n";

    }
}
