package com.port.folio.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.port.folio.admin.domain.Admins;
import com.port.folio.admin.service.AdminService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/adminList")
    public void adminList(Model model) throws Exception{

        List<Admins> adminList = adminService.adminList();
        log.info("adminList : {}" + adminList);
        log.info("adminList : {}" + adminList.toString());
        model.addAttribute("adminList", adminList);

    }
    
    
}
