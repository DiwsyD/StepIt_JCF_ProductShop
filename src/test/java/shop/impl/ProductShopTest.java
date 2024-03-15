package shop.impl;

import org.itstep.jcf.shop.impl.ProductShop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.itstep.jcf.shop.Shop;
import org.itstep.jcf.shop.exception.CheckoutException;
import org.itstep.jcf.shop.util.ShopFiller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductShopTest {

    private final Shop shop = new ProductShop();
    private static List<String> productNames;
    private static final String EMPTY_CART_MESSAGE = "Cart is Empty";

    @BeforeAll
    public static void setUp() {
        productNames = ShopFiller.getProductsFromFile("products");
    }

    @Test
    void shouldReturnNotEmptyProductsList() {
        //given
        List<String> products = shop.products();

        //when
        String string = products.toString();

        //then
        assertFalse(products.isEmpty());
        productNames.forEach(pName -> assertTrue(string.contains(pName)));
    }

    @Test
    void shouldShowCart() {
        //given
        int addProductCount = 5;
        addProductsToCart(addProductCount);

        //when
        String shopCart = shop.showCart();

        //then
        assertNotNull(shopCart);
        assertFalse(shopCart.isBlank());
    }

    @Test
    void shouldAddProductsToCart() {
        //given
        int addProductCount = 5;
        List<String> addedProducts = addProductsToCart(addProductCount);

        //when
        String shopCart = shop.showCart();

        //then
        assertNotNull(shopCart);
        assertFalse(shopCart.isBlank());
        verifyShopCart(addedProducts, shopCart);
    }

    @Test
    void shouldAddProductsWithAmountToCart() {
        //given
        int addProductCount = 5;
        Map<String, Integer> addedProducts = addProductsToCartWithAmount(addProductCount);

        //when
        String shopCart = shop.showCart();

        //then
        assertNotNull(shopCart);
        assertFalse(shopCart.isBlank());
        verifyShopCart(addedProducts, shopCart);

    }

    @Test
    void shouldRemoveProductsFromCart() {
        //given
        int addProductCount = 5;
        int removeProductCount = 2;
        List<String> addedProducts = addProductsToCart(addProductCount);
        removeProductsFromCart(addedProducts, removeProductCount);

        //when
        String shopCart = shop.showCart();

        //then
        assertNotNull(shopCart);
        assertFalse(shopCart.isBlank());
        verifyShopCart(addedProducts, shopCart);
    }

    @Test
    void shouldRemoveProductsWithAmountFromCart() {
        //given
        int addProductCount = 5;
        int removeProductCount = 2;
        Map<String, Integer> addedProducts = addProductsToCartWithAmount(addProductCount);
        removeProductsFromCartWithAmount(addedProducts, removeProductCount);

        //when
        String shopCart = shop.showCart();

        //then
        assertNotNull(shopCart);
        assertFalse(shopCart.isBlank());
        verifyShopCart(addedProducts, shopCart);
    }

    @Test
    void shouldCheckout() {
        //given
        int addProductCount = 5;
        addProductsToCart(addProductCount);

        //when
        double checkout = shop.checkout();

        //then
        assertTrue(checkout > 0);
    }

    @Test
    void shouldPayAndClearCart() throws CheckoutException {
        //given
        int addProductCount = 5;
        addProductsToCart(addProductCount);
        double checkout = shop.checkout();

        //when
        shop.pay(checkout);

        //then
        assertEquals(EMPTY_CART_MESSAGE, shop.showCart());
    }

    @Test
    void shouldNotPayButThrowAnException() throws CheckoutException {
        //given
        int addProductCount = 5;
        addProductsToCart(addProductCount);

        //when
        assertThrows(CheckoutException.class, () -> {
            shop.pay(-5);
        });
    }

    @Test
    void shouldNotPay() throws CheckoutException {
        //given
        int addProductCount = 5;
        addProductsToCart(addProductCount);

        //when
        shop.pay(0);

        //then
        assertFalse(shop.showCart().isBlank());
    }

    @Test
    void shouldClearCart() {
        //given
        int addProductCount = 2;
        addProductsToCart(addProductCount);

        //when
        shop.clearCart();

        //then
        String shopCart = shop.showCart();
        assertEquals(EMPTY_CART_MESSAGE, shopCart);
    }

    private List<String> addProductsToCart(int amount) {
        List<String> addedProducts = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            String product = productNames.get(i);
            addedProducts.add(product);
            shop.addToCart(product);
        }
        return addedProducts;
    }

    private Map<String, Integer> addProductsToCartWithAmount(int addProductCount) {
        Map<String, Integer> addedProducts = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < addProductCount; i++) {
            String product = productNames.get(i);
            int productCount = random.nextInt(9) + 1;
            addedProducts.put(product, productCount);
            shop.addToCart(product, productCount);
        }
        return addedProducts;
    }

    private void removeProductsFromCart(List<String> addedProducts, int removeProductCount) {
        for (int i = 0; i < removeProductCount; i++) {
            String product = addedProducts.get(i);
            addedProducts.remove(product);
            shop.removeFromCart(product);
        }
    }

    private void removeProductsFromCartWithAmount(Map<String, Integer> addedProducts, int removeProductCount) {
        Random random = new Random();
        for (Map.Entry<String, Integer> product : addedProducts.entrySet()) {
            int amountToDelete = random.nextInt(product.getValue());
            shop.removeFromCart(product.getKey(), amountToDelete);
            addedProducts.put(product.getKey(), product.getValue() - amountToDelete);
        }
    }

    private void verifyShopCart(Map<String, Integer> addedProducts, String shopCart) {
        List<String> list = new ArrayList<>(Arrays.stream(
                shopCart.replace("[", "").replace("]", "").trim().split(",| (,%s)")
        ).map(String::trim).toList());
        list.sort(Comparator.comparing(p -> p.charAt(1) + p.charAt(0)));
        List<String> productList = new ArrayList<>(addedProducts.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .toList());
        productList.sort(Comparator.comparing(p -> p.charAt(1) + p.charAt(0)));
        assertEquals(productList, list);
    }

    private void verifyShopCart(List<String> addedProducts, String shopCart) {
        List<String> list = new ArrayList<>(Arrays.stream(
                shopCart.replaceAll("[^a-zA-Z]+", " ").trim().split("\\s+")
        ).map(String::trim).toList());
        list.sort(Comparator.comparing(p -> p.charAt(1) + p.charAt(0)));
        addedProducts.sort(Comparator.comparing(p -> p.charAt(1) + p.charAt(0)));
        assertEquals(addedProducts, list);
    }
}
