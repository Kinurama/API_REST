package com.example.api.repository;

import com.example.api.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByNomeContainingIgnoreCaseOrContatoContainingIgnoreCase(String nome, String contato);
}
