package es.ulpgc.da.fernando.foodieapp.restaurantsList;

public class RestaurantsListModel implements RestaurantsListContract.Model {

    public static String TAG = RestaurantsListModel.class.getSimpleName();

    private String data;

    public RestaurantsListModel() { }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return data;
    }

    @Override
    public void onRestartScreen(String data) {
        // Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        // Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
    }
}