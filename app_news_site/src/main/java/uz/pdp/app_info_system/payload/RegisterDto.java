package uz.pdp.app_info_system.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotNull(message = "Fullname bo'sh bo'lmasligi kerak")
    private String fullName;

    @NotNull(message = "Username bo'sh bo'lmasligi kerak")
    private String username;

    @NotNull(message = "Password bo'sh bo'lmasligi kerak")
    private String password;

    @NotNull(message = "Prepassword bo'sh bo'lmasligi kerak")
    private String prepassword;

}
