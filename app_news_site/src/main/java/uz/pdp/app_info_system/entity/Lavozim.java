package uz.pdp.app_info_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.app_info_system.entity.enums.Huquq;
import uz.pdp.app_info_system.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lavozim extends AbsEntity {

    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Huquq> huquqList;

    @Column(columnDefinition = "text")
    private String description;
}
