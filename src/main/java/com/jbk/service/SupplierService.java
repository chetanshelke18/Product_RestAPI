package com.jbk.service;

import java.util.List;
import com.jbk.entity.Supplier;

public interface SupplierService {
	public boolean saveSupplier(Supplier supplier);
	public Supplier getSupplierById(String supplierId);
	public List<Supplier> getAllSuppliers();
	public boolean deleteSupplierById(String supplierId);
	public boolean updateSupplier(Supplier supplier); 
	

}
