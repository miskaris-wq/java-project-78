package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        validations.put("required", value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimal length must be positive or zero");
        }
        validations.put("minLength", value -> value.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring can't be null");
        }
        validations.put("contains", value -> value.contains(substring));
        return this;
    }
}
