import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //Sushant T. 09/01/2021 Refactoring
    @BeforeEach
    public void BeforeEach(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);


    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //Sushant T. 08/31/2021 - Positive result

        Restaurant resultRestaurantTest = service.findRestaurantByName("Amelie's cafe");
        assertNotNull(resultRestaurantTest);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //Sushant T. 08/31/2021
        Restaurant resultRestaurantTest = service.findRestaurantByName("Pantry D'or");
        assertNull(resultRestaurantTest);
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {


        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {


        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
//<Part 3: Failing test case> TDD Test Cases for calculating item subtotal Sushant T. 09/01/2021
    //<<<<<<<<<<<<<<<<<<<<SUBTOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>


    // To write some test cases for following requirements.
    // 1. SubTotal to be updated on Selection of Menu Items.
    // 2. SubTotal to be restored on Un-selecting of Menu Items.
    // 1. Initially the subTotal should be zero and every subsequent menu seclection

    @Test
    public void Cart_Initialization_with_zero_subTotal() throws restaurantNotFoundException {
        //<Part 3: Failing test case> Sushant T. 09/01/2021 SubTotal Initializaiton to 0.
        //Arrange
        RestaurantService restaurantService = new RestaurantService();
        Restaurant selectedRestaurantTest = new Restaurant();

        //Act -
        selectedRestaurantTest = restaurantService.findRestaurantByName("Amelie's cafe");

        //Assert
        assertEquals(0, restaurantService.getsubTotalForSelectedRestaurantItems());
    }

    @Test
    public void when_user_selects_a_Singleitem_the_cart_total_should_increment_by_the_item_price_in_the_list() throws restaurantNotFoundException{
        //Part 3: Failing test case> Sushant T. 09/01/2021
        //Arrange

        Item testItem = new Item("Sweet corn soup", 119);

        RestaurantService restaurantService = new RestaurantService();
        Restaurant selectedRestaurant = restaurantService.findRestaurantByName("Amelie's cafe");
        restaurantService.setSubTotalForSelectedRestaurantItems(200);
        //Act
        restaurantService.getSelectedItemPriceTotal(testItem, "ADD");
        //Assert - upon adding the item, the total should increase by 119
        assertEquals(319, restaurantService.getsubTotalForSelectedRestaurantItems());
    }

    @Test
    public void when_user_unchecks_an_item_the_cart_total_should_increment_by_the_item_price_in_the_list() throws restaurantNotFoundException{
        //Part 3: Failing test case> Sushant T. 09/01/2021
        //Arrange

        Item testItem = new Item("Sweet corn soup", 119);

        RestaurantService restaurantService = new RestaurantService();
        Restaurant selectedRestaurant = restaurantService.findRestaurantByName("Amelie's cafe");
        restaurantService.setSubTotalForSelectedRestaurantItems(200);

        //Act
        restaurantService.getSelectedItemPriceTotal(testItem, "REMOVE");
        //Assert - upon unchecking the item (remove action), the cart total should reduce to 81
        assertEquals(81, restaurantService.getsubTotalForSelectedRestaurantItems());
    }

    @Test
    public void when_user_provides_all_selected_item_list_of_the_cart_it_should_calculate_total_amount() throws restaurantNotFoundException{
        //Part 3: Failing test case> Sushant T. 09/01/2021
        //Arrange

        List<Item> selectedItemList = new ArrayList<>();
        selectedItemList.add(new Item("Vegetable lasagne", 269));
        selectedItemList.add(new Item("Sweet corn soup", 119));

        RestaurantService restaurantService = new RestaurantService();
        Restaurant selectedRestaurant = restaurantService.findRestaurantByName("Amelie's cafe");
        //Act
        restaurantService.getSelectedItemPriceTotal(selectedItemList);
        //Assert - upon unchecking the item (remove action), the cart total should reduce to 81
        assertEquals(388, restaurantService.getsubTotalForSelectedRestaurantItems());

    }
    //<<<<<<<<<<<<<<<<<<<<SUBTOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>
}
