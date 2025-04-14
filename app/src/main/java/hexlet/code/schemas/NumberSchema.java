package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addValidation(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Min must be less than max");
        }
        addValidation(value -> value == null || (value >= min && value <= max));
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
}
