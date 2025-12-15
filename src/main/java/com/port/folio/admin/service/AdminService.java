package com.port.folio.admin.service;

import java.util.List;

import com.port.folio.admin.domain.Admins;

public interface AdminService {

    public List<Admins> adminList() throws Exception;

}
