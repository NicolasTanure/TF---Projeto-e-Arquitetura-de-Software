package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Assinatura;
import com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories.AssinaturaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    // Criar assinatura com 7 dias grátis
    public Assinatura criarAssinatura(Assinatura assinatura) {
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7));
        return assinaturaRepository.save(assinatura);
    }

    // Atualizar assinatura com o pagamento
    public void processarPagamento(Long assinaturaId, double valorPago, String promocao) {
        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(assinaturaId);

        if (assinaturaOpt.isPresent()) {
            Assinatura assinatura = assinaturaOpt.get();

            LocalDate novaDataFim = calcularNovaDataValidade(assinatura, valorPago, promocao);
            assinatura.setFimVigencia(novaDataFim);

            assinaturaRepository.save(assinatura);
        } else {
            throw new RuntimeException("Assinatura não encontrada");
        }
    }

    // Lógica de cálculo de nova validade
    private LocalDate calcularNovaDataValidade(Assinatura assinatura, double valorPago, String promocao) {
        LocalDate novaValidade;
        if ("PROMO_30_45".equals(promocao)) {
            novaValidade = assinatura.getFimVigencia().plusDays(45); // Promoção de 45 dias
        } else {
            novaValidade = assinatura.getFimVigencia().plusDays(30); // Plano básico
        }
        return novaValidade;
    }

    // Verificar validade da assinatura
    public boolean isAssinaturaValida(Long assinaturaId) {
        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(assinaturaId);
        return assinaturaOpt.map(assinatura -> assinatura.getFimVigencia().isAfter(LocalDate.now())).orElse(false);
    }

    // Listar todas as assinaturas
    public List<Assinatura> listarAssinaturas() {
        return assinaturaRepository.findAll();
    }

    // Listar assinaturas por tipo (ATIVA ou CANCELADA)
    public List<Assinatura> listarAssinaturasPorTipo(String tipo) {
        LocalDate hoje = LocalDate.now();
        if (tipo.equalsIgnoreCase("ATIVAS")) {
            return assinaturaRepository.findByFimVigenciaAfter(hoje);
        } else if (tipo.equalsIgnoreCase("CANCELADAS")) {
            return assinaturaRepository.findByFimVigenciaBefore(hoje);
        }
        return listarAssinaturas(); // Se tipo for "TODAS"
    }

    public List<Assinatura> listarAssinaturasPorCliente(Long clienteId) {
        return assinaturaRepository.findByClienteCodigo(clienteId);
    }

    // Listar assinaturas de um aplicativo
    public List<Assinatura> listarAssinaturasPorAplicativo(Long aplicativoId) {
        return assinaturaRepository.findByAplicativoCodigo(aplicativoId);
    }
}
