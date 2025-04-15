package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestNumberSchema {
    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    public void setUp() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public void testRequired() {
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(3));
    }

    @Test
    public void testPositive() {
        assertTrue(schema.isValid(-3));
        assertTrue(schema.isValid(3));
        assertTrue(schema.isValid(0));
        schema.positive();
        assertFalse(schema.isValid(-3));
        assertTrue(schema.isValid(3));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testRange() {
        schema.range(1, 4);

        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(4));

        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(3));

        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(5));

    }

    @Test
    public void testCombinedValidations() {
        schema.required()
                .positive()
                .range(10, 100);

        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(50));
        assertTrue(schema.isValid(100));

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(9));
        assertFalse(schema.isValid(101));

        var schema1 = v.number()
                .required()
                .range(2, 4)
                .range(10, 12);
        assertTrue(schema1.isValid(11));
    }

    @Test
    void testRangeValidation() {
        assertThrows(IllegalArgumentException.class, () -> schema.range(5, 3));
    }

    @Test
    void testZeroWithPositive() {
        schema.positive();
        assertFalse(schema.isValid(0));
    }
}
