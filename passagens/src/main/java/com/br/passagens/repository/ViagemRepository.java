package com.br.passagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.passagens.entity.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

}
