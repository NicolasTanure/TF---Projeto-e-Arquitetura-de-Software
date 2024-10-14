package com.example.tf.gestaoAssinatura.adapters.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long assinaturaId;
    private double valorPago;
    private String promocao;
}
