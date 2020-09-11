package com.example.demoCFT;

import com.example.demoCFT.entity.HardDrive;
import com.example.demoCFT.entity.Laptop;
import com.example.demoCFT.entity.Monitor;
import com.example.demoCFT.entity.PersonalComputer;
import com.example.demoCFT.repository.HardDriveRepository;
import com.example.demoCFT.repository.LaptopRepository;
import com.example.demoCFT.repository.MonitorRepository;
import com.example.demoCFT.repository.PersonalComputerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FillDatabase {
    private static final Logger log = LoggerFactory.getLogger(FillDatabase.class);
    @Bean
    CommandLineRunner loadDatabase(HardDriveRepository hardDriveRepository,
                                   LaptopRepository laptopRepository,
                                   MonitorRepository monitorRepository,
                                   PersonalComputerRepository personalComputerRepository) {

        return args -> {
            log.info("Adding PC " + personalComputerRepository.save(new PersonalComputer(5, "Intel",
                    500.0, 21, "desktop")));
            log.info("Adding PC " + personalComputerRepository.save(new PersonalComputer(21, "AMD",
                    470.0, 40, "monoblock")));

            log.info("Adding laptop " + laptopRepository.save(new Laptop(2, "MSI",
                    550.0, 32, 14)));
            log.info("Adding laptop " + laptopRepository.save(new Laptop(7, "Apple",
                    531.0, 72, 15)));

            log.info("Adding monitor " + monitorRepository.save(new Monitor(93, "Samsung",
                    700.0, 1231, 42.0)));
            log.info("Adding monitor " + monitorRepository.save(new Monitor(33, "LG",
                    600.0, 221, 38.0)));

            log.info("Adding hard drive " + hardDriveRepository.save(new HardDrive(100, "WD",
                    200.0, 5445, 500)));
            log.info("Adding hard drive " + hardDriveRepository.save(new HardDrive(101, "WD",
                    230.0, 545, 1000)));

        };
    }
}
