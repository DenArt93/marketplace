package ru.inno.market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.market.model.Category;
import ru.inno.market.model.Client;
import ru.inno.market.model.Item;
import ru.inno.market.model.Order;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;

    @BeforeEach
    void setUp(){
        Client client = new Client(1, "Denis");
        order = new Order(1, client);
    }
    @Test
    void addItem(){
        Item item = new Item(2, "Xiaomi POCO M5s", Category.SMARTPHONES, 19490);
        order.addItem(item);

        assertTrue(order.getItems().containsKey(item));
        assertEquals(1,order.getItems().get(item));
    }


    @Test
    void applyDiscount(){
        Item item = new Item(2, "Xiaomi POCO M5s", Category.SMARTPHONES, 19490);
        order.addItem(item);

        double discount = 0.2;
        double totalPriceBefore = order.getTotalPrice();
        order.applyDiscount(discount);
        double totalPriceAfter = order.getTotalPrice();

        assertEquals(totalPriceBefore * (1 - discount),totalPriceAfter);
    }


    @Test
    void getTotalPrice(){
        assertEquals(0,order.getTotalPrice());
    }


    @Test
    void isDiscountApplied(){
        assertFalse(order.isDiscountApplied());
    }
}
