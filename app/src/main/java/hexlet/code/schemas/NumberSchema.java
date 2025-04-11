package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private Integer minRange;
    private Integer maxRange;
    private boolean mustBePositive;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.mustBePositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (!super.checkRequired(value)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (mustBePositive && value <= 0) {
            return false;
        }

        if (minRange != null && value < minRange) {
            return false;
        }

        if (maxRange != null && value > maxRange) {
            return false;
        }

        return true;
    }
}
