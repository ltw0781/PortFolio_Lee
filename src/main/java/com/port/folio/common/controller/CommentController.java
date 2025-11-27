package com.port.folio.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.port.folio.common.domain.Comments;
import com.port.folio.common.service.CommentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("")
    public String commentPost(@RequestBody Comments Comment) throws Exception{

        int result = commentService.insert(Comment);

        if ( result > 0 ) {
            return "SUCCESS";
        }
        return "FAIL";
    }

    /**
     * 댓글 등록
     * @param boardNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public String commentList(@RequestParam("boardNo") int boardNo, Model model) throws Exception {

        List<Comments> commentList = commentService.listByParent(boardNo);
        model.addAttribute("commentList", commentList);

        return "/comment/list";
    }
    
    
}
