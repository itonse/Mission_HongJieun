package com.ll.controller;

import com.ll.base.Rq;
import com.ll.domain.Quotation;
import com.ll.service.QuotationService;
import com.ll.util.JsonUtils;

import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;

public class QuotationController {
    private final QuotationService quotationService = new QuotationService();

    public void add(Scanner scanner) {
        OptionalInt quotationId = quotationService.addQuotation(scanner);
        quotationId.ifPresent(id -> System.out.println(id + "번 명언이 등록되었습니다."));
    }

    public void list() {
        List<Quotation> quotations = quotationService.getQuotations();

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if (quotations.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        } else {
            for (int i = quotations.size() - 1; i >= 0; i--) {
                Quotation quotation = quotations.get(i);
                System.out.printf("%d / %s / %s \n", quotation.getId(), quotation.getAuthorName(), quotation.getContent());
            }
        }
    }

    public void delete(Rq rq) {
        boolean isDeleted = quotationService.deleteQuotation(rq);
        int id = rq.getParseInt("id", 0);

        if (isDeleted) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modify(Rq rq, Scanner scanner) {
        quotationService.modifyQuotation(rq, scanner);
    }

    public void loadData() {
        quotationService.loadQuotations();
    }

    public void saveData() {
        quotationService.saveQuotations();
    }

    public void jsonBuild() {
        quotationService.jsonBuild();
        System.out.println(JsonUtils.JSON_FILE + " 파일의 내용이 갱신되었습니다.");
    }
}
