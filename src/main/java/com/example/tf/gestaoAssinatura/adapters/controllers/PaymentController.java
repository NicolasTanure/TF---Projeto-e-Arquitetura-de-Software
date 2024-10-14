package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.application.service.AssinaturaService;
import com.example.tf.gestaoAssinatura.adapters.dto.PaymentRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrarpagamento")
public class PaymentController {

    private final AssinaturaService assinaturaService;

    public PaymentController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<?> registerPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            assinaturaService.processarPagamento(
                    paymentRequest.getAssinaturaId(),
                    paymentRequest.getValorPago(),
                    paymentRequest.getPromocao()
            );
            return ResponseEntity.ok("Pagamento registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar pagamento: " + e.getMessage());
        }
    }
}