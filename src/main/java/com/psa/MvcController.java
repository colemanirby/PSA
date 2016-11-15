package com.psa;

import com.psa.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class MvcController {
    @Autowired
    private InfoService service;
    @RequestMapping("/")
    public @ResponseBody String userInfo(Authentication authentication) {
        String msg = "";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            msg+=service.getMsg()+ authentication.getName()+", You have "+ role;
        }
        return msg;
    }
}