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
        this.minLengthValue = min;
        // Удаляем предыдущие проверки minLength
        addValidation(value -> value.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsValue = substring;
        // Удаляем предыдущие проверки contains
        addValidation(value -> value.contains(substring));
        return this;
    }
}
