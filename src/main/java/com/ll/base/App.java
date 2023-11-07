package com.ll.base;

import com.ll.controller.QuotationController;

import java.util.Scanner;

public class App {
    public void run() {
        QuotationController quotationController = new QuotationController();
        Scanner scanner = new Scanner(System.in);

        quotationController.fileLoad();    // 파일 로드

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    quotationController.fileSave();    // 파일 저장
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
                case "수정":
                    quotationController.modify(rq);
                    break;
                default:
                    System.out.println("명령어를 정확히 입력해주세요.");
                    break;
            }
        }
    }
}