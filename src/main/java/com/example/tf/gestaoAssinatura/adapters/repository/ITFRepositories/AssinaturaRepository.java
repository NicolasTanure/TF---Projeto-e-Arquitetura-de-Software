package com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Assinatura;

import java.time.LocalDate;
import java.util.List;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByFimVigenciaAfter(LocalDate data);
    List<Assinatura> findByFimVigenciaBefore(LocalDate data);

    List<Assinatura> findByClienteCodigo(Long clienteId);


    List<Assinatura> findByAplicativoCodigo(Long aplicativoId);
}
