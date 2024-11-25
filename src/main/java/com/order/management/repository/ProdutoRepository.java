package com.order.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.management.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}

