package com.example.tf.gestaoAssinatura.domain.repository;

import com.example.tf.gestaoAssinatura.domain.model.Aplicativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AplicativoRepository extends JpaRepository<Aplicativo, Long> {
}
