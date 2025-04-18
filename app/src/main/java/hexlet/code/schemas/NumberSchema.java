package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        validations.put("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        validations.put("rangeMin", value -> value >= min);
        validations.put("rangeMax", value -> value <= max);


        return this;
    }

}

