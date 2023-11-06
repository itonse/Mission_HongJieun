package com.ll.repository;

import com.ll.domain.Quotation;

import java.util.ArrayList;
import java.util.List;

public class QuotationRepository {
    private final List<Quotation> quotations = new ArrayList<>();

    public void save(Quotation quotation) {
        quotations.add(quotation);
    }

    public List<Quotation> findAll() {
        return quotations;
    }

    public void deleteById(int id) {
        quotations.remove(id - 1);
    }

    public boolean existsById(int id) {
        try {
            quotations.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }
}