package sample;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ShoppingCartAddItems {

    Faker faker = new Faker();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ShoppingCart cart;
    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }
    @Test()
    public void shouldThrowIllegalArgumentExceptionTooShortName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal title");
        cart.addItem("", 5.0, 2, Item.Type.SECOND);
    }
    @Test()
    public void shouldThrowIllegalArgumentExceptionTooLongName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal title");
        cart.addItem(faker.lorem().fixedString(33), 5.0, 2, Item.Type.SECOND);
    }
    @Test()
    public void shouldAddNewItemToCardWithCorrectTitle() {
        cart.addItem(faker.lorem().fixedString(15), 5.0, 2, Item.Type.SECOND);
    }
    @Test()
    public void shouldThrowIllegalArgumentExceptionTooLowPrice() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal price");
        cart.addItem(faker.lorem().fixedString(15), -10, 2, Item.Type.SECOND);
    }
    @Test()
    public void shouldThrowIllegalArgumentExceptionTooHighPrice() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal price");
        cart.addItem(faker.lorem().fixedString(15), 150000, 2, Item.Type.SECOND);
    }
    @Test()
    public void shouldAddNewItemToCardWithCorrectPrice() {
        cart.addItem(faker.lorem().fixedString(15), 5.0, 2, Item.Type.SECOND);
    }
    @Test()
    public void shouldThrowIllegalArgumentExceptionTooLowItemQuantity() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal quantity");
        cart.addItem(faker.lorem().fixedString(15), 20, 0, Item.Type.SECOND);
    }

    @Test()
    public void shouldThrowIllegalArgumentExceptionTooHighItemQuantity () {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal quantity");
        cart.addItem(faker.lorem().fixedString(15), 20, 1001, Item.Type.SECOND);
    }
    @Test()
    public void shouldAddNewItemToCardWithCorrectItemsQuantity () {
        cart.addItem(faker.lorem().fixedString(15), 5.0, 5, Item.Type.SECOND);
    }
    @Test()
    public void shouldThrowIndexOutOfBoundsExceptionCartIsCrowded () {
        int cartMaxSize = 100;
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("No more space in cart");
        for(int i = 0; i < cartMaxSize; i++) {
            cart.addItem(faker.lorem().fixedString(15), 20, 10, Item.Type.SECOND);
        }
    }
    @Test()
    public void shouldAddLargeCountOfItemsToCard () {
        int correctCartSize = 80;
        for(int i = 0; i < correctCartSize; i++) {
            cart.addItem(faker.lorem().fixedString(15), 20, 10, Item.Type.SECOND);
        }
    }
}
