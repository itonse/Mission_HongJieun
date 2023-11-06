package com.ll.util;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    @Getter
    private String action;
    private String queryString;
    private List<String> paramNames = new ArrayList<>();    // 명령이름
    private List<String> paramValues = new ArrayList<>();    // 명령에서의 값

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);  // 명령을 '?' 를 기준으로 두 문자열로 분리
        action = cmdBits[0].trim();    // 첫 번째 문자열: 명령 종류  ex) 삭제

        if (cmdBits.length == 1) {    // 두 번째 문자열이 없는 경우, 돌아가기 ex) cmd = "등록"
            return;
        }

        queryString = cmdBits[1].trim();     // 두 번째 문자열: 명령 파라미터(들)  ex) "id=5&name=John"

        String[] queryStringBits = queryString.split("&");    // (확장성) 명령 파라미터가 여러개일 경우, '&' 문자 기준으로 분리 ex) "id=5", "name=John"

        for (int i = 0; i < queryStringBits.length; i++) {    // 명령 파라미터 분리
            String queryParamStr = queryStringBits[i];    // i = 0 -> queryParamStr = "id=5"
            String[] queryParamStrBits = queryParamStr.split("=", 2);   // "id" "5" 로 분리

            String paramName = queryParamStrBits[0];   // "id"
            String paramValue = queryParamStrBits[1];   // "5"

            paramNames.add(paramName);
            paramValues.add(paramValue);
        }
    }
    public int parseInt(String paramName, int defaultValue) {
        int index = paramNames.indexOf(paramName);

        if (index == -1) return defaultValue;

        String paramValue = paramValues.get(index);

        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}