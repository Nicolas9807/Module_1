package sample;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ShoppingCartCalculateDiscount {
    private Item item;
    private int expectedDiscount;
    private static Faker faker = new Faker();

    public ShoppingCartCalculateDiscount(Item item, int expectedDiscount) {
        this.item = item;
        this.expectedDiscount = expectedDiscount;
    }
    @Parameterized.Parameters
    public static Collection items() {
        return Arrays.asList(new Object[][] {
                { new Item(faker.lorem().characters(), 20.0, 5, Item.Type.REGULAR), 0 },
                { new Item(faker.lorem().characters(), 20.0, 10, Item.Type.DISCOUNT), 20 },
                { new Item(faker.lorem().characters(), 20.0, 5, Item.Type.SECOND), 50 },
                { new Item(faker.lorem().characters(), 20.0, 5, Item.Type.SALE),  80 },
        });
    }
    @Test
    public void shouldCalculateCorrectDiscount() {
        int receivedDiscount = ShoppingCart.calculateDiscount(this.item);
        assertEquals(this.expectedDiscount, receivedDiscount);
    }
}
