package main.najah.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.*;

@Suite
@SelectClasses({CalculatorTest.class, ProductTest.class, UserServiceSimpleTest.class, RecipeBookTest.class})
@DisplayName("Complete Test Suite")
public class TestSuite {}
