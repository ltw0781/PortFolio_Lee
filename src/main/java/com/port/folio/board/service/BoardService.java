package com.port.folio.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.port.folio.board.domain.Board;
import com.port.folio.common.domain.Page;

public interface BoardService {
    
    public List<Board> list(@Param("page") Page page) throws Exception;

    public Board read(String id) throws Exception;

    public int insert(Board board) throws Exception;

    public int update(Board board) throws Exception;

    public int delete(String id) throws Exception;

}
