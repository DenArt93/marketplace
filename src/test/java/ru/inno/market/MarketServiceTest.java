package ru.inno.market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.market.core.Catalog;
import ru.inno.market.core.MarketService;
import ru.inno.market.model.Client;
import ru.inno.market.model.Item;
import ru.inno.market.model.Order;
import ru.inno.market.model.PromoCodes;

import static org.junit.jupiter.api.Assertions.*;

public class MarketServiceTest {
    private MarketService marketService;
    private Catalog catalog;

    @BeforeEach
    void setUp(){
        marketService = new MarketService();
        catalog = new Catalog();

    }

    @Test
    void createOrderFor(){
        Client client = new Client(1, "Denis");
        int orderId = marketService.createOrderFor(client);
        assertNotNull(marketService.getOrderInfo(orderId));

    }


    @Test
    void addItemToOrder(){
        Client client = new Client(1, "Denis");
        int orderId = marketService.createOrderFor(client);
        Item item = catalog.getItemById(1);

        marketService.addItemToOrder(item,orderId);
        Order order = marketService.getOrderInfo(orderId);

        assertTrue(order.getItems().containsKey(item));
        assertEquals(1,order.getItems().get(item));

    }


    @Test
    void applyDiscountForOrder(){
        Client client = new Client(1, "Denis");
        int orderId = marketService.createOrderFor(client);
        Item item = catalog.getItemById(1);

        marketService.addItemToOrder(item,orderId);
        double totalPriceBifore = marketService.getOrderInfo(orderId).getTotalPrice();
        double discount = 0.2;
        marketService.applyDiscountForOrder(orderId, PromoCodes.FIRST_ORDER);
        double totalPriceAfter = marketService.getOrderInfo(orderId).getTotalPrice();
        assertEquals(totalPriceBifore * (1 - discount),totalPriceAfter);
    }


    @Test
    void getOrderInfo(){
        Client client = new Client(1, "Denis");
        int orderId = marketService.createOrderFor(client);
        Order order = marketService.getOrderInfo(orderId);

        assertEquals(client,order.getClient());
        assertEquals(orderId,order.getId());

    }


}
