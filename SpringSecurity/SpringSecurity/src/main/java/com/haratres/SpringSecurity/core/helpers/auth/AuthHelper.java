package com.haratres.SpringSecurity.core.helpers.auth;

import com.haratres.SpringSecurity.entities.concretes.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    public static int getuserId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        return  customUserDetail.getUserId();

    }
}
