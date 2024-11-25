package com.order.management.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.order.management.dto.PedidoDTO;
import com.order.management.entity.Pedido;
import com.order.management.service.IPedidoService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
    private ModelMapper modelMapper;
	
    @Autowired
    private IPedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid PedidoDTO pedidoDTO) throws ParseException {
    	Pedido pedido = convertToEntity(pedidoDTO);
        Pedido processado = pedidoService.processarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(processado);
    }

    @GetMapping( "/{pedidoId}" )
    public ResponseEntity<Pedido> buscarPedido(@PathVariable("pedidoId") Long pedidoId) {
    	System.out.println("id:"+pedidoId);
        return pedidoService.buscarPedidoPorId(pedidoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping
    public ResponseEntity<Pedido> criarPedido(@PathVariable("pedidoId") Long pedidoId
    		, @RequestBody @Valid PedidoDTO pedidoDTO) throws ParseException {
    	Pedido pedido = convertToEntity(pedidoDTO);
        Pedido processado = pedidoService.processarPedido(pedido);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(processado);
    }
    
	private Pedido convertToEntity(PedidoDTO pedidoDto) throws ParseException {
    	Pedido pedido = modelMapper.map(pedidoDto, Pedido.class);
       
        return pedido;
    }
}
