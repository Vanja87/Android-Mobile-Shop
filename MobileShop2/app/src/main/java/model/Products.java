package model;

/**
 * Created by PC on 7/17/2017.
 */

public class Products {

    private int id;
    private static String category;
    private String name;
    private String description;
    private int price;
    private String used;


    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

