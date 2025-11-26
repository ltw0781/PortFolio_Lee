package com.port.folio.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.port.folio.board.domain.Board;
import com.port.folio.common.domain.Page;

@Mapper
public interface BoardMapper {

    public List<Board> list(@Param("page") Page page) throws Exception;

    public Board read(String id) throws Exception;

    public int insert(Board board) throws Exception;

    public int update(Board board) throws Exception;

    public int delete(String id) throws Exception;

    // 게시글 데이터 개수 조회
    public int count() throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

}
