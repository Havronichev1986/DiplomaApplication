package AutoService.DiplomaApplication.repositories;

import AutoService.DiplomaApplication.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByBrandAndVinNumberAndOwnerName(String brand, String vinNumber, String ownerName);
}
