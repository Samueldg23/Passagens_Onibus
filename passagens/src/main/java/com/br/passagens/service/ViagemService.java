package com.br.passagens.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.passagens.entity.Cadeira;
import com.br.passagens.entity.Onibus;
import com.br.passagens.entity.Usuario;
import com.br.passagens.entity.Viagem;
import com.br.passagens.repository.ViagemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {
    @Autowired
    private ViagemRepository viagemRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Viagem> listarViagens() {
        return viagemRepository.findAll();
    }

    public Viagem buscarViagemPorId(Long id) {
        return viagemRepository.findById(id).orElse(null);
    }

    public Viagem salvarViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public void deletarViagem(Long id) {
        viagemRepository.deleteById(id);
    }

    public Viagem atualizarViagem(Long id, Viagem viagemAtualizada) {
        return viagemRepository.findById(id).map(viagem -> {
            viagem.setLugarPartida(viagemAtualizada.getLugarPartida());
            viagem.setLugarDestino(viagemAtualizada.getLugarDestino());
            viagem.setHorarioPartida(viagemAtualizada.getHorarioPartida());
            viagem.setHorarioChegada(viagemAtualizada.getHorarioChegada());
            viagem.setValor(viagemAtualizada.getValor());
            viagem.setDescricao(viagemAtualizada.getDescricao());
            viagem.setOnibus(viagemAtualizada.getOnibus());
            return viagemRepository.save(viagem);
        }).orElse(null);
    }

    public Viagem reservarViagem(Long viagemId, Long usuarioId) {
        Viagem viagem = viagemRepository.findById(viagemId).orElse(null);
        if (viagem != null) {
            Onibus onibus = viagem.getOnibus();
            if (onibus != null) {
                Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
                if (usuario != null) {
                    Optional<Integer> vagaLivre = onibus.getCadeiras().stream()
                            .filter(cadeira -> "vago".equals(cadeira.getStatus()))
                            .findFirst()
                            .map(cadeira -> cadeira.getNum());

                    if (vagaLivre.isPresent()) {
                        Cadeira cadeira = onibus.getCadeiras().stream()
                                .filter(c -> c.getNum() == vagaLivre.get())
                                .findFirst()
                                .orElse(null);
                        if (cadeira != null) {
                            cadeira.setStatus("ocupado");
                            cadeira.setUsuario(usuario);
                            return viagemRepository.save(viagem);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Viagem cancelarReservaViagem(Long viagemId, Long usuarioId) {
        Viagem viagem = viagemRepository.findById(viagemId).orElse(null);
        if (viagem != null) {
            Onibus onibus = viagem.getOnibus();
            if (onibus != null) {
                Optional<Cadeira> cadeiraReservada = onibus.getCadeiras().stream()
                        .filter(cadeira -> "ocupado".equals(cadeira.getStatus()) && cadeira.getUsuario().getId().equals(usuarioId))
                        .findFirst();

                if (cadeiraReservada.isPresent()) {
                    Cadeira cadeira = cadeiraReservada.get();
                    cadeira.setStatus("vago");
                    cadeira.setUsuario(null);
                    return viagemRepository.save(viagem);
                }
            }
        }
        return null;
    }
}
