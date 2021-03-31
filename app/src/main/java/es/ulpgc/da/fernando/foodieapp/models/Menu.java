package es.ulpgc.da.fernando.foodieapp.models;

public class Menu {

    private String name;
    private String starter;
    private String beverage;
    private String firstCourse;
    private String secondCourse;
    private String dessert;
    private int price;
    private final int imageResource;

    public Menu(String name, int imageResource, int price, String starter, String beverage, String firstCourse, String secondCourse, String dessert) {
        this.name = name;
        this.starter = starter;
        this.beverage = beverage;
        this.firstCourse = firstCourse;
        this.secondCourse = secondCourse;
        this.dessert = dessert;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    public String getFirstCourse() {
        return firstCourse;
    }

    public void setFirstCourse(String firstCourse) {
        this.firstCourse = firstCourse;
    }

    public String getSecondCourse() {
        return secondCourse;
    }

    public void setSecondCourse(String secondCourse) {
        this.secondCourse = secondCourse;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageResource() {
        return imageResource;
    }

}
