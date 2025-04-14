package hexlet.code.schemas;

import java.util.List;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private Integer currentMinLength;
    private String currentContains;

    @Override
    public StringSchema required() {
        super.required();
        addValidation(value -> !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimal length must be positive or zero");
        }
        this.currentMinLength = min;
        replaceValidation(value -> value.length() >= min, "minLength");
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring can't be null");
        }
        this.currentContains = substring;
        replaceValidation(value -> value.contains(substring), "contains");
        return this;
    }

    private void replaceValidation(Predicate<String> validation, String type) {
        // Удаляем предыдущие проверки того же типа
        getValidations().removeIf(p -> p.toString().contains(type));
        addValidation(validation);
    }

    // Добавляем метод для доступа к validations
    private List<Predicate<String>> getValidations() {
        // Приводим тип, так как знаем, что это StringSchema
        @SuppressWarnings("unchecked")
        List<Predicate<String>> result = (List<Predicate<String>>) (List<?>) super.validations;
        return result;
    }
}
