package com.guilaradev.java_api_restfull.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilaradev.java_api_restfull.model.Produto;
import com.guilaradev.java_api_restfull.model.Exception.ResourceNotFoundException;
import com.guilaradev.java_api_restfull.repository.ProdutoRepository;
import com.guilaradev.java_api_restfull.shared.ProdutoDTO;


@Service
public class ProdutoService {
    
    @Autowired //invertendo controle -> agora EU tomo conto do repository
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> getAllProducts(){

        List<Produto> produtoDTO =  produtoRepository.findAll();
        return produtoDTO.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> getProductById(Integer id){
        Optional<Produto> produtoDTO = produtoRepository.findById(id);

        if(produtoDTO.isEmpty()){
            throw new ResourceNotFoundException("Produto nao encontrado");
        }
        ProdutoDTO dto = new ModelMapper().map(produtoDTO.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }
   
    public ProdutoDTO addProduto(ProdutoDTO produtoDTO){
        //Remove o id para conseguir fazer o cadastro
        produtoDTO.setId(null);

        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();


        //Converter o nosso ProdutoDTO em um Produto
        Produto produto = mapper.map(produtoDTO, Produto.class);

        //salvar o Produto no banco
        produto = produtoRepository.save(produto);

        //Puxa o id criado do banco
        produtoDTO.setId(produto.getId());

        //Retornar o ProdutoDTO atualizado
        return produtoDTO;
       
    }

    public ProdutoDTO updateProduto(Integer id ,ProdutoDTO produtoDTO){
        // Passar o ID para o produtoDTO
        produtoDTO.setId(id);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o produtoDTO em um produto
        Produto produto = mapper.map(produtoDTO, Produto.class);

        // Atualizar o produto no BD
        produtoRepository.save(produto);

        // Retornar o produtoDTO atualizado

        return produtoDTO;
    }


    public void deleteProduto(Integer id){
        //Verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);
        // Se nao existir lança uma excessão
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Nao foi possivel deletar o produto");
        }
        
        //Deleta o produto pelo ID
        produtoRepository.deleteById(id);
    }
}
