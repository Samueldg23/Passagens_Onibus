package com.br.passagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.passagens.entity.Cadeira;
import java.util.List;


@Repository
public interface CadeiraRepository extends JpaRepository<Cadeira, Long> {
    List<Cadeira> findByOnibusIdOrderByNum(Long idOnibus);
}
