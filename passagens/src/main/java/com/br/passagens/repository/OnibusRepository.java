package com.br.passagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.passagens.entity.Onibus;

@Repository
public interface OnibusRepository extends JpaRepository<Onibus, Long> {
}
