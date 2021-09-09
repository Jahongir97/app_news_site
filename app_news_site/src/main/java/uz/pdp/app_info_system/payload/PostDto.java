package uz.pdp.app_info_system.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotBlank(message = "Title bo'sh bo'lmasligi kerak")
    private String title;

    @NotBlank(message = "Text bo'sh bo'lmasligi kerak")
    private String text;

    @NotBlank(message = "Url bo'sh bo'lmasligi kerak")
    private String url;

}
