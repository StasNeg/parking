package com.example.demo.utils;

import com.example.demo.model.user.ParkingUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static Long getAutorizedId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof ParkingUserPrincipal) ? ((ParkingUserPrincipal) principal).getUser().getId() : null;

    }
}
