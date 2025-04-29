package AutoService.DiplomaApplication.controllers;

import AutoService.DiplomaApplication.dtos.OrderRequest;
import AutoService.DiplomaApplication.dtos.OrderResponse;
import AutoService.DiplomaApplication.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createCarAndOrder(@RequestBody OrderRequest dto,
                                                           Authentication authentication) {
        String userEmail = authentication.getName();
        OrderResponse created = orderService.createCarAndOrder(dto, userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
