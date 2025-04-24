package AutoService.DiplomaApplication.controllers;

import AutoService.DiplomaApplication.dtos.AuthRequest;
import AutoService.DiplomaApplication.dtos.AuthResponse;
import AutoService.DiplomaApplication.dtos.RegisterRequest;
import AutoService.DiplomaApplication.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
//        return ResponseEntity.ok(authService.register((request)));
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String token = authService.register(request);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.auth(request));
    }
}
