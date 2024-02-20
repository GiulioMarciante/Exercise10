package develhope.co.exercise10.controllers;

import develhope.co.exercise10.entities.Car;
import develhope.co.exercise10.repositories.CarRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping("/create")
    public Car createCar(@RequestBody Car car) {
        Car newCar = carRepository.saveAndFlush(car);
        return newCar;
    }

    @GetMapping("/showall")
    public List<Car> allCars() {
        return carRepository.findAll();
    }

    @GetMapping("/showbyid/{id}")
    public Car allCars(@PathVariable Long id) {
        Car carById = null;
        if (carRepository.existsById(id)) {
            return carById = carRepository.getReferenceById(id);
        }
        return carById;
    }

    @PutMapping("/changetype/{id}")
    public Car changeTypeById(@PathVariable Long id, @RequestParam String type) {
        Car carToChange = carRepository.getReferenceById(id);
        if (carRepository.existsById(id)) {
            carToChange.setType(type);
            carRepository.saveAndFlush(carToChange);
        }
        return carToChange;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteall")
    public void deleteAllCars(){
        carRepository.deleteAll();
    }
}