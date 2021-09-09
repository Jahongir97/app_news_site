package uz.pdp.app_info_system.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.app_info_system.entity.Lavozim;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.entity.enums.Huquq;
import uz.pdp.app_info_system.repository.LavozimRepository;
import uz.pdp.app_info_system.repository.UserRepository;
import uz.pdp.app_info_system.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.app_info_system.entity.enums.Huquq.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialModeType;

    @Override
    public void run(String... args) throws Exception {
        if (initialModeType.equals("always")){
            Huquq[] values = Huquq.values();
            Lavozim admin = lavozimRepository.save(new Lavozim(
                    AppConstants.ADMIN,
                    Arrays.asList(values),
                    "admin-sistema egasi"
            ));
            Lavozim user = lavozimRepository.save(new Lavozim(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "user-oddiy foydalanuvchi"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }
}
