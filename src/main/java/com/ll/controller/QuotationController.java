package com.ll.controller;

import com.ll.service.QuotationService;
import com.ll.util.Rq;

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
}
