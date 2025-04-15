package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;

public final class StringSchema extends BaseSchema<String> {
    private final Set<Validation<String>> lengthValidations = new HashSet<>();
    private final Set<Validation<String>> containsValidations = new HashSet<>();

    @Override
    public StringSchema required() {
        super.required();
        validations.add(value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimal length must be positive or zero");
        }
        validations.removeAll(lengthValidations);
        lengthValidations.clear();
        Validation<String> newCheck = value -> value.length() >= min;
        lengthValidations.add(newCheck);
        validations.add(newCheck);
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring can't be null");
        }
        validations.removeAll(containsValidations);
        containsValidations.clear();
        Validation<String> newCheck = value -> value.contains(substring);
        containsValidations.add(newCheck);
        validations.add(newCheck);
        return this;
    }
}
