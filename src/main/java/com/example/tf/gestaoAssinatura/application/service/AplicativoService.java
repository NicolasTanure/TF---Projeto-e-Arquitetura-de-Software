package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.domain.model.Aplicativo;
import com.example.tf.gestaoAssinatura.domain.repository.AplicativoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AplicativoService {

    private final AplicativoRepository aplicativoRepository;

    public AplicativoService(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    // Listar todos os aplicativos
    public List<Aplicativo> listarAplicativos() {
        return aplicativoRepository.findAll();
    }

    // Atualizar o custo mensal de um aplicativo
    public Aplicativo atualizarCustoMensal(Long idAplicativo, Double novoCusto) {
        Optional<Aplicativo> aplicativoOpt = aplicativoRepository.findById(idAplicativo);
        if (aplicativoOpt.isPresent()) {
            Aplicativo aplicativo = aplicativoOpt.get();
            aplicativo.setCustoMensal(novoCusto);
            return aplicativoRepository.save(aplicativo);
        } else {
            throw new RuntimeException("Aplicativo não encontrado");
        }
    }
}
