package com.hrssc.service.impl;

import com.hrssc.entities.Company;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.entities.User;
import com.hrssc.model.Constant;
import com.hrssc.repository.CompanyRepository;
import com.hrssc.repository.TemporaryInfoRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.CompaniesManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
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

    public boolean acceptCompany(int tempInfoId){
        if(saveCompany(tempInfoId)){
            if(saveUser(tempInfoId)){
                if(removeTempInfo(tempInfoId)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean saveCompany(int tempInfoId){
        TemporaryInfo temp = tempInfoRepo.findById(tempInfoId);

        Company company = new Company();
        company.setName(temp.getCompanyName());
        company.setAddress(temp.getCompanyAddress());
        company.setCity(temp.getCompanyCity());
        company.setStatus(Constant.CompanyStatus.ACTIVATED);
        company.setEmail(temp.getCompanyEmail());
        company.setTel(temp.getCompanyTel());


        try{
            companyRepo.save(company);
            return true;
        }catch (Exception e){
            Logger.getLogger(CompaniesManagementServiceImpl.class.toString()).log(java.util.logging.Level.INFO, e.toString());
            return false;
        }

    }

    public boolean saveUser(int tempInfoId){
        try {
            TemporaryInfo temp = tempInfoRepo.findById(tempInfoId);

            Company company = companyRepo.findByName(temp.getCompanyName());
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
            return true;
        }catch(NonUniqueResultException e){
            Logger.getLogger(CompaniesManagementServiceImpl.class.toString()).log(java.util.logging.Level.INFO, e.toString());
            return false;
        }
    }
    public boolean removeTempInfo(int id){
        try{
            TemporaryInfo temp = tempInfoRepo.findById(id);
            if(temp != null){
                tempInfoRepo.delete(temp);
                return true;
            }
            return false;
        }catch(Exception e){
            Logger.getLogger(CompaniesManagementServiceImpl.class.toString()).log(java.util.logging.Level.INFO, e.toString());
            return false;
        }
    }
//    private static String randomPassword(){
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        return RandomStringUtils.random( 6, characters );
//    }

}
