package com.br.passagens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.passagens.entity.Cadeira;
import com.br.passagens.entity.Usuario;
import com.br.passagens.entity.Viagem;
import com.br.passagens.service.CadeiraService;
import com.br.passagens.service.UsuarioService;
import com.br.passagens.service.ViagemService;

import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @Autowired
    private CadeiraService cadeiraService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Viagem>> obterTodasViagens() {
        List<Viagem> viagens = viagemService.listarViagens();
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> obterViagemPorId(@PathVariable Long id) {
        Viagem viagem = viagemService.buscarViagemPorId(id);
        return viagem != null ? ResponseEntity.ok(viagem) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Viagem> criarViagem(@RequestBody Viagem viagem) {
        Viagem novaViagem = viagemService.salvarViagem(viagem);
        return ResponseEntity.ok(novaViagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizarViagem(@PathVariable Long id, @RequestBody Viagem viagemAtualizada) {
        Viagem viagem = viagemService.atualizarViagem(id, viagemAtualizada);
        return viagem != null ? ResponseEntity.ok(viagem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarViagem(@PathVariable Long id) {
        viagemService.deletarViagem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cadeiraId}/reservar/{usuarioId}")
    public ResponseEntity<Cadeira> reservarViagem(@PathVariable Long cadeiraId, @PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        Cadeira cadeira = cadeiraService.buscarCadeiraPorId(cadeiraId);
        if (cadeira == null) {
            return ResponseEntity.badRequest().build();
        }

        cadeiraService.reservarCadeira(cadeira.getId(), usuario);
        return ResponseEntity.ok(cadeira);
    }

    @PutMapping("/cadeira/{cadeiraId}")
    public ResponseEntity<Cadeira> cancelarReservaCadeira(@PathVariable Long cadeiraId) {
        Cadeira cadeira =  cadeiraService.liberarCadeira(cadeiraId);
        return cadeira != null ? ResponseEntity.ok(cadeira) : ResponseEntity.badRequest().build();
    }
}
