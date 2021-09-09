package uz.pdp.app_info_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_info_system.entity.User;
import uz.pdp.app_info_system.exceptions.ResourceNotFoundException;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.LoginDto;
import uz.pdp.app_info_system.payload.RegisterDto;
import uz.pdp.app_info_system.repository.LavozimRepository;
import uz.pdp.app_info_system.repository.UserRepository;
import uz.pdp.app_info_system.security.JwtProvider;
import uz.pdp.app_info_system.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;


    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrepassword()))
            return new ApiResponse("Parollar mos emas", false);
        boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (existsByUsername)
            return new ApiResponse("Username mavjud", false);
        User user=new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                lavozimRepository.findByName(AppConstants.USER).orElseThrow(()->new ResourceNotFoundException("Lavozim","name",AppConstants.USER)),
                true

        );
        userRepository.save(user);
        return new ApiResponse("Muaffaqiyatli ro'yxatdan o'tdingiz", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username+" topilmadi"));
    }

    public ApiResponse loginUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            User user = (User)authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getLavozim());
            return new ApiResponse("Tizimga xush kelibsiz",true,token);

        }catch (Exception exception) {
            return new ApiResponse("Parol yoki login noto'g'ri", false);
        }
    }
}
