package com.example.tf.gestaoAssinatura.application.service;


import com.example.tf.gestaoAssinatura.domain.model.Aplicativo;
import com.example.tf.gestaoAssinatura.domain.repository.AplicativoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicativoService {
    private final AplicativoRepository aplicativoRepository;

    public AplicativoService(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    public Aplicativo salvar(Aplicativo aplicativo) {
        return aplicativoRepository.save(aplicativo);
    }

    public List<Aplicativo> listar() {
        return aplicativoRepository.findAll();
    }


}

