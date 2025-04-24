package com.example.api.controller;

import com.example.api.dto.ContatoDTO;
import com.example.api.model.Contato;
import com.example.api.service.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService service;

    @GetMapping
    public List<Contato> listar(@RequestParam(required = false) String q){
        return service.listar(q);
    }

    @GetMapping("/{id}")
    public Contato buscar(@PathVariable Long id){
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Contato> criar(@Valid @RequestBody ContatoDTO dto){
        Contato salvo = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public Contato atualizar(@PathVariable Long id, @Valid @RequestBody ContatoDTO dto){
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id){
        service.excluir(id);
        return "Sucesso, contato excluído";
    }
}
