package uz.pdp.app_info_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.app_info_system.entity.User;


@Configuration
@EnableJpaAuditing
public class KimYozganiniQaytarish {

    @Bean
    AuditorAware<Long> auditorAware(){
        return new KimYozganiniBIlish();
    }
}
