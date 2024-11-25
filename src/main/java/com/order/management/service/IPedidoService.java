package com.order.management.service;

import java.util.Optional;

import com.order.management.entity.Pedido;

public interface IPedidoService {
	public Pedido processarPedido(Pedido pedido);
	Optional<Pedido> buscarPedidoPorId(Long id);
}
