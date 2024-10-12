package com.example.tf.gestaoAssinatura.adapters.controllers;


import com.example.tf.gestaoAssinatura.application.service.AplicativoService;
import com.example.tf.gestaoAssinatura.domain.model.Aplicativo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aplicativos")
public class AplicativoController {
    private final AplicativoService aplicativoService;

    public AplicativoController(AplicativoService aplicativoService) {
        this.aplicativoService = aplicativoService;
    }

    @PostMapping
    public ResponseEntity<Aplicativo> cadastrar(@RequestBody Aplicativo aplicativo) {
        Aplicativo novoAplicativo = aplicativoService.salvar(aplicativo);
        return ResponseEntity.ok(novoAplicativo);
    }

    @GetMapping
    public ResponseEntity<List<Aplicativo>> listar() {
        List<Aplicativo> aplicativos = aplicativoService.listar();
        return ResponseEntity.ok(aplicativos);
    }

    // Outros endpoints, como atualizar e remover
}

