package com.br.passagens.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.passagens.entity.Cadeira;
import com.br.passagens.entity.Onibus;
import com.br.passagens.repository.CadeiraRepository;
import com.br.passagens.repository.OnibusRepository;

import java.util.List;

@Service
public class OnibusService {
    @Autowired
    private OnibusRepository onibusRepository;
    @Autowired
    private CadeiraRepository cadeiraRepository;

    public List<Onibus> listarOnibus() {
        return onibusRepository.findAll();
    }

    public Onibus buscarOnibusPorId(Long id) {
        return onibusRepository.findById(id).orElse(null);
    }

    public Onibus salvarOnibus(Onibus onibus) {
        Onibus onibusSalvo = onibusRepository.save(onibus);
        for (int i = 1; i <= onibus.getQuantidadeCadeira(); i++) {
            Cadeira cadeira = new Cadeira(null, i, "vago", null, onibusSalvo);
            cadeiraRepository.save(cadeira);
        }
        return onibusSalvo;
    }

    public void deletarOnibus(Long id) {
        onibusRepository.deleteById(id);
    }

    public Onibus atualizarOnibus(Long id, Onibus onibusAtualizado) {
        Onibus onibusExistente = buscarOnibusPorId(id);
        if (onibusExistente != null) {
            onibusAtualizado.setId(id);
            return onibusRepository.save(onibusAtualizado);
        }
        return null;
    }
}
