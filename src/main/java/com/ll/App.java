package com.ll;

import com.ll.controller.QuotationController;
import java.util.Scanner;

public class App {
    public void run() {
        QuotationController quotationController = new QuotationController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String request = scanner.nextLine();

            switch (request) {

                case "종료":
                    return;
                case "등록":
                    quotationController.add();
                    break;
                default:
                    System.out.println("명령어를 정확히 입력해주세요.");
                    break;
            }
        }
    }
}
