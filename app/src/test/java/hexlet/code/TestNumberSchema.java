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
        assertTrue(schema.isValid(3));
        assertTrue(schema.isValid(6));
        schema.range(1, 4);
        assertTrue(schema.isValid(3));
        assertFalse(schema.isValid(6));

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
