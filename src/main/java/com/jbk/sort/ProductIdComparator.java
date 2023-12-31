package com.jbk.sort;

import java.util.Comparator;

import com.jbk.entity.Product;

;


public class ProductIdComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		
		return p1.getProductId().compareTo(p2.getProductId());
	}

}
