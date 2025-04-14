package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addValidation(value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Objects.requireNonNull(min);
        Objects.requireNonNull(max);

        if (min > max) {
            throw new IllegalArgumentException("Min must be less than max");
        }

        addValidation(value -> value >= min && value <= max);
        return this;
    }
}