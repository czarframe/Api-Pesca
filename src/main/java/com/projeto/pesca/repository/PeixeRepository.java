package com.projeto.pesca.repository;

import com.projeto.pesca.domain.Peixe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeixeRepository extends JpaRepository<Peixe, Long> {
}
