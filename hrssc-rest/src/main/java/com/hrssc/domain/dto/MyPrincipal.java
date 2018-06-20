package com.hrssc.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrssc.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Thien on 6/19/2018.
 */
public class MyPrincipal implements UserDetails{
    private int id;
    private String fullname;
    private String username;

    private String email;

    private String password;
    private String tel;
    private int companyID;
    private boolean isFirstLogin;
    private int roleID;

    private Collection<? extends GrantedAuthority> authorities;

    public MyPrincipal(int id, String fullname, String username, String email, String password, String tel, int companyId,int roleId, boolean firstLogin, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.companyID = companyId;
        this.roleID = roleId;
        this.isFirstLogin = firstLogin;
        this.authorities = authorities;

    }

    public static MyPrincipal create(User user, Collection<? extends GrantedAuthority> authorities){

        return new MyPrincipal(
                user.getId(),
                user.getFullname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getTel(),
                user.getCompanyId(),
                user.getRoleId(),
                user.isFirstLogin(),
                authorities
        );
    }

    public String getTel() {
        return tel;
    }

    public int getCompanyID() {
        return companyID;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleID() {
        return roleID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
