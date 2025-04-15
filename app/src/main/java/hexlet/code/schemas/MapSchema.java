package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private Integer requiredSize = null;
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        validations.add(this::validateShape);
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
        validations.add(value -> value.size() == size);
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

/*
package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer requiredSize = null;
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
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
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (!super.checkRequired(value)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (requiredSize != null && value.size() != requiredSize) {
            return false;
        }

        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();
            Object fieldValue = value.get(key);

            if (!value.containsKey(key) || !isValidField(schema, fieldValue)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private <T> boolean isValidField(BaseSchema<T> schema, Object value) {
        try {
            return schema.isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }
}

 */
