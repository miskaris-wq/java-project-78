package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer requiredSize = null;

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

        return requiredSize == null || value.size() == requiredSize;
    }
}
