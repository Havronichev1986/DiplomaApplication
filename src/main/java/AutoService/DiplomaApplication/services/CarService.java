package AutoService.DiplomaApplication.services;

import AutoService.DiplomaApplication.entities.Cars;
import AutoService.DiplomaApplication.repositories.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

public List<Cars> findAll(){
    return carRepository.findAll();
}
public Optional<Cars> findById(Long id){
    return carRepository.findById(id);
}
public Cars save(Cars cars){
    return carRepository.save(cars);
}

@Transactional
    public List<Cars> create(List<Cars> carsList){
    return carRepository.saveAll(carsList);
}
public void deleteById(Long id){
    carRepository.deleteById(id);
}
}
