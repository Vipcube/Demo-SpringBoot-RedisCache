package org.vipcube.spring.redis.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vipcube.spring.redis.entity.Product;
import org.vipcube.spring.redis.service.IProductService;

@RestController
@RequestMapping( "/products" )
public class ProductController {
	private final IProductService service;

	public ProductController( IProductService service ){
		this.service = service;
	}

	@GetMapping( value = "", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok( this.service.findAll() );
	}

	@GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findById( @PathVariable( "id" ) Long id ){
		return ResponseEntity.ok( this.service.findById( id ) );
	}

	@PostMapping( value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> create( @RequestBody Product product ){
		return ResponseEntity.ok( this.service.create( product ) );
	}

	@PutMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> update( @PathVariable( "id" ) Long id, @RequestBody Product product ){
		return ResponseEntity.ok( this.service.update( id, product ) );
	}
}
