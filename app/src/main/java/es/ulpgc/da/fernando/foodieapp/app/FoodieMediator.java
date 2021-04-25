package es.ulpgc.da.fernando.foodieapp.app;

public class FoodieMediator {

    private static FoodieMediator INSTANCE;

    private State mState;

    private FoodieMediator() {
        mState = new State();
    }

    public static void resetInstance() {
        INSTANCE = null;
    }


    public static FoodieMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FoodieMediator();
        }

        return INSTANCE;
    }

    public State getState() {
        return mState;
    }

    public NextToState getNextScreenState() {
        return null;
    }

    public void setNextScreenState(ToNextState state) {

    }

    public void setPreviousScreenState(ToPreviousState state) {

    }

    public PreviousToState getPreviousScreenState() {
        return null;
    }
}
