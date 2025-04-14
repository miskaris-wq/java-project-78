package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private Integer requiredSize = null;
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        addValidation(this::validateShape);
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        this.requiredSize = size;
        addValidation(value -> value.size() == size);
        return this;
    }

    private boolean validateShape(Map<String, T> value) {
        return shapeSchemas.entrySet().stream()
                .allMatch(entry -> isValidField(entry.getValue(), value.get(entry.getKey())));
    }

    private boolean isValidField(BaseSchema<?> schema, Object value) {
        if (value == null) {
            return false;
        }
        try {
            return validateWithSchema(schema, value);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> boolean validateWithSchema(BaseSchema<T> schema, Object value) {
        return schema.isValid((T) value);
    }
}
