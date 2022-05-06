package wooteco.subway.error;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> duplicateKeyExceptionHandler() {
        return ResponseEntity.badRequest().body(new ErrorResponse("중복된 이름입니다."));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> EmptyResultDataAccessExceptionHandler() {
        return ResponseEntity.badRequest().body(new ErrorResponse("해당 값이 존재하지 않습니다."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler() {
        return ResponseEntity.internalServerError().body(new ErrorResponse("현재 서버에 문제가 발생해 응답할 수 없습니다."));
    }
}
