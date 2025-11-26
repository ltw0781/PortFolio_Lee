package com.port.folio.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.port.folio.common.domain.Files;

@Mapper
public interface FileMapper {
    
    // 목록
    public List<Files> list() throws Exception;

    // 조회
    public Files select(String id) throws Exception;

    // 등록
    public int insert(Files file) throws Exception;

    // 수정
    public int update(Files file) throws Exception;

    // 삭제
    public int delete(String id) throws Exception;

    // 부모테이블 기준 조회 파일 목록
    public List<Files> listByParent(Files file) throws Exception;


}
