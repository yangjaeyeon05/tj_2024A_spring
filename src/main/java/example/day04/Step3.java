package example.day04;

import java.text.DecimalFormat;

public class Step3 {
    public static void main(String[] args) {

        String money = "123123123";
        Integer money1 = Integer.valueOf(money);
        // 문제 : money 변수의 존재하는 문자열 금액의 천단위 쉽표를 넣기
            // 천단위 쉼표가 포함된 형식으로 반환하는 함수 구현하시오.
        DecimalFormat df = new DecimalFormat("###,###");
        String moneyStr = df.format(money1);
        System.out.println("moneyStr = " + moneyStr);

    }
}
