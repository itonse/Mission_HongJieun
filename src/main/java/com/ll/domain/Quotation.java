package com.ll.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Quotation {
    private int id;
    @Setter
    private String content;
    @Setter
    private String authorName;
}
