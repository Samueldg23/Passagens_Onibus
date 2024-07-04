package com.br.passagens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.passagens.entity.Cadeira;
import com.br.passagens.entity.Onibus;
import com.br.passagens.service.OnibusService;
import com.br.passagens.service.CadeiraService;

import java.util.List;

@RestController
@RequestMapping("/onibus")
public class OnibusController {

    @Autowired
    private OnibusService onibusService;

    @Autowired
    private CadeiraService cadeiraService;

    @GetMapping
    public ResponseEntity<List<Onibus>> obterTodosOnibus() {
        List<Onibus> onibus = onibusService.listarOnibus();
        return ResponseEntity.ok(onibus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Onibus> obterOnibusPorId(@PathVariable Long id) {
        Onibus onibus = onibusService.buscarOnibusPorId(id);
        return onibus != null ? ResponseEntity.ok(onibus) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/cadeiras")
    public ResponseEntity<List<Cadeira>> obterCadeirasPorOnibus(@PathVariable Long id) {
        Onibus onibus = onibusService.buscarOnibusPorId(id);
        if (onibus != null) {
            List<Cadeira> cadeiras = cadeiraService.buscarCadeirasPorOnibusId(id);
            return ResponseEntity.ok(cadeiras);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Onibus> criarOnibus(@RequestBody Onibus onibus) {
        Onibus novoOnibus = onibusService.salvarOnibus(onibus);
        return ResponseEntity.ok(novoOnibus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Onibus> atualizarOnibus(@PathVariable Long id, @RequestBody Onibus onibusAtualizado) {
        Onibus onibus = onibusService.atualizarOnibus(id, onibusAtualizado);
        return onibus != null ? ResponseEntity.ok(onibus) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOnibus(@PathVariable Long id) {
        onibusService.deletarOnibus(id);
        return ResponseEntity.noContent().build();
    }
}
