package com.hrssc.service.impl;

import com.hrssc.entities.Company;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.entities.User;
import com.hrssc.model.CompanyStatus;
import com.hrssc.model.UserStatus;
import com.hrssc.repository.CompanyRepository;
import com.hrssc.repository.TemporaryInfoRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.ManageCompaniesService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("manageCompaniesService")
public class ManageCompaniesServiceImpl implements ManageCompaniesService {

    @Autowired
    TemporaryInfoRepository tempInfoRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CompanyRepository companyRepo;

    @Override
    public List<TemporaryInfo> loadAllRequest() {
       return tempInfoRepo.findAll();
    }



    public void saveCompany(int tempInfoId){
        TemporaryInfo temp = tempInfoRepo.findById(tempInfoId);

        Company company = new Company();
        company.setName(temp.getCompanyName());
        company.setAddress(temp.getCompanyAddress());
        company.setCity(temp.getCompanyCity());
        company.setStatus(CompanyStatus.ACTIVATED);
        company.setEmail(temp.getCompanyEmail());
        company.setTel(temp.getCompanyTel());
        companyRepo.save(company);
        company = companyRepo.findByName(temp.getCompanyName());


        User user = new User();
        user.setUsername(temp.getCompanyEmail());
        user.setEmail(temp.getRepresenttativeEmail());
        user.setFullname(temp.getRepresentativeName());
        user.setFirstLogin(true);

//        String rawPassword = randomPassword(); // Use this to notify user via email after this
//        System.out.println("password: "+rawPassword);
        String rawPassword = "12345";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        user.setCompanyId(company.getId());
        user.setRoleId(2);
        user.setTel(temp.getRepresentativeTel());
        user.setStatus(UserStatus.ACTIVATED);
        userRepo.save(user);
    }

    private static String randomPassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random( 6, characters );
    }

}
