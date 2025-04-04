package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.UserService;

@DisplayName("UserService Tests")
public class UserServiceSimpleTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    @DisplayName("Valid Email Test")
    void testValidEmail() {
        assertTrue(service.isValidEmail("test@example.com"));
    }

    @Test
    @DisplayName("Invalid Email Test")
    void testInvalidEmail() {
        assertFalse(service.isValidEmail("invalidemail"));
    }

    @Test
    @DisplayName("Authentication Test")
    void testAuthentication() {
        assertTrue(service.authenticate("admin", "1234"));
        assertFalse(service.authenticate("user", "wrongpass"));
    }
    
    @Test
    @DisplayName("Empty Email Test")
    void testEmptyEmail() {
        assertFalse(service.isValidEmail("")); // Should return false for an empty email
    }

    @Test
    @DisplayName("Email without @ Test")
    void testEmailWithoutAtSymbol() {
        assertFalse(service.isValidEmail("testexample.com")); // Invalid email (missing '@')
    }

    @Test
    @DisplayName("Email without . Test")
    void testEmailWithoutDot() {
        assertFalse(service.isValidEmail("test@com")); // Invalid email (missing '.')
    }

    @Test
    @DisplayName("Null Email Test")
    void testNullEmail() {
        assertFalse(service.isValidEmail(null)); // Should return false for a null email
    }

    @Test
    @DisplayName("Edge Case Authentication Test")
    void testEdgeCaseAuthentication() {
        assertFalse(service.authenticate("", "")); // Empty username and password should fail
        assertFalse(service.authenticate(null, null)); // Null username and password should fail
    }
    
    @Test
    @DisplayName("Case-Sensitive Password Test")
    void testCaseSensitivePassword() {
        assertFalse(service.authenticate("admin", "1234abcd")); // Password is case-sensitive
        assertTrue(service.authenticate("admin", "1234"));      // Correct username and password
    }

    @Test
    @DisplayName("Special Characters in Username Test")
    void testSpecialCharactersInUsername() {
        assertFalse(service.authenticate("admin@site.com", "1234")); // Invalid username with special characters
        assertTrue(service.authenticate("admin", "1234"));           // Valid username and password
    }

    @Test
    @DisplayName("Empty Username Test")
    void testEmptyUsername() {
        assertFalse(service.authenticate("", "1234")); // Empty username should fail
    }

    @Test
    @DisplayName("Special Characters in Password Test")
    void testSpecialCharactersInPassword() {
        assertFalse(service.authenticate("admin", "1234!@#")); // Password with special characters
        assertTrue(service.authenticate("admin", "1234"));     // Correct username and password
    }


}
