package com.taskhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentInfo {

    private String content;
    private String creationDate;

}
