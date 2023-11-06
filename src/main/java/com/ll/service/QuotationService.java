package com.ll.service;

import com.ll.domain.Quotation;
import com.ll.repository.QuotationRepository;

public class QuotationService {
    private static QuotationRepository quotationRepository;
    private static int id = 0;

    public void addQuotation(String inputContent, String inputAuthorName) {
        if (inputContent.isEmpty() || inputAuthorName.isEmpty()) {
            System.out.println("명언 또는 작가명은 공백일 수 없습니다.");
        }

        Quotation quotation = new Quotation(++id, inputContent, inputAuthorName);
        quotationRepository.addData(quotation);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }
}
