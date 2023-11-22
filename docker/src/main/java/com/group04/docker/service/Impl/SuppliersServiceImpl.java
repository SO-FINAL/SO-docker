package com.group04.docker.service.Impl;

import com.group04.docker.model.Suppliers;
import com.group04.docker.repository.SuppliersRepository;
import com.group04.docker.service.SuppliersService;
import com.group04.docker.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersServiceImpl implements SuppliersService {
    @Autowired
    private SuppliersRepository suppliersRepository;

    @Override
    public Suppliers createSupplier(Suppliers supplier) {
        validateSupplier(supplier);
        return suppliersRepository.save(supplier);
    }

    @Override
    public Suppliers getSupplierById(String id) {
        existsById(id);
        return suppliersRepository.findById(id).orElse(null);
    }

    @Override
    public Suppliers updateSupplier(String id, Suppliers supplier) {
        existsById(id);
        validateSupplier(supplier);
        return suppliersRepository.findById(id)
                .map(existingSupplier -> {
                    existingSupplier.setCompanyName(supplier.getCompanyName());
                    existingSupplier.setContactName(supplier.getContactName());
                    existingSupplier.setPhone(supplier.getPhone());
                    existingSupplier.setBussinessPage(supplier.getBussinessPage());
                    return suppliersRepository.save(existingSupplier);
                })
                .orElse(null);
    }

    @Override
    public void deleteSupplier(String id) {
        existsById(id);
        suppliersRepository.deleteById(id);
    }

    @Override
    public List<Suppliers> getAllSuppliers() {
        return suppliersRepository.findAll();
    }

    private void validateSupplier(Suppliers supplier) {
        if (supplier.getCompanyName() == null || supplier.getCompanyName().isEmpty()) {
            throw new ValidationException("Company name is required");
        }
        if (supplier.getContactName() == null || supplier.getContactName().isEmpty()) {
            throw new ValidationException("Contact name is required");
        }
        if (supplier.getPhone() == null || supplier.getPhone().isEmpty()) {
            throw new ValidationException("Phone is required");
        }
        if (supplier.getBussinessPage() == null || supplier.getBussinessPage().isEmpty()) {
            throw new ValidationException("Bussiness page is required");
        }
    }

    private void existsById(String id) {
        if (!suppliersRepository.existsById(id)) {
            throw new ValidationException("Supplier with id " + id + " does not exist");
        }
    }


}