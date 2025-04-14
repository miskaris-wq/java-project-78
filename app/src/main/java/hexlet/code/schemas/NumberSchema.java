package hexlet.code.schemas;

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
        if (min > max || min == max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        addValidation(value -> value >= min && value <= max);
        return this;
    }
}
