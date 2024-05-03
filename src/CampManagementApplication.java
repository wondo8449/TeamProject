package TeamProject.src;

import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore; // 전체 학생 데이터 저장 List
    private static List<Subject> subjectStore; // 과목 목록 생성용 List

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY"; // 필수 과목 설정용 상수
    private static String SUBJECT_TYPE_CHOICE = "CHOICE"; // 선택 과목 설정용 상수

    // index 관리 필드
    private static int studentIndex; // 학생용 인덱스
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex; // 과목용 인덱스
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex; // 점수용 인덱스 (안 써도 될듯)
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    // 실행시킬 메인 메서드
    public static void main(String[] args) {
        setInitData(); // 데이터 저장소들 생성 메서드
        try {
            // 여기부터
            Student student1 = new Student("1", "김예찬"); // 학생1 객체 생성
            //student1.setSubjectList(subjectStore.get(0)); // 학생1의 수강 과목 입력
            student1.setSubjectList(subjectStore.get(1));
            student1.setSubjectList(subjectStore.get(2));
            student1.getSubjectList().get(0).setScore(1, 80, 'C'); // 0번째 과목에 점수 입력
            student1.getSubjectList().get(0).setScore(2, 92, 'B'); // 근데 학생의 과목별로 입력이 안되고 같이 입력되는 문제있음
            student1.getSubjectList().get(1).setScore(1, 63, 'F'); // 현재 0번 과목(Java)에 1번 학생 점수랑 2번 학생 점수가 같이 저장됨
            student1.getSubjectList().get(2).setScore(1, 74, 'D'); // 학생별로 SubjectList는 있으나 그 안에 객체가 공유되고 있는 상황인 것 같음

            // 이렇게 과목을 넣을 때 Subject를 새로 생성하면 문제 해결되나 모든 학생이 수강 신청을 할 때마다
            // 이렇게 생성을 하기에는 구조가 별로임
            student1.setSubjectList(new Subject("SU9", "Java", SUBJECT_TYPE_MANDATORY));


            Student student2 = new Student("2", "노상윤");
            student2.setSubjectList(new Subject("SU10", "Java", SUBJECT_TYPE_MANDATORY));
            //student1.setSubjectList(subjectStore.get(0));
            student2.setSubjectList(subjectStore.get(4));
            student2.setSubjectList(subjectStore.get(5));
            student2.getSubjectList().get(0).setScore(1, 81, 'C');
            student2.getSubjectList().get(0).setScore(2, 76, 'D');
            student2.getSubjectList().get(1).setScore(1, 99, 'A');
            student2.getSubjectList().get(2).setScore(1, 90, 'B');

            studentStore.add(student1); // 완성된 학생 정보를 전체 학생 List에 저장
            studentStore.add(student2);

            System.out.println(studentStore.get(1).getSubjectList().get(0).getScoreList().get(0)); // 2번 학생의 1회차 점수가 아닌 1번 학생의 1회차 점수가 나옴

            // 입력 잘 되었는지 테스트용 출력
            for (int i =0; i < studentStore.size(); i++) {
                System.out.println("이름 : " + studentStore.get(i).getStudentName());
                System.out.println("아이디 : " + studentStore.get(i).getStudentId());
                for (int j =0; j < studentStore.get(i).getSubjectList().size(); j++) {
                    System.out.println("수강중인 과목 : " + studentStore.get(i).getSubjectList().get(j).getSubjectName());
                    for (int k =0; k < studentStore.get(i).getSubjectList().get(j).getScoreList().size(); k++)
                    System.out.println(studentStore.get(i).getSubjectList().get(j).getSubjectName() + "의 " + studentStore.get(i).getSubjectList().get(j).getScoreList().get(k));
                }
            }

            // 과목 인덱스 번호 확인용 출력 (출력해보시려면 주석 풀어주세요)
//            for (int i =0; i < subjectStore.size(); i++) {
//                System.out.println("아이디 : " + subjectStore.get(i).getSubjectId());
//                System.out.println("이름 : " + subjectStore.get(i).getSubjectName());
//                System.out.println("타입 : " + subjectStore.get(i).getSubjectType());
//            }
            // 여기까지 테스트용 데이터

            displayMainView(); // 실제 로직이 담긴 메서드
        } catch (Exception e) { // 예외처리
            System.out.println(e);
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>(); // 전체 학생 정보를 담을 List를 초기화
        subjectStore = List.of( // 과목별로 객체를 생성해서 전체 과목 List에 담아주기
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록 (상윤님 파트)
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        //Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회 (승훈님 파트)
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 조회용 학생 ID받아오는 메서드 (수정 X)
    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록 (효진님 파트)
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정 (지우님 파트)
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회 (예찬님 파트)
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}