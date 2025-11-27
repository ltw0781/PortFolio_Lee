package com.port.folio.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.port.folio.board.domain.Board;
import com.port.folio.board.mapper.BoardMapper;
import com.port.folio.common.domain.Files;
import com.port.folio.common.domain.Page;
import com.port.folio.common.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<Board> list(Page page) throws Exception {

        int total = boardMapper.count();
        page.setTotal(total);

        List<Board> boardList = boardMapper.list(page);
        return boardList;
    }

    @Override
    public Board read(String id) throws Exception {

        Board board = boardMapper.read(id);
        return board;

    }

    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);

        List<MultipartFile> fileList = board.getFileList();

        if (fileList != null) {

            for (MultipartFile file : fileList) {
                Files uploadFile = new Files();
                uploadFile.setFile(file);
                uploadFile.setParentTable("board");
                uploadFile.setParentNo(board.getNo());
                uploadFile.setType("main");
                fileService.upload(uploadFile);
            }

        }

        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        // 게시글 정보 수정
        int result = boardMapper.update(board);

        // 삭제할 파일 처리
        List<String> deleteFiles = board.getDeleteFiles();
        if ( deleteFiles != null && !deleteFiles.isEmpty() ) {
            for (String fileId : deleteFiles) {
                log.info("fileId : " + fileId);
                fileService.delete(fileId); // 파일 삭제
            }
        }

        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        
        Board board = boardMapper.read(id);
        // 게시글 삭제
        int result = boardMapper.delete(id);

        // 첨부파일 종속 삭제

        Files deleteFile = new Files();
        deleteFile.setParentTable("board");
        deleteFile.setParentNo(board.getNo());
        int fileResult = fileService.deleteByParent(deleteFile);
        log.info("fileResult : " + fileResult);

        return result;

    }

}
