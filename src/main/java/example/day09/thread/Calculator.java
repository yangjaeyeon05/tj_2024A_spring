package example.day09.thread;

public class Calculator {

    int memory;

    public synchronized void setMemory(int memory){     // 동기화 : synchronized 한 thread가 끝나면 다른 thread 실행
    // public void setMemory(int memory){               // 비동기화 : 한 thread가 실행되는 도중에 다른 thread가 끼어들어 값을 바꿔치기함

        System.out.println("memory = " + memory);
        // 매개변수 값을 필드 저장
        this.memory = memory;
        // 2초간 일시정지
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(Thread.currentThread().getName());
        // 현재 필드 값 확인
        System.out.println(this.memory);                // 비동기화 = memory 값이 같게 나옴 동기화 = memory 값 다르게 나옴
    }   // setMemory() end

}   // class end
