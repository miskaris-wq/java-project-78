package hexlet.code;

public class StringSchema {
    private boolean isRequired = false;
    private int minLengthValue = 0;
    private String requiredSubstring = null;
    public StringSchema required() {
        this.isRequired = true;
        return this;

    }

    public StringSchema minLength(int min) {
        this.minLengthValue = min;
        return this;
    }
    public StringSchema contains(String substring) {
        this.requiredSubstring = substring;
        return this;

    }

    public boolean isValid(String string) {
        if (isRequired && (string == null || string.isEmpty())) {
            return false;
        }
        if (string == null || string.isEmpty()) {
            return true;
        }

        if (string.length() < minLengthValue) {
            return false;
        }

        return requiredSubstring == null || string.contains(requiredSubstring);
    }

}
