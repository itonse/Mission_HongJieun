package com.ll.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ll.domain.Quotation;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonUtils {
    public static final String JSON_FILE = "data1.json";    // 파일의 경로

    public static void saveQuotationsToJsonFile(List<Quotation> quotations) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);   // pretty print(보기 좋게 정렬) 활성화

        try {
            mapper.writeValue(new File(JSON_FILE), quotations);   // 파일이 없을 경우 새로 생성하고, 기존에 존재할 경우 업데이트
        } catch (IOException e) {
            log.error(e.toString());
        }
    }
}
