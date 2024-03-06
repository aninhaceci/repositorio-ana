package com.example.demo.ifpr.repository;

import com.example.demo.ifpr.model.Usuario;
import org.springframework.data.repository.CrudRepository;

//CRUD é uma interface padrão
//<> - genérico, levar a classe em consideração (ex a tabela "usuario")
public interface UsuarioRepository extends CrudRepository <Usuario, Long>{
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
}
