package uz.pdp.app_info_system.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.exceptions.ForbiddenException;

@Component
@Aspect// anotatsiyamga natija ekanligi bildiradi
public class HuquqniTekshirishniIshlatuvchi {
    @Before(value = "@annotation(huquqniTekshirish)")
    public void huquqniTekshiruvchiMetodim(HuquqniTekshirish huquqniTekshirish){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist=false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(huquqniTekshirish.huquq())){
                exist=true;
            }
        }
        if (!exist)
            throw new ForbiddenException(huquqniTekshirish.huquq(), "Afsuski ruxsat yo'q");
    }
}
