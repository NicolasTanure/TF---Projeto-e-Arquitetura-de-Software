package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IAssinaturaRepository;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssinaturaService {

    private final IAssinaturaRepository assinaturaRepository;

    public AssinaturaService(IAssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    // Criar assinatura com 7 dias grátis
    public AssinaturaModel criarAssinatura(AssinaturaModel assinatura) {
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7));
        return assinaturaRepository.save(assinatura);
    }

    // Atualizar assinatura com o pagamento
    public void processarPagamento(Long assinaturaId, double valorPago, String promocao) {
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findById(assinaturaId);

        if (assinaturaOpt.isPresent()) {
            AssinaturaModel assinatura = assinaturaOpt.get();

            LocalDate novaDataFim = calcularNovaDataValidade(assinatura, valorPago, promocao);
            assinatura.setFimVigencia(novaDataFim);

            assinaturaRepository.save(assinatura);
        } else {
            throw new RuntimeException("Assinatura não encontrada");
        }
    }

    // Lógica de cálculo de nova validade
    private LocalDate calcularNovaDataValidade(AssinaturaModel assinatura, double valorPago, String promocao) {
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
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findById(assinaturaId);
        return assinaturaOpt.map(assinatura -> assinatura.getFimVigencia().isAfter(LocalDate.now())).orElse(false);
    }

    // Listar todas as assinaturas
    public List<AssinaturaModel> listarAssinaturas() {
        return assinaturaRepository.findAll();
    }

    // Listar assinaturas por tipo (ATIVA ou CANCELADA)
    public List<AssinaturaModel> listarAssinaturasPorTipo(String tipo) {
        LocalDate hoje = LocalDate.now();
        if (tipo.equalsIgnoreCase("ATIVAS")) {
            return assinaturaRepository.findByFimVigenciaAfter(hoje);
        } else if (tipo.equalsIgnoreCase("CANCELADAS")) {
            return assinaturaRepository.findByFimVigenciaBefore(hoje);
        }
        return listarAssinaturas(); // Se tipo for "TODAS"
    }

    public List<AssinaturaModel> listarAssinaturasPorCliente(Long clienteId) {
        return assinaturaRepository.findByClienteCodigo(clienteId);
    }

    // Listar assinaturas de um aplicativo
    public List<AssinaturaModel> listarAssinaturasPorAplicativo(Long aplicativoId) {
        return assinaturaRepository.findByAplicativoCodigo(aplicativoId);
    }
}
