package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.application.service.AplicativoService;
import com.example.tf.gestaoAssinatura.domain.model.Aplicativo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad/aplicativos")
public class AplicativoController {

    private final AplicativoService aplicativoService;

    public AplicativoController(AplicativoService aplicativoService) {
        this.aplicativoService = aplicativoService;
    }

    // Listar todos os aplicativos
    @GetMapping
    public List<Aplicativo> listarAplicativos() {
        return aplicativoService.listarAplicativos();
    }

    // Atualizar o custo mensal do aplicativo
    @PostMapping("/atualizacusto/{idAplicativo}")
    public ResponseEntity<Aplicativo> atualizarCustoMensal(@PathVariable Long idAplicativo, @RequestBody Aplicativo aplicativoAtualizado) {
        Aplicativo atualizado = aplicativoService.atualizarCustoMensal(idAplicativo, aplicativoAtualizado.getCustoMensal());
        return ResponseEntity.ok(atualizado);
    }
}
