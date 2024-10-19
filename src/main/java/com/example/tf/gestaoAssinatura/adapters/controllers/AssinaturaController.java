package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Assinatura;
import com.example.tf.gestaoAssinatura.application.service.AssinaturaService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    // Criar assinatura
    @PostMapping
    public Assinatura criarAssinatura(@RequestBody Assinatura assinatura) {
        return assinaturaService.criarAssinatura(assinatura);
    }

    // Listar assinaturas por tipo
    @GetMapping("/{tipo}")
    public List<Assinatura> listarAssinaturas(@PathVariable String tipo) {
        return assinaturaService.listarAssinaturasPorTipo(tipo);
    }


    @GetMapping("/assinvalida/{codass}")
    public boolean isAssinaturaValida(@PathVariable Long codass) {
        return assinaturaService.isAssinaturaValida(codass);
    }

    // Registrar pagamento
    @PostMapping("/registrarpagamento")
    public void processarPagamento(@RequestParam Long codass, @RequestParam double valorPago, @RequestParam String promocao) {
        assinaturaService.processarPagamento(codass, valorPago, promocao);
    }

    @GetMapping("/asscli/{codcli}")
    public List<Assinatura> listarAssinaturasCliente(@PathVariable Long codcli) {
        return assinaturaService.listarAssinaturasPorCliente(codcli);
    }

    // Listar assinaturas de um aplicativo
    @GetMapping("/assapp/{codapp}")
    public List<Assinatura> listarAssinaturasAplicativo(@PathVariable Long codapp) {
        return assinaturaService.listarAssinaturasPorAplicativo(codapp);
    }
}
