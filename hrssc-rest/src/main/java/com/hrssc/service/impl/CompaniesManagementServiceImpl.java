package com.hrssc.service.impl;

import com.hrssc.entities.Company;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.entities.User;
import com.hrssc.domain.Constant;
import com.hrssc.repository.CompanyRepository;
import com.hrssc.repository.TemporaryInfoRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.CompaniesManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("manageCompaniesService")
public class CompaniesManagementServiceImpl implements CompaniesManagementService {

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

    @Transactional
    public boolean acceptCompany(int tempInfoId){
        try {
            saveCompany(tempInfoId);
            saveUser(tempInfoId);
            removeTempInfo(tempInfoId);
            return true;
        }catch (RuntimeException e){
            Logger.getLogger(CompaniesManagementServiceImpl.class.toString()).log(Level.INFO,e.toString());
            return false;
        }
    }

    public void saveCompany(int tempInfoId){
        TemporaryInfo temp = tempInfoRepo.findById(tempInfoId);

        Company company = new Company();
        company.setName(temp.getCompanyName());
        company.setAddress(temp.getCompanyAddress());
        company.setCity(temp.getCompanyCity());
        company.setStatus(Constant.CompanyStatus.ACTIVATED);
        company.setEmail(temp.getCompanyEmail());
        company.setTel(temp.getCompanyTel());



            companyRepo.save(company);

    }

    public void saveUser(int tempInfoId){
            TemporaryInfo temp = tempInfoRepo.findById(tempInfoId);

            Company company = companyRepo.findByEmail(temp.getCompanyEmail());
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
            user.setStatus(Constant.UserStatus.ACTIVATED);
            userRepo.save(user);
    }
    public void removeTempInfo(int id){
            TemporaryInfo temp = tempInfoRepo.findById(id);
                tempInfoRepo.delete(temp);

    }
//    private static String randomPassword(){
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        return RandomStringUtils.random( 6, characters );
//    }

}
