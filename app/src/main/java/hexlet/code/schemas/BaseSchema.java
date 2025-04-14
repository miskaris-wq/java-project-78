package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected final List<Predicate<T>> validations = new ArrayList<>();

    public boolean isValid(T value) {
        if (!isRequired && value == null) {
            return true;
        }
        if (isRequired && value == null) {
            return false;
        }
        return validations.stream().allMatch(v -> v.test(value));
    }

    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }

    protected void addValidation(Predicate<T> validation) {
        validations.add(validation);
    }
}
