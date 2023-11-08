package com.ll.controller;

import com.ll.base.Rq;
import com.ll.service.QuotationService;

public class QuotationController {
    private final QuotationService quotationService = new QuotationService();

    public void add() {
        quotationService.addQuotation();
    }

    public void list() {
        quotationService.listQuotation();
    }

    public void delete(Rq rq) {
        quotationService.deleteQuotation(rq);
    }

    public void modify(Rq rq) {
        quotationService.modifyQuotation(rq);
    }

    public void loadData() {
        quotationService.loadQuotations();
    }

    public void saveData() {
        quotationService.saveQuotations();
    }

    public void jsonBuild() {
        quotationService.jsonBuild();
    }
}
