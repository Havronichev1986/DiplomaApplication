package AutoService.DiplomaApplication.services;

import AutoService.DiplomaApplication.dtos.AuthRequest;
import AutoService.DiplomaApplication.dtos.AuthResponse;
import AutoService.DiplomaApplication.dtos.RegisterRequest;
import AutoService.DiplomaApplication.entities.Role;
import AutoService.DiplomaApplication.entities.User;
import AutoService.DiplomaApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest authRequest) {
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            throw new RuntimeException("Такой пользователь уже существует");
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.USER);  // Роль по умолчанию — USER
        userRepository.save(user);

        return jwtService.generateToken(user.getEmail(),user.getRole()); // передаем роль в JWT
    }

    public AuthResponse auth(AuthRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("Пользователь не найден!"));

        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Неверный пароль");
        }
        String token = jwtService.generateToken(user.getEmail(),user.getRole());
        return new AuthResponse(token);
    }
}
