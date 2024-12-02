package com.guilaradev.java_api_restfull.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilaradev.java_api_restfull.services.ProdutoService;
import com.guilaradev.java_api_restfull.shared.ProdutoDTO;
import com.guilaradev.java_api_restfull.view.model.ProdutoRequest;
import com.guilaradev.java_api_restfull.view.model.ProdutoResponse;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAllProducts(){
        List<ProdutoDTO> produtos  = produtoService.getAllProducts();
        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> res = produtos.stream()
        .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> addProduto(
        @RequestBody ProdutoRequest produtoReq
    ){
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO dto = mapper.map(produtoReq, ProdutoDTO.class);
        dto = produtoService.addProduto(dto);
        
        return new ResponseEntity<>(mapper.map(dto, ProdutoResponse.class), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getProductById(@PathVariable Integer id) {
        Optional<ProdutoDTO> dto = produtoService.getProductById(id);
    
        if (dto.isPresent()) {
            ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PutMapping
    public ResponseEntity<ProdutoResponse> updateProduto(
        @RequestBody ProdutoRequest produtoReq,
        @PathVariable Integer id
    ){
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO dto = mapper.map(produtoReq, ProdutoDTO.class);
        dto =  produtoService.updateProduto(id, dto);
        return new ResponseEntity<>(
            mapper.map(dto,ProdutoResponse.class),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
        @PathVariable Integer id
    ){
        produtoService.deleteProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
