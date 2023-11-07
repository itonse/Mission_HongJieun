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
        quotations.remove(findIndexById(id));
    }

    public boolean existsById(int id) {
        try {
            quotations.get(findIndexById(id));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public Quotation findById(int id) {
        return quotations.get(findIndexById(id));
    }

    private int findIndexById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}