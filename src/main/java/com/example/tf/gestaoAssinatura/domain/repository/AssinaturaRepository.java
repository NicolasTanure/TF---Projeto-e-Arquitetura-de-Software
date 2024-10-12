package com.example.tf.gestaoAssinatura.domain.repository;



import com.example.tf.gestaoAssinatura.domain.model.Aplicativo;
import com.example.tf.gestaoAssinatura.domain.model.Assinatura;
import com.example.tf.gestaoAssinatura.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Assinatura findByClienteAndAplicativo(Cliente cliente, Aplicativo aplicativo);
}

