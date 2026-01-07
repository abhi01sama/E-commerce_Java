package tests;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.*;

public class HomeTest extends BaseTest {

    @Test
    public void SearchProduct() {
        String productName = "Gaming V16";

        HomePage homepage = new HomePage(driver);
        homepage.searchProduct(productName);

        ProductPage productPage = homepage.selectProduct(productName);
        String price = productPage.getProductPrice();
        System.out.println("Extracted Price: ₹" + price);

        CartPage cartPage = productPage.addToCart();
        cartPage.goToCart();

        String priceInCart = cartPage.getCartPrice();
        System.out.println("Price in Cart: ₹" + priceInCart);

        if (price.equals(priceInCart)) {
            System.out.println("✅ Prices match: ₹" + price);
        } else {
            System.out.println("❌ Prices do not match!");
            System.out.println("Search Result Price: ₹" + price);
            System.out.println("Cart Price: ₹" + priceInCart);
        }
    }
}
