package com.example.tf.gestaoAssinatura.domain.repository;


import com.example.tf.gestaoAssinatura.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}

