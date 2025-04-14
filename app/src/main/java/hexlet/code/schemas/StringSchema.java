package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

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
        addValidation(value -> value == null || value.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null || substring.isEmpty()) {
            throw new IllegalArgumentException("Substring can't be null");
        }
        addValidation(value -> value == null || value.contains(substring));
        return this;
    }
}
