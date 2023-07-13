package com.jbk.service;

import java.util.List;
import com.jbk.entity.Product;

public interface ProductService {
	public boolean saveProduct(Product product);
	public Product getProductById(String productId);
	public List<Product> getAllProducts();
	public boolean deleteProductById(String productId);
	public boolean updateProduct(Product product); 
	public List<Product> getMaxPriceProduct();
	public List<Product> sortProductById_ASC();
	public List<Product> sortProductById_DESC();
	public double getMaxPrice();
	public double countSumOfProductPrice();
	public int getTotalCountOfProducts();

}
 