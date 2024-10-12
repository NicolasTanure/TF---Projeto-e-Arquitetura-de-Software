package com.example.tf.gestaoAssinatura.adapters.controllers;


import com.example.tf.gestaoAssinatura.application.service.AssinaturaService;
import com.example.tf.gestaoAssinatura.domain.model.Assinatura;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {
    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<Assinatura> cadastrar(@RequestBody Assinatura assinatura) {
        Assinatura novaAssinatura = assinaturaService.salvar(assinatura);
        return ResponseEntity.ok(novaAssinatura);
    }

    @GetMapping("/validade")
    public ResponseEntity<Boolean> verificarValidade(@RequestParam Long clienteId, @RequestParam Long aplicativoId) {
        boolean isValida = assinaturaService.isAssinaturaValida(clienteId, aplicativoId);
        return ResponseEntity.ok(isValida);
    }
}
