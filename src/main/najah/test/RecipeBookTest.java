package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.*;

@DisplayName("RecipeBook Tests")
public class RecipeBookTest {

    private RecipeBook book;
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        book = new RecipeBook();
        recipe = new Recipe();
        recipe.setName("Espresso");
    }

    @Test
    @DisplayName("Add Recipe Test")
    void testAddRecipe() {
        assertTrue(book.addRecipe(recipe));
    }

    @Test
    @DisplayName("Delete Recipe Test")
    void testDeleteRecipe() {
        book.addRecipe(recipe);
        assertEquals("Espresso", book.deleteRecipe(0));
    }
    
    @Test
    @DisplayName("Edit Recipe Test")
    void testEditRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Americano");
        book.addRecipe(recipe);

        assertEquals("Espresso", book.editRecipe(0, newRecipe)); // Should return old recipe name
    }

    @Test
    @DisplayName("Add Duplicate Recipe Test")
    void testAddDuplicateRecipe() {
        Recipe duplicateRecipe = new Recipe();
        duplicateRecipe.setName("Espresso");

        book.addRecipe(recipe);
        assertFalse(book.addRecipe(duplicateRecipe)); // Should not add the duplicate recipe
    }

    @Test
    @DisplayName("Delete Non-Existent Recipe Test")
    void testDeleteNonExistentRecipe() {
        assertNull(book.deleteRecipe(0)); // Should return null, no recipe exists at index 0
    }

    @Test
    @DisplayName("Edit Non-Existent Recipe Test")
    void testEditNonExistentRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Flat White");

        assertNull(book.editRecipe(0, newRecipe)); // Should return null, no recipe exists at index 0
    }

    @Test
    @DisplayName("Edit with Invalid Recipe Test")
    void testEditWithInvalidRecipe() {
        Recipe emptyRecipe = new Recipe(); // Name is empty

        book.addRecipe(recipe);
        assertEquals("Espresso", book.editRecipe(0, emptyRecipe)); // Should still return the old recipe name
    }

    @Test
    @DisplayName("Get Recipes Test")
    void testGetRecipes() {
        // Initially, the array should contain all null values
        Recipe[] recipes = book.getRecipes();
        assertNotNull(recipes);  // Ensure the array is not null
        assertEquals(4, recipes.length);  // Ensure the array has 4 slots as defined by NUM_RECIPES
        
        // Check if all slots are null initially
        for (Recipe recipe : recipes) {
            assertNull(recipe);  // All slots should be null initially
        }
    }

    @Test
    @DisplayName("Add Recipe to First Empty Spot")
    void testAddRecipeToFirstEmptySpot() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Latte");

        // Add the recipe and check if it was added
        assertTrue(book.addRecipe(newRecipe));

        // Verify that the first slot is not null and contains the correct recipe
        assertNotNull(book.getRecipes()[0]);
        assertEquals("Latte", book.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Add Multiple Recipes")
    void testAddMultipleRecipes() {
        // Add first recipe
        Recipe recipe1 = new Recipe();
        recipe1.setName("Espresso");
        assertTrue(book.addRecipe(recipe1));

        // Add second recipe
        Recipe recipe2 = new Recipe();
        recipe2.setName("Cappuccino");
        assertTrue(book.addRecipe(recipe2));

        // Verify that both recipes are added in the first two slots
        assertNotNull(book.getRecipes()[0]);
        assertEquals("Espresso", book.getRecipes()[0].getName());
        assertNotNull(book.getRecipes()[1]);
        assertEquals("Cappuccino", book.getRecipes()[1].getName());
    }

}
