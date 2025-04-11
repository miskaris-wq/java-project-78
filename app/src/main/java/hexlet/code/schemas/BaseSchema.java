package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    protected boolean checkRequired(T value) {
        if (!isRequired) {
            return true;
        }
        return value != null && !(value instanceof String && ((String) value).isEmpty());
    }

    public abstract boolean isValid(T value);
}
