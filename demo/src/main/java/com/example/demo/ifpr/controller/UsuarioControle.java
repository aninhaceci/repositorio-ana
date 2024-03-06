package com.example.demo.ifpr.controller;

import com.example.demo.ifpr.model.Usuario;
import com.example.demo.ifpr.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioControle {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping (path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String nome,
                                           @RequestParam String email,
                                           @RequestParam String senha,
                                           @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataNascimento){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setDataNascimento(dataNascimento);
        usuarioRepository.save(usuario);
        return "Salvou!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Usuario> pegaTodos(){
        return usuarioRepository.findAll();

    }

    @GetMapping(path="/findByEmail")
    public @ResponseBody Usuario findByEmail(
            @RequestParam String email
    ){
        Usuario achou = usuarioRepository.findByEmail(email);
        return achou;
    }

    @GetMapping(path="/existeEmail")
    public @ResponseBody boolean existeEmail(
            @RequestParam String email
    ){
        return  usuarioRepository.existsByEmail(email);

    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<Usuario> getUsuario(@PathVariable String id){
        return usuarioRepository.findById(Long.parseLong(id));
    }

    @GetMapping(path="/nascidos")
    public @ResponseBody Usuario usuariosNascidos(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataNascimento
    ){
        Usuario nasceram = usuarioRepository.findByStartDateBetween(dataNascimento);
        return nasceram;
    }
}
