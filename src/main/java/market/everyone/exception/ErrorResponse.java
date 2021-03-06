package market.everyone.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final OffsetDateTime time = OffsetDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String detail;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .detail(errorCode.getMessage())
                        .message(e.getMessage())
                        .build());
    }
}
