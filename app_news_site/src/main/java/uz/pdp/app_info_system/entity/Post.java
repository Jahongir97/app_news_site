package uz.pdp.app_info_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_info_system.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AbsEntity {

    @NotBlank(message = "Title bo'sh bo'lmasligi kerak")
    @Column(nullable = false,columnDefinition = "text")
    private String title;

    @NotBlank(message = "Text bo'sh bo'lmasligi kerak")
    @Column(nullable = false,columnDefinition = "text")
    private String text;

    @NotBlank(message = "Url bo'sh bo'lmasligi kerak")
    @Column(nullable = false,columnDefinition = "text")
    private String url;

}
