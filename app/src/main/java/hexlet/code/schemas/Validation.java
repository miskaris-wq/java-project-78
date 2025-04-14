package hexlet.code.schemas;

@FunctionalInterface
public interface Validation<T> {
    boolean test(T value);
}
