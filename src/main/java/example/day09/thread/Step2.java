package example.day09.thread;

public class Step2 {
    public static void main(String[] args) {

        // 1. 계산기 객체
        Calculator myCal = new Calculator();

        // 2. 유저 객체 생성
        User1 user1 = new User1();
            user1.setName("USER1 Thread");
        // 유저 필드에 계산기 대입
        user1.calculator = myCal;
        user1.value = 100;
        user1.start();

        // 2. 유저 객체
        User1 user2 = new User1();
            user2.setName("USER2 Thread");
        // * user1 , user2 객체 내 동일한 필드 값 대입
        user2.calculator = myCal;
        user2.value = 200;
        user2.start();

    }   // main end
}   // class end
