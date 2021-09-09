package uz.pdp.app_info_system.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_info_system.entity.Post;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull(message = "Text bo'sh bo'lmasligi kerak")
    private String text;

    @NotNull(message = "Post bo'sh bo'lmasligi kerak")
    private Long postId;

}
