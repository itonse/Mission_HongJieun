package com.ll;

import com.ll.controller.QuotationController;
import com.ll.util.Rq;

import java.util.Scanner;

public class App {
    public void run() {
        QuotationController quotationController = new QuotationController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    quotationController.add();
                    break;
                case "목록":
                    quotationController.list();
                    break;
                case "삭제":
                    quotationController.delete(rq);
                    break;
                default:
                    System.out.println("명령어를 정확히 입력해주세요.");
                    break;
            }
        }
    }
}