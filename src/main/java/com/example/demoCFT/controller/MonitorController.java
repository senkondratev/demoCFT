package com.example.demoCFT.controller;

import com.example.demoCFT.entity.Monitor;
import com.example.demoCFT.exception.NoSuchIdException;
import com.example.demoCFT.exception.NotEnoughArgumentsException;
import com.example.demoCFT.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class MonitorController {
    @Autowired
    MonitorRepository monitorRepository;

    //добавить новый монитор
    @PostMapping("Monitor/add")
    Monitor addMonitor(@RequestBody Monitor monitor){
        //сохраняю переданные значения
        Integer serialNumber = monitor.getSerialNumber();
        String manufacturer = monitor.getManufacturer();
        Double price = monitor.getPrice();
        Integer amount = monitor.getAmount();
        Double diagonal = monitor.getDiagonal();

        //если не хватает параметров (хранить null в качестве значения не хочу)
        if(serialNumber == null || manufacturer == null || price == null || amount == null || diagonal == null)
            throw new NotEnoughArgumentsException("Required arguments are: serialNumber, manufacturer, price, amount, diagonal");

        //числовые значения должны быть положительны
        if(serialNumber <= 0 || price <= 0 || amount <= 0 || diagonal <= 0)
            throw new NumberFormatException("Digit arguments must be higher than zero");

        return monitorRepository.save(monitor);
    }

    //редактировать информацию о существующем мониторе
    @PutMapping("Monitor/put/{id}")
    Monitor updateMonitor(@PathVariable Long id,
                        @RequestBody Monitor m){
        Optional<Monitor> curMonitor = monitorRepository.findById(id);
        Monitor monitor;

        //проверяю наличие монитора с заданным id в базе
        if(curMonitor.isPresent())
            monitor = curMonitor.get();
        else
            throw new NoSuchIdException("Cannot find monitor by id "+ id);

        //сохраняю переданные значения
        Integer serialNumber = m.getSerialNumber();
        String manufacturer = m.getManufacturer();
        Double price = m.getPrice();
        Integer amount = m.getAmount();
        Double diagonal = m.getDiagonal();

        //замены требуют лишь не null значения,числа должны быть положительны
        if (serialNumber != null)
            if(serialNumber > 0)
                monitor.setSerialNumber(serialNumber);
            else
                throw new IllegalArgumentException("Serial number must be higher than zero");
        if (manufacturer != null)
            monitor.setManufacturer(manufacturer);
        if (price != null)
            if(price > 0)
                monitor.setPrice(price);
            else
                throw new IllegalArgumentException("Price must be higher than zero");
        if (amount != null)
            if(amount > 0)
                monitor.setAmount(amount);
            else
                throw new IllegalArgumentException("Amount must be higher than zero");
        if (diagonal != null)
            if(diagonal > 0)
                monitor.setDiagonal(diagonal);
            else
                throw new IllegalArgumentException("Diagonal must be higher than zero");

        return monitorRepository.save(monitor);
    }

    //получить информацию о всех мониторах
    @GetMapping("Monitor/getAll")
    List<Monitor> getAll(){
        return (List<Monitor>) monitorRepository.findAll();
    }

    //получить информацию о конкретном мониторе
    @GetMapping("Monitor/get/{id}")
    Monitor getMonitor(@PathVariable Long id){
        try {
            return monitorRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            throw new NoSuchIdException("Cannot find monitor by id "+ id);
        }
    }
}
