package uz.pdp.app_info_system.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.FORBIDDEN)//403 qo'ysak ham bo'luradi
@Data
public class ForbiddenException extends RuntimeException{
    private String type;
    private String message;

    public ForbiddenException(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
