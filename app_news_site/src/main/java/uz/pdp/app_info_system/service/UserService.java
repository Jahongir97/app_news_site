package uz.pdp.app_info_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_info_system.entity.Lavozim;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.UserDto;
import uz.pdp.app_info_system.repository.LavozimRepository;
import uz.pdp.app_info_system.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public ApiResponse addUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return new ApiResponse("Bunday username mavjud",false);
        }
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(userDto.getLavozimId());
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Bunday lavozim mavjud emas",false);
        User user=new User(
                userDto.getFullName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                optionalLavozim.get(),
                true
        );
        userRepository.save(user);
        return new ApiResponse("User saqlandi",true);
    }

    public ApiResponse editUser(UserDto userDto, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User topilmadi", false);
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return new ApiResponse("Bunday username mavjud",false);
        }
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(userDto.getLavozimId());
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Bunday lavozim mavjud emas",false);
        User user=optionalUser.get();
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLavozim(optionalLavozim.get());
        userRepository.save(user);
        return new ApiResponse("User tahrirlandi",true);
    }

    public ApiResponse deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User topilmadi", false);
        userRepository.deleteById(id);
        return new ApiResponse("User o'chirildi", true);

    }

    public ApiResponse getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> new ApiResponse("User topildi", true, user)).orElseGet(() -> new ApiResponse("User topilmadi", false));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
