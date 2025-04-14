package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private Integer minLengthValue;
    private String containsValue;

    @Override
    public StringSchema required() {
        super.required();
        addValidation(value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimal length must be positive or zero");
        }
        this.minLengthValue = min;
        addValidation(value -> value.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring can't be null");
        }
        this.containsValue = substring;
        addValidation(value -> value.contains(substring));
        return this;
    }
}
