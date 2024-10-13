package com.example.tf.gestaoAssinatura.application.service;



import com.example.tf.gestaoAssinatura.domain.model.Cliente;
import com.example.tf.gestaoAssinatura.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}

