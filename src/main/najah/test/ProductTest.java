package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import main.najah.code.Product;

@DisplayName("Product Tests")
public class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Laptop", 1000);
    }

    @Test
    @DisplayName("Price Validation Test")
    void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Phone", -500));
    }

    @ParameterizedTest
    @DisplayName("Discount Test")
    @ValueSource(doubles = {10.0, 20.0, 50.0})
    void testValidDiscount(double discount) {
        product.applyDiscount(discount);
        assertEquals(discount, product.getDiscount());
    }

    @Test
    @DisplayName("Invalid Discount Test")
    void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60));
    }
    
    @Test
    @DisplayName("Final Price Without Discount Test")
    void testFinalPriceWithoutDiscount() {
        assertEquals(1000, product.getFinalPrice(), "Final price should be equal to the original price when no discount is applied");
    }
    
    @Test
    @DisplayName("Final Price With Discount Test")
    void testFinalPriceWithDiscount() {
        product.applyDiscount(20);
        assertEquals(800, product.getFinalPrice(), "Final price should be correctly calculated with the discount");
    }



    @Test
    @DisplayName("Zero Discount Test")
    void testZeroDiscount() {
        product.applyDiscount(0);
        assertEquals(1000, product.getFinalPrice(), "Final price should be equal to the original price with 0% discount");
    }

    @Test
    @DisplayName("Maximum Discount Test")
    void testMaximumDiscount() {
        product.applyDiscount(50);
        assertEquals(500, product.getFinalPrice(), "Final price should be half of the original price with a 50% discount");
    }

    
    @Test
    @DisplayName("Product Name Test")
    void testProductName() {
        assertEquals("Laptop", product.getName(), "Product name should be initialized correctly");
    }

    @Test
    @DisplayName("Very Small Price Test")
    void testVerySmallPrice() {
        Product productSmallPrice = new Product("Cheap Item", 0.01); 
        assertEquals(0.01, productSmallPrice.getPrice(), "Price should handle very small values");
    }
    
    @Test
    @DisplayName("Very Large Price Test")
    void testVeryLargePrice() {
        Product productLargePrice = new Product("Luxury Item", 1e6); 
        assertEquals(1e6, productLargePrice.getPrice(), "Price should handle very large values");
    }
}
