package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.najah.code.Calculator;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Calculator Tests")
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {
    
    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("Setup complete");
    }

    @Test
    @Order(1)
    @DisplayName("Addition Test")
    void testAddition() {
        assertEquals(10, calc.add(2, 3, 5));
    }

    @Test
    @Order(2)
    @DisplayName("Divide by Zero Test")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource({"5,120", "0,1", "-1,IllegalArgumentException"})
    @DisplayName("Factorial Test with Parameters")
    void testFactorial(int input, String expected) {
        if (expected.equals("IllegalArgumentException")) {
            assertThrows(IllegalArgumentException.class, () -> calc.factorial(input));
        } else {
            assertEquals(Integer.parseInt(expected), calc.factorial(input));
        }
    }

    @Test
    @Order(4)
    @DisplayName("Timeout Test")
    void testTimeout() {
        assertTimeoutPreemptively(java.time.Duration.ofMillis(100), () -> calc.factorial(10));
    }

    @Test
    @Order(5)
    @Disabled("Bug: Fix division logic")
    @DisplayName("Disabled Test Example")
    void disabledTest() {
        assertEquals(0, calc.divide(1, 1)); // Incorrect test, should be 1
    }

    @Test
    @Order(6)
    @DisplayName("Addition with No Numbers Test")
    void testAdditionWithNoNumbers() {
        assertEquals(0, calc.add());
    }
    
    @Test
    @Order(7)
    @DisplayName("Addition with Negative Numbers Test")
    void testAdditionWithNegativeNumbers() {
        assertEquals(0, calc.add(-2, 3, -1));
    }
    
    @Test
    @Order(8)
    @DisplayName("Division with Positive Numbers Test")
    void testDivisionWithPositiveNumbers() {
        assertEquals(2, calc.divide(10, 5));
    }
    
    @Test
    @Order(9)
    @DisplayName("Division with Negative Dividend Test")
    void testDivisionWithNegativeDividend() {
        assertEquals(-2, calc.divide(-10, 5));
    }
    
    @Test
    @Order(10)
    @DisplayName("Division with Negative Divisor Test")
    void testDivisionWithNegativeDivisor() {
        assertEquals(-2, calc.divide(10, -5));
    }
    
    @AfterEach
    void tearDown() {
        System.out.println("Test complete");
    }
}
