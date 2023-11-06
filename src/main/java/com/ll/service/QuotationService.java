package com.ll.service;

import com.ll.domain.Quotation;
import com.ll.repository.QuotationRepository;
import com.ll.util.Rq;

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

        if (quotations.isEmpty()) {
            System.out.println("등록 된 명언이 없습니다.");
        }

        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.println(quotation.getId() + " / " + quotation.getAuthorName() + " / " + quotation.getContent());
        }
    }

    public void deleteQuotation(Rq rq) {
        int id = rq.parseInt("id", 0);

        quotationRepository.deleteById(id);

        System.out.println(id);
    }
}
