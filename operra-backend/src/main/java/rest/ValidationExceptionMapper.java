package rest;

import core.ValidationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "validation_failed");

        List<Map<String, String>> messages = exception.getMessages().stream().map(msg -> {
            Map<String, String> item = new HashMap<>();
            item.put("field", msg.getField() != null ? msg.getField() : "unknown");
            item.put("message", msg.getMessage() != null ? msg.getMessage() : "Validation error");
            return item;
        }).collect(Collectors.toList());

        errorResponse.put("messages", messages);

        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(errorResponse)
                .build();
    }
}