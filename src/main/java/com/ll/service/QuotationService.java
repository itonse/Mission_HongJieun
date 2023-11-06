package com.ll.service;

import com.ll.domain.Quotation;
import com.ll.repository.QuotationRepository;

import java.util.List;

public class QuotationService {
    private final QuotationRepository quotationRepository = new QuotationRepository();
    private static int id = 0;

    public void addQuotation(String inputContent, String inputAuthorName) {
        if (inputContent.isEmpty() || inputAuthorName.isEmpty()) {
            System.out.println("명언 또는 작가명은 공백일 수 없습니다.");
        }

        Quotation quotation = new Quotation(++id, inputContent, inputAuthorName);
        quotationRepository.save(quotation);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void listQuotation() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<Quotation> quotations = quotationRepository.findAll();

        for (Quotation quotation : quotations) {
            System.out.println(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent());
        }
    }
}
