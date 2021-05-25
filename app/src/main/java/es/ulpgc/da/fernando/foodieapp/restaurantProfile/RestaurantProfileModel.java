package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantProfileModel implements RestaurantProfileContract.Model {

    public static String TAG = RestaurantProfileModel.class.getSimpleName();

    private final RepositoryContract repository;

    public RestaurantProfileModel(RepositoryContract repository) {
        //iniciali repo
        this.repository = repository;
    }

}