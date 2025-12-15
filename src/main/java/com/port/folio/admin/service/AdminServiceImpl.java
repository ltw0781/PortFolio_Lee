package com.port.folio.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.port.folio.admin.domain.Admins;
import com.port.folio.admin.mapper.AdminMapper;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admins> adminList() throws Exception {
        List<Admins> adminList = adminMapper.adminList();
        return adminList;
    }
    
}
