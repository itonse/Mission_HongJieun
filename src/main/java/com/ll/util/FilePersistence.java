package com.ll.util;

import com.ll.domain.Quotation;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilePersistence {
    private static final String TXT_FILE = "quotations.txt";   // 파일의 경로

    public static void saveQuotationsToTextFile(List<Quotation> quotations) {   // 파일이 없을 경우 새로 생성하고, 기존에 존재할 경우 업데이트
        try (PrintWriter writer = new PrintWriter(TXT_FILE)) {    // PrintWriter를 사용하여 내용을 파일에 쓴다
            // 다음 저장 형식 형태로 한 줄씩 저장: 1 / 작자미상 / 현재를 사랑하라.
            for (Quotation quotation : quotations) {
                writer.println(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent());
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    public static List<Quotation> loadQuotationsFromTextFile() {     // 기존 파일에서 Quotation 데이터 로드
        List<Quotation> quotations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TXT_FILE))) {    // BufferedReader 를 사용하여 라인 단위로 파일을 읽는다
            String line;   // 라인 단위로 읽을 것
            while ((line = reader.readLine()) != null) {    // 파일의 첫 줄부터 마지막 줄까지 쭉 읽는다 (파일의 끝에 도달하면 null 반환)
                String[] parts = line.split(" / ");    // 읽어온 줄을 (" / ")로 분리. 이렇게 분리된 문자열은 parts 배열에 저장
                if (parts.length == 3) {           // parts[0]: "1", parts[1]: "작자미상", parts[2]: "현재를 사랑하라."
                    int id = Integer.parseInt(parts[0]);
                    String authorName = parts[1];
                    String content = parts[2];
                    quotations.add(new Quotation(id, content, authorName));    // 정보를 바탕으로, Quotation 객체를 생성하여 리스트에 추가
                }
            }
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }

        return quotations;
    }
}
