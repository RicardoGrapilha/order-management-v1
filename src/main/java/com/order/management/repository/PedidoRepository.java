package com.order.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.management.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findById(Long id);
}