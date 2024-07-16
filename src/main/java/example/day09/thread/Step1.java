package example.day09.thread;

public class Step1 {
    // 메인 thread 제공받는다.
    public static void main(String[] args) {

        // 1. 현재 코드를 실행하는 스레드의 이름 호출
        Thread thread = Thread.currentThread();
        System.out.println("해당 코드를 읽어들이는 스레드명 : "+thread.getName());

        // 2. 여러개의 스레드를 만들어서 스레드 이름 확인
        for(int i = 1 ; i<=5 ; i++){
            Thread threadA = new Thread(){
                @Override
                public void run() { // main 스레드 아닌 각 stack 스레드 구역 그래서 i를 여기서 사용할 수 없다.
                    // System.out.println(i);
                    Thread thread = Thread.currentThread();
                    // thread.setName("내가 만든 작업 스레드"); // 스레드 이름 정의하기
                    System.out.println("해당 코드를 읽어들이는 스레드명 : "+thread.getName());
                }   // run end
            };  // constructor end
            threadA.start();
        }   // for end

        // 3. 현재 스레드를 주어진 시간동안 일시정지
        try{
            System.out.println("===== 5초간 (main thread) 대기중 =====");
            Thread.sleep(5000); // Thread.sleep(밀리초);   // 밀리초 1/1000
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("===== 5초후 (main thread) 실행됨 =====");

        // 4. 서로 다른 스레드의 종료를 기다림
        SumThread sumThread = new SumThread();
        sumThread.start();
            // -- main스레드가 sumThread스레드 누적합계 구하기 전에 결과를 출력
        System.out.println("JOIN 전 합계 결과 : "+sumThread.sum);   // 스레드가 다 돌기 전에 main 스레드가 실행되어 0이 나옴
            // -- main스레드가 sumThread스레드가 종료될 때까지 기다림
        try{
            sumThread.join();   // main스레드와 sumThread스레드 조인(흐름 합치기)
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("JOIN 후 합계 결과 : "+sumThread.sum);    // 5050

        // 5. 다른 스레드에게 순서를 양보
        WorkThread workThreadA = new WorkThread();  // 스레드 객체 생성
            workThreadA.setName("작업스레드A");      // 스레드 이름 정의
        WorkThread workThreadB = new WorkThread();
            workThreadB.setName("작업스레드B");

        workThreadA.start();    // 각 스레드 실행
        workThreadB.start();

        try {
            Thread.sleep(5000);     // main 스레드 5초 일시정지
        }catch (Exception e){
            System.out.println(e);
        }
        workThreadA.work = false;        // 작업스레드A의 필드값 변경

        try {
            Thread.sleep(5000);    // main 스레드 5초 일시정지
        }catch (Exception e){
            System.out.println(e);
        }
        workThreadA.work = true;        // 작업스레드A의 필드값 변경

    }   // main end
}   // class end













