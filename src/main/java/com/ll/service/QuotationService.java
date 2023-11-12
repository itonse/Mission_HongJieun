package com.ll.service;

import com.ll.base.Rq;
import com.ll.domain.Quotation;
import com.ll.repository.QuotationRepository;
import com.ll.util.FilePersistence;
import com.ll.util.JsonUtils;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;

public class QuotationService {
    private final QuotationRepository quotationRepository = new QuotationRepository();

    public OptionalInt addQuotation(Scanner scanner) {
        System.out.print("명언 : ");
        String inputContent = scanner.nextLine();
        System.out.print("작가 : ");
        String inputAuthorName = scanner.nextLine();

        if (!isValidInput(inputContent, inputAuthorName)) {
            return OptionalInt.empty();
        }

        int id = quotationRepository.save(inputContent, inputAuthorName);
        return OptionalInt.of(id);
    }

    public List<Quotation> getQuotations() {
        return quotationRepository.findAll();
    }

    public boolean deleteQuotation(Rq rq) {
        int id = rq.getParseInt("id", 0);

        if (quotationRepository.existsById(id)) {
            quotationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void modifyQuotation(Rq rq, Scanner scanner) {
        int id = rq.getParseInt("id", 0);
        Optional<Quotation> optionalQuotation = quotationRepository.findById(id);

        if (optionalQuotation.isEmpty()) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        Quotation quotation = optionalQuotation.get();

        System.out.print("명언(기존) : ");
        System.out.println(quotation.getContent());
        System.out.print("명언 : ");
        String inputContent = scanner.nextLine();

        System.out.print("작가(기존) : ");
        System.out.println(quotation.getAuthorName());
        System.out.print("작가 : ");
        String inputAuthorName = scanner.nextLine();

        if (!isValidInput(inputContent, inputAuthorName)) {
            return;
        }

        quotation.setContent(inputContent);
        quotation.setAuthorName(inputAuthorName);
    }

    private boolean isValidInput(String inputContent, String inputAuthorName) {
        if (inputContent.isEmpty() || inputAuthorName.isEmpty()) {
            System.out.println("명언 또는 작가명은 공백일 수 없습니다.");
            return false;
        }
        return true;
    }

    public void saveQuotations() {
        List<Quotation> quotations = quotationRepository.findAll();
        FilePersistence.saveQuotationsToTextFile(quotations);
    }

    public void loadQuotations() {
        List<Quotation> quotations = FilePersistence.loadQuotationsFromTextFile();
        if (quotations.size() > 0) {
            quotationRepository.setQuotations(quotations);
        }
    }

    public void jsonBuild() {
        List<Quotation> quotations = quotationRepository.findAll();
        JsonUtils.saveQuotationsToJsonFile(quotations);
    }
}
