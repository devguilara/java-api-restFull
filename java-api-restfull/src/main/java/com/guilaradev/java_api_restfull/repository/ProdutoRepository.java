package com.guilaradev.java_api_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guilaradev.java_api_restfull.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    
}
