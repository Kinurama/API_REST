package com.example.api.repository;
import com.example.api.model.Profissional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    @Query("select p from Profissional p where " +
            "(lower(p.nome) like lower(concat('%',:q,'%')) or :q is null)")
    List<Profissional> busca(@Param("q") String q);
}