/*package hexlet.code.schemas;

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
        validations.removeIf(v -> v.toString().contains("length"));
        validations.add(validation);
    }

}
*/
package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected List<Validation<T>> validations = new ArrayList<>();

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
        return validations.stream().allMatch(v -> v.test(value));


    }

}
