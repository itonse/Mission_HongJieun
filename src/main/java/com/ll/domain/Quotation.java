package com.ll.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quotation {
    @Getter
    private int id;
    @Setter
    @Getter
    private String content;
    @Setter
    @Getter
    private String authorName;
}
