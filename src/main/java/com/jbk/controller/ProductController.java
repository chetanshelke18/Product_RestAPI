package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Product;
import com.jbk.service.ProductService;


@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService service;	
	@PostMapping(value = "/save-product")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product){
	
	boolean isAdded=service.saveProduct(product);
	if(isAdded) {
		return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
	}else {
		
		return new ResponseEntity<Boolean>(isAdded, HttpStatus.ALREADY_REPORTED);
	}
	}     
	
	@GetMapping(value= "/get-product-by-id/{productId}")
	public ResponseEntity<Product>getProductById(@PathVariable String productId){
		
		Product product  =service.getProductById(productId);
		if(product!=null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}else {
			
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@DeleteMapping(value ="/delete-product/")
	public ResponseEntity<Boolean>deleteProduct(@RequestParam String productId){
		boolean isDeleted = service.deleteProductById(productId);
		if(isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
			
		}
	}
	
	@PutMapping(value ="/update-product")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product){
		boolean isUpdated = service.updateProduct(product);
		if(isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(isUpdated,HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get-allproducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product>products=service.getAllProducts();
		if(products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
		
		
	}
}
 