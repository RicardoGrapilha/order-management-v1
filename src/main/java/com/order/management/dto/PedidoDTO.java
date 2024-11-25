package com.order.management.dto;

import java.time.LocalDateTime;
import java.util.List;


public class PedidoDTO {

    private Long id;
    private Double valorTotal;
    private StatusPedidoDTO status;
    private List<ProdutoDTO> produtos;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    // Getters e Setters
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPedidoDTO getStatus() {
		return status;
	}

	public void setStatus(StatusPedidoDTO status) {
		this.status = status;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
