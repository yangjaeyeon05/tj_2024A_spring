package example.day09.lombok;

import lombok.*;

@Builder            // 해당 클래스에 @Builder 패턴 생성
@AllArgsConstructor // 해당 클래스에 풀 생성자 자동으로 주입/생성
@NoArgsConstructor  // 해당 클래스에 기본 생성자 자동으로 주입/생성
@ToString   // 해당 클래스에 toString 메소드를 자동으로 재정의
@Getter     // 해당 클래스에 getter 매소드를 자동으로 주입/생성
@Setter     // 해당 클래스에 setter 메소드를 자동으로 주입/생성
public class Member {
    // 필드
    private String id;
    private String name;
    // private String address;
        // - 필드 변화에 따라 유동적으로 getter , setter , 생성자 , toString 사용할 수 있다.

    // public Member(String id , String name){}

    // public Member(String id , String address){}

}   // class end
