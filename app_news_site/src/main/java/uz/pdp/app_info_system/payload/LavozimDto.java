package uz.pdp.app_info_system.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_info_system.entity.enums.Huquq;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LavozimDto {
    @NotBlank(message = "Lavozim nomi bo'sh bo'lmasligi kerak")
    private String name;

    private String description;

    @NotEmpty
    private List<Huquq> huquqList;


}
