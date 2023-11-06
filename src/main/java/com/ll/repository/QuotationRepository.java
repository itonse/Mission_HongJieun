package com.ll.repository;

import com.ll.domain.Quotation;

import java.util.ArrayList;
import java.util.List;

public class QuotationRepository {
    private final List<Quotation> quotations = new ArrayList<>();

    public void addData(Quotation quotation) {
        quotations.add(quotation);
    }
}
