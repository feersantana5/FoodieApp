package es.ulpgc.da.fernando.foodieapp.models;

public class Restaurant {

    // variables que representan el titulo, la locaclizacion, la pagina web e id de la imagen del restaurante
    private String title;
    private String location;
    private String webpage;
    private final int imageResource;

    //TODO: hacer los menus
    public Restaurant(String title, String location, String webpage, int imageResource) {
        this.title = title;
        this.location = location;
        this.webpage = webpage;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public int getImageResource() {
        return imageResource;
    }

}
