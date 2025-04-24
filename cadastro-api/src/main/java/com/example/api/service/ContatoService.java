package com.example.api.service;

import com.example.api.dto.ContatoDTO;
import com.example.api.model.Contato;
import com.example.api.model.Profissional;
import com.example.api.repository.ContatoRepository;
import com.example.api.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Getter

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepo;
    private final ProfissionalRepository profissionalRepo;

    /* LISTAR */
    public List<Contato> listar(String q) {
        return (q == null || q.isBlank())
                ? contatoRepo.findAll()
                : contatoRepo.findByNomeContainingIgnoreCaseOrContatoContainingIgnoreCase(q, q);
    }

    /* BUSCAR */
    public Contato buscar(Long id) {
        return contatoRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }

    /* CRIAR */
    @Transactional
    public Contato criar(ContatoDTO dto) {
        Profissional prof = buscarProfissional(dto.profissionalId());

        Contato c = new Contato();
        c.setNome(dto.nome());
        c.setContato(dto.contato());
        c.setProfissional(prof);

        return contatoRepo.save(c);
    }

    /* ATUALIZAR */
    @Transactional
    public Contato atualizar(Long id, ContatoDTO dto) {
        Contato c = buscar(id);
        c.setNome(dto.nome());
        c.setContato(dto.contato());

        if (!c.getProfissional().getId().equals(dto.profissionalId())) {
            Profissional prof = buscarProfissional(dto.profissionalId());
            c.setProfissional(prof);
        }
        return contatoRepo.save(c);
    }

    /* EXCLUIR */
    @Transactional
    public void excluir(Long id) {
        contatoRepo.delete(buscar(id));
    }

    /* utilitário */
    private Profissional buscarProfissional(Long id) {
        return profissionalRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado"));
    }
}
