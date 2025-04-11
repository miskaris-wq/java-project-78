package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    /**
     * Marks the field as required.
     * @return current schema instance for method chaining
     */

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    /**
     * Checks if value satisfies required constraint.
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
     * @param value the value to validate
     * @return true if value is valid
     */
    public abstract boolean isValid(T value);
}
