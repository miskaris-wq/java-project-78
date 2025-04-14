
package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private boolean isRequired = false;
    private final List<Predicate<T>> validations = new ArrayList<>();

    public BaseSchema() {
        addValidation(value -> !isRequired || value != null);
    }

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    protected void addValidation(Predicate<T> validation) {
        validations.add(validation);
    }

    public boolean isValid(T value) {
        return validations.stream().allMatch(v -> v.test(value));
    }
}
