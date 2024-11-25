package com.order.management.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.management.entity.Pedido;
import com.order.management.entity.Produto;
import com.order.management.entity.StatusPedido;
import com.order.management.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService  implements IPedidoService{

	private final PedidoRepository pedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@Transactional
	public Pedido processarPedido(Pedido pedido) {
		if (isPedidoDuplicado(pedido)) {
			throw new RuntimeException("Pedido duplicado!");
		}

		pedido.setValorTotal(pedido.getProdutos().stream().mapToDouble(Produto::getValor).sum());
		if (pedido.getId() != null) {
			// Atualizar um pedido existente
			Pedido existente = pedidoRepository.findById(pedido.getId())
					.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
			existente.setStatus(StatusPedido.PROCESSADO);

			existente.setProdutos(pedido.getProdutos());
			existente.getProdutos().forEach(produto -> produto.setPedido(existente));
			return pedidoRepository.save(existente);
		} else {
			// Criar um novo pedido
			//pedido.setVersion(0);
			pedido.setStatus(StatusPedido.NOVO);
			return pedidoRepository.save(pedido);
		}
	}

	private boolean isPedidoDuplicado(Pedido pedido) {
		if (pedido == null) {
			throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
	    }
		if(pedido.getId() == null) return false;
		return pedidoRepository.findById(pedido.getId()).isPresent();
	}

	public Optional<Pedido> buscarPedidoPorId(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("O ID do pedido não pode ser nulo.");
	    }
		return Optional.ofNullable(
				pedidoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado")));
	}

	
}
