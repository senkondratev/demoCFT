package com.example.demoCFT.repository;

import com.example.demoCFT.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProductRepository<T extends  Product> extends CrudRepository<T, Long> {

}
