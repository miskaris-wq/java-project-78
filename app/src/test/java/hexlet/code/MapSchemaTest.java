package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class MapSchemaTest {
    private Validator validator;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    void testWithoutConstraints() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeof() {
        schema.sizeof(2);

        Map<String, Object> data = new HashMap<>();
        assertFalse(schema.isValid(data));

        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    void testNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> schema.sizeof(-1));
    }

    @Test
    void testShapeValidation() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        assertFalse(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("firstName", "John");
        human4.put("lastName", null);
        assertFalse(schema.isValid(human4));
    }

    @Test
    void testShapeWithDifferentValueTypes() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());

        schema.shape(schemas);

        Map<String, Object> person1 = new HashMap<>();
        person1.put("name", "Alice");
        person1.put("age", 25);
        assertTrue(schema.isValid(person1));

        Map<String, Object> person2 = new HashMap<>();
        person2.put("name", "Bob");
        person2.put("age", -10);
        assertFalse(schema.isValid(person2));

        Map<String, Object> person3 = new HashMap<>();
        person3.put("age", 30);
        assertFalse(schema.isValid(person3));
    }

    @Test
    void testCombinedConstraints() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("username", validator.string().required().contains("admin"));
        schemas.put("password", validator.string().required().minLength(8));

        schema.required().shape(schemas);

        Map<String, Object> user1 = new HashMap<>();
        user1.put("username", "admin_user");
        user1.put("password", "securepassword123");
        assertTrue(schema.isValid(user1));

        Map<String, Object> user2 = new HashMap<>();
        user2.put("username", "admin");
        user2.put("password", "short");
        assertFalse(schema.isValid(user2));

        Map<String, Object> user3 = new HashMap<>();
        user3.put("username", "regular_user");
        user3.put("password", "longenoughpassword");
        assertFalse(schema.isValid(user3));
    }
}
