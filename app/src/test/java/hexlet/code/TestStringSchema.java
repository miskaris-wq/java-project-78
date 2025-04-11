package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestStringSchema {
    private Validator v;
    private StringSchema schema;

    @BeforeEach
    public void setUp() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public void testRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("abc"));
    }

    @Test
    public void testMinLength() {
        schema.minLength(3);
        assertFalse(schema.isValid("ab"));
        assertTrue(schema.isValid("abc"));
        assertTrue(schema.isValid("abcd"));
    }

    @Test
    public void testContains() {
        schema.contains("hex");
        assertFalse(schema.isValid("exlet"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testMinLengthValidation() {
        assertThrows(IllegalArgumentException.class, () -> schema.minLength(-1));
    }

    @Test
    void testContainsValidation() {
        assertThrows(IllegalArgumentException.class, () -> schema.contains(null));
    }
}
