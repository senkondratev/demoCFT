package com.example.demoCFT.controller;

import com.example.demoCFT.entity.HardDrive;
import com.example.demoCFT.exception.NoSuchIdException;
import com.example.demoCFT.exception.NotEnoughArgumentsException;
import com.example.demoCFT.repository.HardDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class HardDriveController {
    @Autowired
    private HardDriveRepository hardDriveRepository;

    //добавить новый жесткий диск
    @PostMapping("HDD/add")
    HardDrive addHardDrive(@RequestBody HardDrive hd){
        //сохраняю переданные значения
        Integer serialNumber = hd.getSerialNumber();
        String manufacturer = hd.getManufacturer();
        Double price = hd.getPrice();
        Integer amount = hd.getAmount();
        Integer volume = hd.getVolume();

        //если не хватает параметров (хранить null в качестве значения не хочу)
        if(serialNumber == null || manufacturer == null || price == null || amount == null || volume == null)
            throw new NotEnoughArgumentsException("Required arguments are: serialNumber, manufacturer, price, amount, volume");

        //числовые значения должны быть положительны
        if(serialNumber <= 0 || price <= 0 || amount <= 0 || volume <= 0)
            throw new NumberFormatException("Digit arguments must be higher than zero");

        return hardDriveRepository.save(hd);
    }

    //редактировать информацию о существующем жестком диске
    @PutMapping("HDD/put/{id}")
    HardDrive updateHardDrive(@PathVariable Long id,
                          @RequestBody HardDrive hd){
        Optional<HardDrive> curHD = hardDriveRepository.findById(id);
        HardDrive hardDrive;

        //проверяю наличие жесткого диска с заданным id в базе
        if(curHD.isPresent())
            hardDrive = curHD.get();
        else
            throw new NoSuchIdException("Cannot find hard drive by id "+ id);

        //сохраняю переданные значения
        Integer serialNumber = hd.getSerialNumber();
        String manufacturer = hd.getManufacturer();
        Double price = hd.getPrice();
        Integer amount = hd.getAmount();
        Integer volume = hd.getVolume();

        //замены требуют лишь не null значения,числа должны быть положительны
        if (serialNumber != null)
            if(serialNumber > 0)
                hardDrive.setSerialNumber(serialNumber);
            else
                throw new IllegalArgumentException("Serial number must be higher than zero");
        if (manufacturer != null)
            hardDrive.setManufacturer(manufacturer);
        if (price != null)
            if(price > 0)
                hardDrive.setPrice(price);
            else
                throw new IllegalArgumentException("Price must be higher than zero");
        if (amount != null)
            if(amount > 0)
                hardDrive.setAmount(amount);
            else
                throw new IllegalArgumentException("Amount must be higher than zero");
        if (volume != null)
            if(volume > 0 )
                hardDrive.setVolume(volume);
            else
                throw new IllegalArgumentException("Volume must be higher than zero");

        return hardDriveRepository.save(hardDrive);
    }

    //получить информацию о всех жестких дисках
    @GetMapping("HDD/getAll")
    List<HardDrive> getAll(){
        return (List<HardDrive>) hardDriveRepository.findAll();
    }

    //получить информацию о конкретном жестком диске
    @GetMapping("HDD/get/{id}")
    HardDrive getHardDrive(@PathVariable Long id){
        try {
            return hardDriveRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            throw new NoSuchIdException("Cannot find hard drive by id "+ id);
        }
    }
}
