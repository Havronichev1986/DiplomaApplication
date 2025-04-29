package AutoService.DiplomaApplication.services;

import AutoService.DiplomaApplication.dtos.OrderRequest;
import AutoService.DiplomaApplication.dtos.OrderResponse;
import AutoService.DiplomaApplication.entities.Car;
import AutoService.DiplomaApplication.entities.Order;
import AutoService.DiplomaApplication.entities.User;
import AutoService.DiplomaApplication.repositories.CarRepository;
import AutoService.DiplomaApplication.repositories.OrderRepository;
import AutoService.DiplomaApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderResponse createCarAndOrder(OrderRequest dto, String userEmail) {
        // Получаем пользователя по email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Ищем машину по VIN, принадлежащую этому пользователю
        Optional<Car> existingCarOpt = user.getCars().stream()
                .filter(car -> car.getVinNumber().equalsIgnoreCase(dto.vinNumber()))
                .findFirst();

        Car car = existingCarOpt.orElseGet(() -> {
            // Если машина не найдена, создаём новую
            Car newCar = new Car();
            newCar.setBrand(dto.brand());
            newCar.setVinNumber(dto.vinNumber());
            newCar.setOwner(user);

            // Убедимся, что добавляем эту машину в список у пользователя
            user.getCars().add(newCar);
            carRepository.save(newCar);
            userRepository.save(user); // Обновляем пользователя с новой машиной
            return newCar; // Возвращаем только что сохранённую машину
        });

        // Создаем заявку
        Order order = new Order();
        order.setDescription(dto.description());
        order.setDate(dto.date());
        order.setStatus(dto.status());
        order.setCar(car);

        Order saved = orderRepository.save(order);
        return new OrderResponse(saved.getId(), saved.getDescription(), saved.getDate(), saved.getStatus());
    }
}