//package AutoService.DiplomaApplication.controllers;
//
//import AutoService.DiplomaApplication.entities.User;
//import AutoService.DiplomaApplication.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin")
//@RequiredArgsConstructor
//public class AdminController {
//
//    private final UserRepository userRepository;
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userRepository.findAll());
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        if (!userRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        userRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
