/*package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addValidation(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Min must be less than max");
        }
        addValidation(value -> value == null || (value >= min && value <= max));
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
}
*/

package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;

public final class NumberSchema extends BaseSchema<Integer> {

    private final Set<Validation<Integer>> rangeValidations = new HashSet<>();

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        validations.add(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        validations.removeAll(rangeValidations);
        rangeValidations.clear();

        Validation<Integer> newCheckMin = value -> value >= min;
        rangeValidations.add(newCheckMin);
        validations.add(newCheckMin);

        Validation<Integer> newCheckMax = value -> value <= max;
        rangeValidations.add(newCheckMax);
        validations.add(newCheckMax);

        return this;
    }

}

