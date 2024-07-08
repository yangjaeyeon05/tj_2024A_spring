package example.day03;

class Book{
    // 직접 extends Object 하지 않아도 자동으로 Object 클래스로부터 상속받는다.
    int bookNumber;
    String bookTitle;

    public Book(int bookNumber, String bookTitle) {
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
    }   // 생성자 end
    // 메소드
    public String toString(){
        return "Object Info : " + bookTitle+","+bookNumber;
    }
}   // class end

public class Step1 {
    public static void main(String[] args) {
        // 1. 객체 생성
        Book book1 = new Book (200,"ANT");                                  // 스택메모리 : book1 = 힙메모리 : 302번지 객체 생성
        // 2. book 클래스의 메소드가 아닌 Object 클래스의 메소드 호출
        System.out.println(book1);              // 참조변수를 출력하면 자동으로 toString()가 자동으로 호출된다.
                                                // example.day03.Book@5ca881b5
        System.out.println(book1.toString());   // example.day03.Book@5ca881b5
        // 3. 객체2 생성
        Book book2 = new Book(300,"TIGER");                                 // 스택메모리 : book2 = 힙메모리 : 402번지 객체 생성
        // 4. 객체3 생성
        Book book3 = new Book(200,"ANT");   // 객체1 멤버변수 동일하게 생성    // 스택메모리 : book3 = 힙메모리 : 502번지 객체 생성
        // 5. 객체4 생성이 아닌 객체참조 대입
        Book book4 = book1;                                                                     // 스택메모리 : book4 = book1 ( 302번지 참조 )

        System.out.println(book1==book2);   // false            302번지 == 402번지
        System.out.println(book1==book3);   // false            302번지 == 502번지
        System.out.println(book1==book4);   // true             302번지 == 302번지

        System.out.println(book1.equals(book2));    // false    302번지.equals(402번지)
        System.out.println(book1.equals(book3));    // false    302번지.equals(502번지)
        System.out.println(book1.equals(book4));    // true     302번지.equals(302번지)
    }   // main end
}   // class end
