package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        List<Quotation> quotations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");
        System.out.print("명령) ");

        String request = scanner.nextLine();

        switch (request) {
            case "종료":
                return;
            default:
                System.out.println("명령어를 정확히 입력해주세요.");
                break;
        }
    }
}
