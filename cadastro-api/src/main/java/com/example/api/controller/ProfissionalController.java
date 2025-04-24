package com.example.api.controller;

import com.example.api.dto.FieldFilter;
import com.example.api.model.Profissional;
import com.example.api.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {
    private final ProfissionalService service;

    @GetMapping
    public List<?> listar(@RequestParam(required=false) String q,
                          @RequestParam(required=false) List<String> fields){
        return service.list(q).stream()
                .map(p -> FieldFilter.filter(p, fields))
                .toList();
    }

    @GetMapping("/{id}")    public Profissional buscar(@PathVariable Long id){ return service.active(id); }

    @PostMapping            public ResponseEntity<?> criar(@Valid @RequestBody Profissional p){
        return ResponseEntity.status(201).body(service.save(p)); }

    @PutMapping("/{id}")    public String atualizar(@PathVariable Long id, @RequestBody @NotNull Profissional in){
        Profissional p = service.active(id);
        BeanUtils.copyProperties(in, p, "id", "createdDate", "ativo", "contatos");
        service.save(p); return "Sucesso cadastrado alterado"; }

    @DeleteMapping("/{id}") public String excluir (@PathVariable Long id){ service.logicalDelete(id); return "Sucesso profissional excluído"; }
}