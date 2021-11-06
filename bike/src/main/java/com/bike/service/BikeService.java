package com.bike.service;

import com.bike.model.Bike;
import com.bike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.getAll();
    }

    public Optional<Bike> getBike(int id) {
        return bikeRepository.getBike(id);
    }

    public Bike save(Bike bike) {
        if (bike.getId() == null) {
            return bikeRepository.save(bike);
        } else {
            Optional<Bike> e = bikeRepository.getBike(bike.getId());
            if (e.isEmpty()) {
                return bikeRepository.save(bike);
            } else {
                return bike;
            }
        }
    }

    public boolean deleteBike(int id) {

        Optional<Bike> bike = getBike(id);

        if (bike.isEmpty()) {
            return false;
        } else {
            bikeRepository.delete(bike.get());
            return true;
        }
    }
    /*
        {"id":1,"brand":"BK","name":"BK shimano 25","description":"BK shimano 25",
        "year":2019}
    */
    public Bike update(Bike bike) {
        if (bike.getId() != null) {
            Optional<Bike> bicicleta = bikeRepository.getBike(bike.getId());
            if (!bicicleta.isEmpty()) {
                if (bike.getBrand() != null) {
                    bicicleta.get().setBrand(bike.getBrand());
                }
                if (bike.getName()!=null){
                    bicicleta.get().setName(bike.getName());
                }
                if (bike.getDescription()!=null){
                    bicicleta.get().setDescription(bike.getDescription());
                }
                if (bike.getYear()!=null){
                    bicicleta.get().setYear(bike.getYear());
                }
                
                return bikeRepository.save(bicicleta.get());
            }
        }
        return bike;
    }

}
