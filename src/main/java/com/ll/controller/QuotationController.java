package com.ll.controller;

import com.ll.service.QuotationService;

import java.util.Scanner;

public class QuotationController {
    private final QuotationService quotationService = new QuotationService();
    private final Scanner scanner = new Scanner(System.in);

    public void add() {
        System.out.print("명언 : ");
        String inputContent = scanner.nextLine();
        System.out.print("작가 : ");
        String inputAuthorName = scanner.nextLine();

        quotationService.addQuotation(inputContent, inputAuthorName);
    }

    public void list() {
        quotationService.listQuotation();
    }
}
