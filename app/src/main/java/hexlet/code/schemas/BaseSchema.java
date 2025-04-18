package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected Map<String, Predicate<T>> validations = new HashMap<>();


    /**
     * Marks the field as required.
     *
     * @return current schema instance for method chaining
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    /**
     * Checks if value satisfies required constraint.
     *
     * @param value the value to check
     * @return true if value is valid according to required constraint
     */
    protected boolean checkRequired(T value) {
        if (!isRequired) {
            return true;
        }
        return value != null && !(value instanceof String && ((String) value).isEmpty());
    }

    /**
     * Validates the value against schema rules.
     *
     * @param value the value to validate
     * @return true if value is valid
     */
    public boolean isValid(T value) {

        if (!isRequired && value == null) {
            return true;
        }
        if (isRequired && value == null) {
            return false;
        }
        List<Predicate<T>> list = new ArrayList<>();
        for (String key: validations.keySet()) {
            var v = validations.get(key);
            list.add(v);

        }
        return list.stream().allMatch(v -> v.test(value));

    }
}
