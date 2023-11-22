package com.group04.docker.controller;

import com.group04.docker.dto.SupplierRequest;
import com.group04.docker.dto.mapper.SupplierMapper;
import com.group04.docker.model.Suppliers;
import com.group04.docker.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@CrossOrigin(origins = "*")
public class SuppliersController {
    @Autowired
    private SuppliersService suppliersService;

    @PostMapping
    public Suppliers createSupplier(@RequestBody SupplierRequest supplierRequest) {
        var supplier = SupplierMapper.INSTANCE.supplierRequestToSupplier(supplierRequest);
        return suppliersService.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public Suppliers getSupplierById(@PathVariable String id) {
        return suppliersService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Suppliers updateSupplier(@PathVariable String id, @RequestBody SupplierRequest supplierRequest) {
        var supplier = SupplierMapper.INSTANCE.supplierRequestToSupplier(supplierRequest);
        return suppliersService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable String id) {
        suppliersService.deleteSupplier(id);
    }

    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        return suppliersService.getAllSuppliers();
    }
}