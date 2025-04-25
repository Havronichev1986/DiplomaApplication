package AutoService.DiplomaApplication.repositories;

import AutoService.DiplomaApplication.entities.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {
}
