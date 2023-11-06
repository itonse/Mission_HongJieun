package com.ll.service;

import com.ll.domain.Quotation;
import com.ll.repository.QuotationRepository;
import com.ll.util.Rq;

import java.util.List;
import java.util.Scanner;

public class QuotationService {
    private final QuotationRepository quotationRepository = new QuotationRepository();
    private static int id = 0;
    private final Scanner scanner = new Scanner(System.in);

    private String inputContent;
    private String inputAuthorName;

    public void addQuotation() {
        System.out.print("명언 : ");
        inputContent = scanner.nextLine();
        System.out.print("작가 : ");
        inputAuthorName = scanner.nextLine();

        if (!isValidInput(inputContent, inputAuthorName)) {
            return;
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

        if (quotationRepository.existsById(id)) {
            quotationRepository.deleteById(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modifyQuotation(Rq rq) {
        int id = rq.parseInt("id", 0);

        if (quotationRepository.existsById(id)) {
            Quotation quotation = quotationRepository.findById(id);

            System.out.print("명언(기존) : ");
            System.out.println(quotation.getContent());
            System.out.print("명언 : ");
            inputContent = scanner.nextLine();

            System.out.print("작가(기존) : ");
            System.out.println(quotation.getAuthorName());
            System.out.print("작가 : ");
            inputAuthorName = scanner.nextLine();

            if (!isValidInput(inputContent, inputAuthorName)) {
                return;
            }

            quotation.setContent(inputContent);
            quotation.setAuthorName(inputAuthorName);
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    private boolean isValidInput(String inputContent, String inputAuthorName) {
        if (inputContent.isEmpty() || inputAuthorName.isEmpty()) {
            System.out.println("명언 또는 작가명은 공백일 수 없습니다.");
            return false;
        }
        return true;
    }
}
