package com.example.api.service;

import com.example.api.model.Profissional;
import com.example.api.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {
    private final ProfissionalRepository repo;

    public List<Profissional> list(String q){ return repo.busca(q); }
    public Profissional save(Profissional p){ return repo.save(p); }
    public Profissional active(Long id){
        return repo.findById(id)
                .filter(Profissional::getAtivo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public void logicalDelete(Long id){
        Profissional p = active(id);
        p.setAtivo(false);
        repo.save(p);
    }
}