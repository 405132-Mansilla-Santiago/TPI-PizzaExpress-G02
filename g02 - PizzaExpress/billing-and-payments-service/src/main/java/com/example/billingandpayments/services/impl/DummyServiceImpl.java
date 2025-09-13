package com.example.billingandpayments.services.impl;

import com.example.billingandpayments.models.DummyModel;
import com.example.billingandpayments.repositories.DummyRepository;
import com.example.billingandpayments.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyServiceImpl implements DummyService {

    @Autowired
    private DummyRepository dummyRepository;

    @Override
    public DummyModel getDummy(Long id) {
        return null;
    }

    @Override
    public List<DummyModel> getDummyList() {
        return null;
    }

    @Override
    public DummyModel createDummy(DummyModel dummyModel) {
        return null;
    }

    @Override
    public DummyModel updateDummy(DummyModel dummyModel) {
        return null;
    }

    @Override
    public Void deleteDummy(DummyModel dummyModel) {
        return null;
    }
}
