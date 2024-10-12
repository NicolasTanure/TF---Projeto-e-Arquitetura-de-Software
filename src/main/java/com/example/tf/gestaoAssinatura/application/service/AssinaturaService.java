package com.example.tf.gestaoAssinatura.application.service;



import com.example.tf.gestaoAssinatura.domain.model.Assinatura;
import com.example.tf.gestaoAssinatura.domain.repository.AssinaturaRepository;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaService {
    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    public Assinatura salvar(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public boolean isAssinaturaValida(Long clienteId, Long aplicativoId) {
        // Lógica para verificar se a assinatura é válida
        return true;
    }

}

