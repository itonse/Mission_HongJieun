## [1Week] 홍지은

### 📌미션 요구사항 분석 & 체크리스트

---

- [x] 1단계: 명언 앱 종료 기능
- [x] 2단계: 명언 등록 기능
- [x] 3단계: 명언 등록시 생성된 명언번호가 노출되도록 구현
- [x] 4단계: 명언을 등록할 때마다 생성되는 명언번호 증가하도록 구현
- [x] 5단계: 명언 목록 출력 기능
- [x] 6단계: 명언 삭제 기능
- [x] 7단계: 존재하지 않는 명언삭제에 대한 예외처리 기능
- [x] 8단계: 명언 수정 기능
- [x] 9단계: 파일을 통한 영속성 기능
- [x] 10단계: data.json 빌드 기능
- [ ] 11단계: SimpleDbTest.java 의 모든 테스트케이스를 만족하는 기능 구현
- [ ] 12단계: 파일 영속성에 관련된 기능을 제거하고 SimpleDB 를 사용하여 명언 정보를 DB에 저장
- [ ] 13단계: @Configuration, @Bean, @Component, @Autowired 를 이용한 의존성 주입기능 구현
- [ ] 14단계: @Configuration, @Bean, @Component, @Autowired 를 이용한 의존성 주입기능 적용
<br><br><br>

### 📌1주차 미션 요약

---

**[접근 방법]**

MVC 패턴에 따라 클래스 별로 역할을 구분을 하였습니다. <br>
```
- Controller (App, QuotationController): 사용자의 입력을 받아 처리하는 요청을 적절한 서비스 메서드에 전달하고, 결과를 반환합니다.

- Service (QuotationService): 비즈니스 로직을 처리합니다. 여기서는 명언 CURD 로직이 해당됩니다.

- Repository (QuotationRepository): 데이터를 저장하고 관리하는 역할을 수행합니다. 여기서는 Quotation 객체의 저장소 역할을 합니다.
```
<br>
현재 클래스의 구조는 아래와 같습니다. <br>

Main -> App -> QuotationController -> QuotationService -> QuotationRepository -> Quotation

<br>

---

**[특이사항]**

명언 CRUD 기능과 다소 거리가 있는 파일 I/O, json 관련 기능의 메서드들을 묶어 
다른 서비스 클래스(ex: Data IO Service)로 분리하고 싶었지만, 수행하지 못했습니다. <br>

이유: QuotationService와 DataIOService 클래스 각각에서 ```new QuotationRepository();```를 호출하여 별도의 QuotationRepository 인스턴스를 생성하면, 
두 서비스는 각각의 quotations(명언 리스트)와 lastId(마지막 id)를 가지게 되므로 **데이터가 공유되지 않습니다.**

시도해본 방법: QuotationRepository 인스턴스를 QuotationController 에 static 변수로 두고 모든 서비스에서 이를 사용할 수 있게 하는 방법<br>
-> 이 방법을 시도해 보았으나 "Controller에서 Repository에 직접 접근할 수 있게 만들면, Controller와 Repository 사이에 의존성이 생겨 
    MVC 패턴의 계층 분리의 원칙에 부합하지 않게 됩니다. 따라서 각 계층의 역할을 유지하면서 서비스를 기능별로 적절히 분리할 수 있는 
    다른 방법을 모색해 볼 필요가 있습니다.

시도해볼 방법(피드백): 의존성 주입을 관리하는 별도의 context 클래스를 도입하는 방법 이용<br>
-> 싱글톤 패턴과 팩토리 패턴을 활용하여, 자주 사용되는 Controller, Service, Repository 클래스들을 관리하고 공유하도록 한다.