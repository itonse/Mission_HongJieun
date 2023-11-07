package com.ll.repository;

import com.ll.domain.Quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class QuotationRepository {
    private final List<Quotation> quotations = new ArrayList<>();

    public void save(Quotation quotation) {
        quotations.add(quotation);
    }

    public List<Quotation> findAll() {
        return new ArrayList<>(quotations);
    }

    public void deleteById(int id) {
        if (existsById(id)) {
            quotations.remove(findIndexById(id));
        }
    }

    public boolean existsById(int id) {
        return findIndexById(id) != -1;
    }

    public Optional<Quotation> findById(int id) {
        if (findIndexById(id) == -1) {
            return Optional.empty();
        } else {
            return Optional.of(quotations.get(findIndexById(id)));
        }
    }

    private int findIndexById(int id) {    // id에 해당하는 quotations 인덱스 찾기
        return IntStream.range(0, quotations.size())
                .filter(i -> quotations.get(i).getId() == id)
                .findFirst()
                .orElse(-1);    // id에 해당하는 인덱스가 없을 시 -1 반환
    }
}