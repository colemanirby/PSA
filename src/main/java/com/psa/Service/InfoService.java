package com.psa.Service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface InfoService {
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    String getMsg();
}