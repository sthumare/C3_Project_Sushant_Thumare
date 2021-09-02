import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
        //Part 3 Solution: 09/02/2021 : Initilization of variable Selected restaurant and SubTotals
      private Restaurant selectedRestaurant = new Restaurant();
      private int subTotalForSelectedRestaurantItems;
    public Restaurant findRestaurantByName(String restaurantName){
        //return null;
        //Part 2 Solution: Sushant T. 08/31/2021 Searching through restaurant list and return if found.
        for (Restaurant restaurant : restaurants) {

            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }

        }
        return null;
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    //<Part 3: Solution> - Sushant T. This method used for Dynamic Updates to SubTotal on selection and de Selection.
    public int getSelectedItemPriceTotal(List<Item> selectedItems){
        int itemTotalPrice = 0;
        for (Item selectedItem: selectedItems) {
            itemTotalPrice += selectedItem.getPrice();
        }
        this.subTotalForSelectedRestaurantItems = itemTotalPrice;
        return this.subTotalForSelectedRestaurantItems;
    }

    //<Part 3: Solution> - Sushant T this method can be used to dynamically update subTotal
    // ACTION = ADD : Checkbox is checked by user otherwise Unchecked
    public int getSelectedItemPriceTotal(Item selectedItems, String action){
        if (action == "ADD"){
            this.subTotalForSelectedRestaurantItems += selectedItems.getPrice();//Check Box is checked adds the price
        } else{
            this.subTotalForSelectedRestaurantItems -= selectedItems.getPrice();//Check Box is unchecked subtracts the price
        }
        return this.subTotalForSelectedRestaurantItems;
    }
    public int getsubTotalForSelectedRestaurantItems(){
        return subTotalForSelectedRestaurantItems;
    }

    public void setSubTotalForSelectedRestaurantItems(int subTotalForSelectedRestaurantItems) {
        this.subTotalForSelectedRestaurantItems = subTotalForSelectedRestaurantItems;
    }

    public Restaurant getSelectedRestaurant(){
        return selectedRestaurant;
    }
}



