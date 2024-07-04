package com.br.passagens.service;

import org.springframework.stereotype.Service;

import com.br.passagens.entity.Cadeira;
import com.br.passagens.entity.Usuario;
import com.br.passagens.repository.CadeiraRepository;

import java.util.List;

@Service
public class CadeiraService {

    private final CadeiraRepository cadeiraRepository;

    public CadeiraService(CadeiraRepository cadeiraRepository) {
        this.cadeiraRepository = cadeiraRepository;
    }

    public List<Cadeira> listarCadeiras() {
        return cadeiraRepository.findAll();
    }

    public Cadeira buscarCadeiraPorId(Long id) {
        return cadeiraRepository.findById(id).orElse(null);
    }

    public Cadeira salvarCadeira(Cadeira cadeira) {
        return cadeiraRepository.save(cadeira);
    }

    public void deletarCadeira(Long id) {
        cadeiraRepository.deleteById(id);
    }

    public Cadeira reservarCadeira(Long cadeiraId, Usuario usuarioId) {
        Cadeira cadeira = buscarCadeiraPorId(cadeiraId);
        if (cadeira != null && "vago".equals(cadeira.getStatus())) {
            cadeira.setStatus("ocupado");
            System.out.println("passou aqui");
            cadeira.setUsuario(usuarioId);
            return cadeiraRepository.save(cadeira);
        }
        return null;
    }

    public Cadeira liberarCadeira(Long cadeiraId) {
        Cadeira cadeira = buscarCadeiraPorId(cadeiraId);
        if (cadeira != null && "ocupado".equals(cadeira.getStatus())) {
            cadeira.setStatus("vago");
            cadeira.setUsuario(null);
            cadeiraRepository.save(cadeira);
            return cadeiraRepository.save(cadeira);
        }
        return null;
    }

    public List<Cadeira> buscarCadeirasPorOnibusId(Long onibusId) {
        return cadeiraRepository.findByOnibusIdOrderByNum(onibusId);
    }
}
