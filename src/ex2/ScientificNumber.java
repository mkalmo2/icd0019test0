package ex2;

public class ScientificNumber {
    private int mantissa;
    private int exponent;

    public ScientificNumber(int mantissa, int exponent) {
        this.mantissa = mantissa;
        this.exponent = exponent;
    }

    public int intValue() {
        return mantissa * Double.valueOf(Math.pow(10, exponent)).intValue();
    }

}