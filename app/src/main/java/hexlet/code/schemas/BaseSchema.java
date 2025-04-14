package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected final List<Predicate<T>> validations = new ArrayList<>();

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    protected final void addValidation(Predicate<T> validation) {
        validations.add(validation);
    }

    public boolean isValid(T value) {
        if (isRequired && value == null) {
            return false;
        }
        if (!isRequired && value == null) {
            return true;
        }
        return validations.stream().allMatch(v -> v.test(value));
    }
}
