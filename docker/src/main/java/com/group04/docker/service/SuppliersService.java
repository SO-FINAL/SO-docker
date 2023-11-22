package com.group04.docker.service;

import com.group04.docker.model.Suppliers;

import java.util.List;

public interface SuppliersService {
    Suppliers createSupplier(Suppliers supplier);
    Suppliers getSupplierById(String id);
    Suppliers updateSupplier(String id, Suppliers supplier);
    void deleteSupplier(String id);
    List<Suppliers> getAllSuppliers();
}