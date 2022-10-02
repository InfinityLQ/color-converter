package com.zlatamigas.colorconverter.entity;

public class CMYK {

    private double c;
    private double m;
    private double y;
    private double k;

    public CMYK() {
    }

    public CMYK(double c, double m, double y, double k) {
        this.c = c;
        this.m = m;
        this.y = y;
        this.k = k;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CMYK{");
        sb.append("c=").append(c);
        sb.append(", m=").append(m);
        sb.append(", y=").append(y);
        sb.append(", k=").append(k);
        sb.append('}');
        return sb.toString();
    }
}