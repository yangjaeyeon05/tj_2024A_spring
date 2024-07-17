package example.day10;

public class GenericPrinterTest {
    public static void main(String[] args) {
        // 1.
        GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();

        powderPrinter.setMaterial(new Powder());
        Powder powder = powderPrinter.getMaterial();
        System.out.println(powderPrinter);

        // 2.
        GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();

        plasticPrinter.setMaterial(new Plastic());
        Plastic plastic = plasticPrinter.getMaterial();
        System.out.println(plasticPrinter);

        // 3.
        // GenericPrinter<Water> waterPrinter = new GenericPrinter<Water>(); // Water 클래스는 Material 클래스를 상속받지 않았으므로 사용할 수 없다.

    }   // main end
}   // class end
