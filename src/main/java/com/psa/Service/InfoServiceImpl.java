package com.psa.Service;

import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {
    public String getMsg() {
        return "Hello ";
    }
}
