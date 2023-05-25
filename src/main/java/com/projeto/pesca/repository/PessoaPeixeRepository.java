package com.projeto.pesca.repository;

import com.projeto.pesca.domain.PessoaPeixe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaPeixeRepository extends JpaRepository<PessoaPeixe, Long> {
    PessoaPeixeRepository findPeixeById(Long peixeId);
}
