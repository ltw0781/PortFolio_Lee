package com.port.folio.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.port.folio.admin.domain.Admins;

@Mapper
public interface AdminMapper {

    public List<Admins> adminList() throws Exception;

}
