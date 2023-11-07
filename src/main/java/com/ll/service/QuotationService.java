package com.ll.service;

import com.ll.base.Rq;
import com.ll.domain.Quotation;
import com.ll.repository.QuotationRepository;
import com.ll.util.FilePersistence;
import com.ll.util.JsonUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class QuotationService {
    private final QuotationRepository quotationRepository = new QuotationRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void addQuotation() {
        System.out.print("명언 : ");
        String inputContent = scanner.nextLine();
        System.out.print("작가 : ");
        String inputAuthorName = scanner.nextLine();

        if (!isValidInput(inputContent, inputAuthorName)) {
            return;
        }

        int id = quotationRepository.save(inputContent, inputAuthorName);
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
        int id = rq.getParseInt("id", 0);

        if (quotationRepository.existsById(id)) {
            quotationRepository.deleteById(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modifyQuotation(Rq rq) {
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

    public void saveQuotationsToFile() {
        List<Quotation> quotations = quotationRepository.findAll();
        FilePersistence.saveQuotationsToTextFile(quotations);
    }

    public void loadQuotationsFromFile() {
        List<Quotation> quotations = FilePersistence.loadQuotationsFromTextFile();
        if (quotations.size() > 0) {
            quotationRepository.setQuotations(quotations);
        }
    }

    public void jsonBuild() {
        List<Quotation> quotations = quotationRepository.findAll();
        JsonUtils.saveQuotationsToJsonFile(quotations);
        System.out.println(JsonUtils.JSON_FILE + " 파일의 내용이 갱신되었습니다.");
    }
}
