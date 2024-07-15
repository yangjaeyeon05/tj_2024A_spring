package example.day08.thread;

import java.awt.*;

public class 작업스레드B implements Runnable{
    // implements : 구현하다

    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Toolkit : java.awt 자바의 UI(화면 , 소리 등등) 라이브러리
        for(int i = 1; i <=5; i++) { // 5회 반복
            toolkit.beep(); // '띵' 비프음 소리 출력
            // 비프음 소리출력 속도보다 for문의 5회 반복 속도가 더 빠르다.
            // for문을 처리하는 흐름[스레드]을 잠시 일시정지
            //  Thread.sleep(밀리초) : 밀리초만큼 스레드가 일시정지
            // 밀리초 : 1/1000 초
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }   // run end
}   // class end
