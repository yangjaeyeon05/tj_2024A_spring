package example.day10;

public class GenericPrinterTest2 {
    public static void main(String[] args) {
        GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
        powderPrinter.setMaterial(new Powder());
        powderPrinter.doPrinting();

        GenericPrinter<Plastic> platicrPrinter = new GenericPrinter<Plastic>();
        platicrPrinter.setMaterial(new Plastic());
        platicrPrinter.doPrinting();
    }
}
