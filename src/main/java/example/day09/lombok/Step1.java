package example.day09.lombok;

public class Step1 {
    public static void main(String[] args) {

        // Member 객체 생성
        Member m1 = new Member();
        m1.setId("qwe");
        m1.setName("유재석");

        System.out.println(m1.getId());
        System.out.println(m1.getName());

        System.out.println(m1);

        // Member 객체 생성
        Member m2 = new Member("asd" , "강호동");
        System.out.println(m2);

        // 생성자 규칙 : 메소드와 동일하게 매개변수의 순서 , 타입 , 개수 일치해야함
        // 빌더패턴 : 객체 생성 과정에서 표현 방법을 분리하여 다른 표현으로 객체를 생성하는 방법
            // - 필요한 데이터만 설정할 수 있다. 유연성 확보 , 가독성 높일 수 있다 , 안전성 보장
            /*
                클래스명.builder().필드명(값).필드명(값).필드명(값).build();
            */
        Member m3 = Member.builder()
                .name("신동엽")
                .id("zxcv")
                .build();
        System.out.println(m3);


    }   // main end
}   // class end
