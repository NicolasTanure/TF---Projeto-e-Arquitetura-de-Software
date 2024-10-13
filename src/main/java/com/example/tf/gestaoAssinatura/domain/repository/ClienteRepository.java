package com.example.tf.gestaoAssinatura.domain.repository;

import com.example.tf.gestaoAssinatura.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
