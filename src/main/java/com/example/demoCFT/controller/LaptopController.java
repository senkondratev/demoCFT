package com.example.demoCFT.controller;

import com.example.demoCFT.entity.Laptop;
import com.example.demoCFT.exception.NoSuchIdException;
import com.example.demoCFT.exception.NotEnoughArgumentsException;
import com.example.demoCFT.exception.WrongScreenSizeException;
import com.example.demoCFT.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LaptopController {
    @Autowired
    LaptopRepository laptopRepository;
    //допустимые размеры экрана ноутбука
    final ArrayList<Integer> sizes = new ArrayList<>(Arrays.asList(13,14,15,17));

    //добавить новый ноутбук
    @PostMapping("Laptop/add")
    Laptop addLaptop(@RequestBody Laptop laptop){
        //сохраняю переданные значения
        Integer serialNumber = laptop.getSerialNumber();
        String manufacturer = laptop.getManufacturer();
        Double price = laptop.getPrice();
        Integer amount = laptop.getAmount();
        Integer screenSize = laptop.getScreenSize();

        //если не хватает параметров (хранить null в качестве значения не хочу)
        if(serialNumber == null || manufacturer == null || price == null || amount == null || screenSize == null)
            throw new NotEnoughArgumentsException("Required arguments are: serialNumber, manufacturer, price, amount, screenSize");

        //числовые значения должны быть положительны
        if(serialNumber <= 0 || price <= 0 || amount <= 0 || screenSize <= 0)
            throw new NumberFormatException("Digit arguments must be higher than zero");

        //допускатся форм-факторы только из предопределенного набора
        if(!sizes.contains(screenSize)){
            throw new WrongScreenSizeException("Laptop screen size  must be one of following "+sizes.toString());
        }
        return laptopRepository.save(laptop);
    }

    //редактировать информацию о существующем ноутбуке
    @PutMapping("Laptop/put/{id}")
    Laptop updateLaptop(@PathVariable Long id,
                              @RequestBody Laptop l){
        Optional<Laptop> curLaptop = laptopRepository.findById(id);
        Laptop laptop;

        //проверяю наличие ноутбука с заданным id в базе
        if(curLaptop.isPresent())
            laptop = curLaptop.get();
        else
            throw new NoSuchIdException("Cannot find laptop by id "+ id);

        //сохраняю переданные значения
        Integer serialNumber = l.getSerialNumber();
        String manufacturer = l.getManufacturer();
        Double price = l.getPrice();
        Integer amount = l.getAmount();
        Integer screenSize = l.getScreenSize();

        //замены требуют лишь не null значения,числа должны быть положительны ,размер экрана требует проверки на допустимость
        if (serialNumber != null)
            if(serialNumber > 0)
                laptop.setSerialNumber(serialNumber);
            else
                throw new IllegalArgumentException("Serial number must be higher than zero");
        if (manufacturer != null)
            laptop.setManufacturer(manufacturer);
        if (price != null)
            if(price > 0)
                laptop.setPrice(price);
            else
                throw new IllegalArgumentException("Price must be higher than zero");
        if (amount != null)
            if(amount > 0)
                laptop.setAmount(amount);
            else
                throw new IllegalArgumentException("Amount must be higher than zero");

        if (screenSize != null)
            if (sizes.contains(screenSize))
                laptop.setScreenSize(screenSize);
            else
                throw new WrongScreenSizeException("Laptop screen size  must be one of following " + sizes.toString());
        return laptopRepository.save(laptop);
    }

    //получить информацию о всех ноутбуках
    @GetMapping("Laptop/getAll")
    List<Laptop> getAll(){
        return (List<Laptop>) laptopRepository.findAll();
    }

    //получить информацию о конкретном ноутбуке
    @GetMapping("Laptop/get/{id}")
    Laptop getLaptop(@PathVariable Long id){
        try {
            return laptopRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            throw new NoSuchIdException("Cannot find laptop by id "+ id);
        }
    }
}
