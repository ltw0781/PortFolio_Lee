package com.port.folio.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.port.folio.board.domain.Board;
import com.port.folio.board.service.BoardService;
import com.port.folio.common.domain.Files;
import com.port.folio.common.domain.Page;
import com.port.folio.common.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    public void list(Model model, Page page) throws Exception {

        List<Board> boardList = boardService.list(page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);

    }

    @GetMapping("/read")
    public String read(@RequestParam("id") String id, Model model, Files file) throws Exception {

        // 게시글 조회
        Board board = boardService.read(id);
        model.addAttribute("board", board);

        // 파일 목록 조회
        file.setParentNo(board.getNo());
        file.setParentTable("board");

        List<Files> fileList = fileService.listByParent(file);
        model.addAttribute("fileList", fileList);

        return "/board/read";
    }

    @GetMapping("/insert")
    public String insert() throws Exception {
        return "/board/insert";
    }

    @PostMapping("/insert")
    public String insertPost(Board board) throws Exception {

        log.info("board : " + board);

        int result = boardService.insert(board);

        if (result > 0) {

            return "redirect:/board/list";

        }

        return "redirect:/board/insert?=error";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") String id, Model model, Files file) throws Exception {

        Board board = boardService.read(id);
        model.addAttribute("board", board);

        // 파일 목록 조회
        file.setParentNo(board.getNo());
        file.setParentTable("board");

        log.info("file : " + file);
        List<Files> fileList = fileService.listByParent(file);
        model.addAttribute("fileList", fileList);

        return "/board/update";

    }

    @PostMapping("/update")
    public String updatePost(Board board) throws Exception {

        int result = boardService.update(board);

        if (result > 0) {
            return "redirect:/board/list";
        }
        String id = board.getId();
        return "redirect:/board/update?id=" + id + "&error";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) throws Exception {

        int result = boardService.delete(id);

        if (result > 0) {
            return "redirect:/board/list";
        }

        return "redirect:/board/update?error&id=" + id + "&error";
    }

}
