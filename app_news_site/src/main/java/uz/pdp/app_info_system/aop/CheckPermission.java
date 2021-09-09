package uz.pdp.app_info_system.aop;

import java.lang.annotation.*;

//O'zim yozgan annotatsiya
@Documented
@Target(ElementType.METHOD)//qayerda ishlatishimizni yoziladi
@Retention(RetentionPolicy.RUNTIME)//anotatsiya qachon ishlashi kerakligini yoziladi
public @interface CheckPermission {
    String value();//kiruvchi element, value majburiy emas
}
