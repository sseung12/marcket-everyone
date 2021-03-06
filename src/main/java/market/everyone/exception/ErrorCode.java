package market.everyone.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 이메일을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
