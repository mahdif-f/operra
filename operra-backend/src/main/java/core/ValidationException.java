package core;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<ValidationMessage> messages = new ArrayList<>();

    public ValidationException(String field, String message) {
        this.messages.add(new ValidationMessage(field, message));
    }

    public void add(String field, String message) {
        this.messages.add(new ValidationMessage(field, message));
    }

    public List<ValidationMessage> getMessages() {
        return messages;
    }

    public static class ValidationMessage {
        private final String field;
        private final String message;

        public ValidationMessage(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
