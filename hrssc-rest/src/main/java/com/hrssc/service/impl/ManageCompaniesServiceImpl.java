package com.hrssc.service.impl;

import com.hrssc.entities.TemporaryInfo;
import com.hrssc.repository.TemporaryInfoRepository;
import com.hrssc.service.ManageCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("manageCompaniesService")
public class ManageCompaniesServiceImpl implements ManageCompaniesService {

    @Autowired
    TemporaryInfoRepository tempInfoRepo;
    @Override
    public List<TemporaryInfo> loadAllRequest() {
       return tempInfoRepo.findAll();
    }
}
