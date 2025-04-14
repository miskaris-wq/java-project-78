package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        addValidation(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addValidation(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        if (min > max || min == max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        addValidation(value -> value == null || value >= min && value <= max);
        return this;
    }
}
