package com.example.demo.service;

import com.example.demo.dto.CarDto;
import com.example.demo.model.car.Car;
import com.example.demo.model.car.Model;
import com.example.demo.model.car.Producer;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.ProducerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Objects;

@Service
public class CarService {
    private CarRepository carRepository;
    private ProducerRepository producerRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;

    @Autowired
    public CarService(CarRepository carRepository, ProducerRepository producerRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.producerRepository = producerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    public Iterable<CarDto> getCars() {
        Iterable<CarDto> dtos = new LinkedList<>();
        for (Car car :carRepository.findAllByUserId(UserUtil.getAutorizedId())
             ) {
            ((LinkedList<CarDto>) dtos).add(CarDto.asTo(car));

        }
        return dtos;
    }

    public Car getById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car save(CarDto car) {
        Model model = modelRepository.getModelByModelNameAndProducerName(car.getProducer(), car.getModel()).orElse(null);
        if (model == null) return null;
        Car savedCar;
        if (car.getId() == null) {
//            String number, String description, @NotNull User user, @NotNull Model model
            savedCar = new Car(car.getNumber(), car.getDescription(), userRepository.findById(Objects.requireNonNull(UserUtil.getAutorizedId())).get(), model);
        } else {
            savedCar = carRepository.findById(car.getId()).orElse(null);
            if (savedCar == null) return null;
            model.setType(car.getCarType());
            savedCar.setDescription(car.getDescription());
            savedCar.setNumber(car.getNumber());
            savedCar.setModel(model);
            savedCar.setDescription(car.getDescription());
        }
        return carRepository.save(savedCar);
    }

    public Car getByNumber(String number) {
                return carRepository.findByNumber(number).orElse(null);
    }
}
