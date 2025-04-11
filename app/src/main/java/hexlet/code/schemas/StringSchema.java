package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;
    private String requiredSubstring;

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema minLength(int min) {
        if (min <= 0) {
            throw new IllegalArgumentException("Minimal length must be positive or zero");
        }
        this.minLength = min;
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring can't be null");
        }
        this.requiredSubstring = substring;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (!checkRequired(value)) {
            return false;
        }

        if (value == null || value.isEmpty()) {
            return true;
        }

        if (value.length() < minLength) {
            return false;
        }

        return requiredSubstring == null || value.contains(requiredSubstring);
    }
}
