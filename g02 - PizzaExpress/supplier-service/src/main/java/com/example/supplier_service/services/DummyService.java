package com.example.supplier_service.services;

import com.example.supplier_service.models.DummyModel;

import java.util.List;

public interface DummyService {

    DummyModel getDummy(Long id);
    List<DummyModel> getDummyList();
    DummyModel createDummy(DummyModel dummyModel);
    DummyModel updateDummy(DummyModel dummyModel);
    Void deleteDummy(DummyModel dummyModel);
}
