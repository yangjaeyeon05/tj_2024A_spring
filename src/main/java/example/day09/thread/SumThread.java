package example.day09.thread;

public class SumThread extends Thread{

    public int sum;
    // 1~100까지 누적 합계 구하기
    @Override
    public void run() {
        for(int i = 1; i <=100; i++){
            sum += i;   // 누적 합계
        }   // for end
    }   // run end

}   // class end
