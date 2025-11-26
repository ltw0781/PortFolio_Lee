package com.port.folio.board.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Board {

    private int no;
    private String id;
    private String category;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private int viewCount;

    private List<MultipartFile> fileList; // 첨부파일 목록

    public Board() {
        this.id = UUID.randomUUID().toString();
    }
    
}
