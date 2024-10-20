package com.example.tf.gestaoAssinatura.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.tf.gestaoAssinatura.adapters.dto.RespostaPagamentoDTO;
import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Assinatura;
import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IAssinaturaRepository;
import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IPagamentoRepository;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;
import com.example.tf.gestaoAssinatura.domain.model.PagamentoModel;

import jakarta.transaction.Transactional;

public class PagamentoService {
    private IAssinaturaRepository assiRepo;
    private IPagamentoRepository pagaRepo;
    
    @Autowired
    public PagamentoService(IAssinaturaRepository assiRepo,IPagamentoRepository pagaRepo) {
        this.assiRepo = assiRepo;
        this.pagaRepo = pagaRepo;
    }

    @Transactional
    public RespostaPagamentoDTO fazPagamento(LocalDate dataPagamento, Long codigo, Double valor) {
        Optional<AssinaturaModel> assinaturaOpc = assiRepo.findById(codigo);
        if (assinaturaOpc.isEmpty()) {
            return RespostaPagamentoDTO.builder()
                    .data(dataPagamento)
                    .valor(valor)
                    .build();
        }

        AssinaturaModel assinatura = assinaturaOpc.get();
        Double valorDif = valor - assinatura.getAplicativo().getCustoMensal();

        if ( valorDif < 0) {
            return RespostaPagamentoDTO.builder()
                    .data(dataPagamento)
                    .valor(valor)
                    .build();
        }

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setDataPagamento(dataPagamento);
        pagamento.setAssinatura(assinatura);
        pagamento.setValorPago(valor);

        pagaRepo.save(pagamento);

        LocalDate fimVigenciaAtual = assinatura.getFimVigencia();

    LocalDate novaFimVigencia;
    if (fimVigenciaAtual.isBefore(dataPagamento)) {
        novaFimVigencia = dataPagamento.plusMonths(1);
    } else {
        novaFimVigencia = fimVigenciaAtual.plusMonths(1);
    }
    // Retorna a resposta com status de pagamento bem-sucedido e a nova data de fim de vigência
    return RespostaPagamentoDTO.builder()
            .data(novaFimVigencia)
            .valor(valorDif) // Se precisar retornar o valor pago, mude isso
            .build();
    }
}
