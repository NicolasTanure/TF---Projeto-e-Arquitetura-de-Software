package com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Aplicativo;

public interface AplicativoRepository extends JpaRepository<Aplicativo, Long> {
}
