package com.ll.base;

import com.ll.controller.QuotationController;

import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        QuotationController quotationController = new QuotationController();

        quotationController.loadData();    // 로드 데이터

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    quotationController.saveData();    // 파일 저장
                    scanner.close();
                    return;
                case "등록":
                    quotationController.add(scanner);
                    break;
                case "목록":
                    quotationController.list();
                    break;
                case "삭제":
                    quotationController.delete(rq);
                    break;
                case "수정":
                    quotationController.modify(rq, scanner);
                    break;
                case "빌드":
                    quotationController.jsonBuild();
                    break;
                default:
                    System.out.println("명령어를 정확히 입력해주세요.");
                    break;
            }
        }
    }
}
