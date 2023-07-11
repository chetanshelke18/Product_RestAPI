package com.jbk.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.service.ProductService;

@Service
public class ProductServiceIMPL implements ProductService  {
	
	@Autowired
	private ProductDao dao;

	@Override
	public boolean saveProduct(Product product) {
		String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		product.setProductId(productId);
		boolean isAdded = dao.saveProduct(product);
		return isAdded;
	}

	@Override
	public Product getProductById(String productId) {
		
		return dao.getProductById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return dao.getAllProducts();
	
	}

	@Override
	public boolean deleteProductById(String productId) {
		
		return dao.deleteProductById(productId);
	}

	@Override
	public boolean updateProduct(Product product) {
		
		return dao.updateProduct(product);
	}
	

}
