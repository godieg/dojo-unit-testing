package com.dojo.unittest.lvl1;

public class Calculator {

    private final double x;
    private final double y;

    public Calculator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double add() {
        return x + y;
    }

    public double subtract() {
        return x - y;
    }

    public double multiply() {
        return x * y;
    }

    public double divide() {
        return x / y;
    }

}
