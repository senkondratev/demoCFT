package com.example.demoCFT.controller;

import com.example.demoCFT.entity.PersonalComputer;
import com.example.demoCFT.exception.NoSuchIdException;
import com.example.demoCFT.exception.NotEnoughArgumentsException;
import com.example.demoCFT.exception.WrongPCformException;
import com.example.demoCFT.repository.PersonalComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonalComputerController {
    @Autowired
    PersonalComputerRepository personalComputerRepository;
    //допустимые форм-факторы компьютера
    final ArrayList<String> forms = new ArrayList<>(Arrays.asList("desktop", "nettop", "monoblock"));

    //добавить новый компьютер
    @PostMapping("PC/add")
    PersonalComputer addPC(@RequestBody PersonalComputer pc){
        //сохраняю переданные значения
        Integer serialNumber = pc.getSerialNumber();
        String manufacturer = pc.getManufacturer();
        Double price = pc.getPrice();
        Integer amount = pc.getAmount();
        String form = pc.getForm();

        //если не хватает параметров (хранить null в качестве значения не хочу)
        if(serialNumber == null || manufacturer == null || price == null || amount == null || form == null)
            throw new NotEnoughArgumentsException("Required arguments are: serialNumber, manufacturer, price, amount, form");

        //числовые значения должны быть положительны
        if(serialNumber <= 0 || price <= 0 || amount <= 0)
            throw new NumberFormatException("Digit arguments must be higher than zero");

        //допускаются форм-факторы только из предопределенного набора
        if(!forms.contains(form)){
           throw new WrongPCformException("PC form must be one of following "+forms.toString());
        }
        return personalComputerRepository.save(pc);
    }

    //редактировать информацию о существующем компьютере
    @PutMapping("PC/put/{id}")
    PersonalComputer updatePC(@PathVariable Long id,
                           @RequestBody PersonalComputer pc){
        Optional<PersonalComputer> p = personalComputerRepository.findById(id);
        PersonalComputer computer;

        //проверяю наличие компьютера с заданным id в базе
        if(p.isPresent())
            computer = p.get();
        else
            throw new NoSuchIdException("Cannot find PC by id "+ id);

        //сохраняю переданные значения
        Integer serialNumber = pc.getSerialNumber();
        String manufacturer = pc.getManufacturer();
        Double price = pc.getPrice();
        Integer amount = pc.getAmount();
        String form = pc.getForm();

        //замены требуют лишь не null значения,числа должны быть положительны ,форм-фактор требует проверки на допустимость
        if (serialNumber != null)
            if(serialNumber > 0)
                computer.setSerialNumber(serialNumber);
            else
                throw new IllegalArgumentException("Serial number must be higher than zero");
        if (manufacturer != null)
            computer.setManufacturer(manufacturer);
        if (price != null)
            if(price > 0)
                computer.setPrice(price);
            else
                throw new IllegalArgumentException("Price must be higher than zero");
        if (amount != null)
            if(amount > 0)
                computer.setAmount(amount);
            else
                throw new IllegalArgumentException("Amount must be higher than zero");

        if (form != null)
            if (forms.contains(form))
                computer.setForm(form);
            else
                throw new WrongPCformException("PC form must be one of following " + forms.toString());
        return personalComputerRepository.save(computer);
    }

    //получить информацию о всех компьютерах
    @GetMapping("PC/getAll")
    List<PersonalComputer> getAll(){
        return (List<PersonalComputer>) personalComputerRepository.findAll();
    }

    //получить информацию о конкретном компьютере
    @GetMapping("PC/get/{id}")
    PersonalComputer getPC(@PathVariable Long id){
        try {
            return personalComputerRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            throw new NoSuchIdException("Cannot find PC by id "+ id);
        }
    }
}
