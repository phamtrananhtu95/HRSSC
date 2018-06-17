package com.hrssc.service;

import com.hrssc.entities.TemporaryInfo;

import java.util.List;

public interface ManageCompaniesService {

    List<TemporaryInfo> loadAllRequest();
    boolean saveCompany(int tempInfoId);
    boolean acceptCompany(int tempInfoId);
    boolean removeTempInfo(int id);

}
