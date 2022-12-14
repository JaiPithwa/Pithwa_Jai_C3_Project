import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.spi.ResolveResult;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void beforeEach() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        LocalTime shouldReturnOpen = LocalTime.parse("12:40:00");
        Restaurant spyRest = Mockito.spy(restaurant);
        Mockito.when(spyRest.getCurrentTime()).thenReturn(shouldReturnOpen);

        assertTrue(spyRest.isRestaurantOpen());

        //WRITE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        LocalTime shouldReturnFalse = LocalTime.parse("00:40:00");
        Restaurant spyRest = Mockito.spy(restaurant);
        Mockito.when(spyRest.getCurrentTime()).thenReturn(shouldReturnFalse);

        assertTrue(spyRest.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>FINDING ORDER VALUE/COST<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void order_cost_should_be_588_if_sizzlingBrownie_plus_vegetableLasagne_is_ordered () {

        List<String> selectedItems = Arrays.asList("Sweet corn soup", "Vegetable lasagne");
        int totalCost = restaurant.getTotalCost(selectedItems);
        assertEquals(388, totalCost);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<FINDING ORDER VALUE/COST>>>>>>>>>>>>>>>>>>>>>>>>>>>

}