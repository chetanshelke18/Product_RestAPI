package com.jbk.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.jbk.dao.SupplierDao;
import com.jbk.entity.Supplier;
import com.jbk.service.SupplierService;

public class SupplierServiceIMPL implements SupplierService{
	
@Autowired	
private SupplierDao dao;

@Override
public boolean saveSupplier(Supplier supplier) {
	String supplierId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
	supplier.setSupplierId(supplierId);
	boolean added = dao.saveSupplier(supplier);
	return added;
}

@Override
public Supplier getSupplierById(String supplierId) {
	
	return null;
}

@Override
public List<Supplier> getAllSuppliers() {
	
	return null;
}

@Override
public boolean deleteSupplierById(String supplierId) {
	
	return false;
}

@Override
public boolean updateSupplier(Supplier supplier) {
	
	return false;
}
}
