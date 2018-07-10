package com.hrssc.service;

import com.hrssc.entities.Company;
import com.hrssc.entities.TemporaryInfo;

import java.util.List;

public interface CompaniesManagementService {

    List<TemporaryInfo> loadAllRequest();
    void saveCompany(int tempInfoId);
    boolean acceptCompany(int tempInfoId);
    void removeTempInfo(int id);
    List<Company> getCompanyList();

}
