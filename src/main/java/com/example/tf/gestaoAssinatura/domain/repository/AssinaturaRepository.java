package com.example.tf.gestaoAssinatura.domain.repository;

import com.example.tf.gestaoAssinatura.domain.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByFimVigenciaAfter(LocalDate data);
    List<Assinatura> findByFimVigenciaBefore(LocalDate data);

    List<Assinatura> findByClienteCodigo(Long clienteId);


    List<Assinatura> findByAplicativoCodigo(Long aplicativoId);
}
