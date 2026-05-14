package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common;

public class ApiResponseFactory {

    public static <T> ApiResponse<T> success(
            String message,
            int status,
            T data
    ) {

        return ApiResponse.<T>builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(
            String message,
            int status
    ) {

        return ApiResponse.<T>builder()
                .message(message)
                .status(status)
                .data(null)
                .build();
    }
}
