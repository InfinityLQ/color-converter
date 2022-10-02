
package com.zlatamigas.colorconverter.service;

import com.zlatamigas.colorconverter.entity.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ColorConverter {

    public static final int DOUBLE_PLACES = 6;
    public static final int RGB_PLACES = 0;

    public CMYK toDoublePlacesCMYK(CMYK cmyk) {

        CMYK newCmyk = new CMYK();

        newCmyk.setC(roundDoubleNoNegative(cmyk.getC(), DOUBLE_PLACES));
        newCmyk.setM(roundDoubleNoNegative(cmyk.getM(), DOUBLE_PLACES));
        newCmyk.setY(roundDoubleNoNegative(cmyk.getY(), DOUBLE_PLACES));
        newCmyk.setK(roundDoubleNoNegative(cmyk.getK(), DOUBLE_PLACES));

        return newCmyk;
    }

    public XYZ toDoublePlacesXYZ(XYZ xyz) {

        XYZ newXYZ = new XYZ();

        newXYZ.setX(roundDoubleNoNegative(xyz.getX(), DOUBLE_PLACES));
        newXYZ.setY(roundDoubleNoNegative(xyz.getY(), DOUBLE_PLACES));
        newXYZ.setZ(roundDoubleNoNegative(xyz.getZ(), DOUBLE_PLACES));

        return newXYZ;
    }

    public CMYK convertRGBtoCMYK(RGB rgb) {

        CMYK cmyk = new CMYK();

        double dr = 1 - (double) rgb.getR() / 255;
        double dg = 1 - (double) rgb.getG() / 255;
        double db = 1 - (double) rgb.getB() / 255;

        double k;
        k = Math.min(dr, dg);
        k = Math.min(k, db);
        k = roundDoubleNoNegative(k, DOUBLE_PLACES);

        double dk = 1 - k;
        double c = 0;
        double m = 0;
        double y = 0;

        if (dk != 0) {
            c = roundDoubleNoNegative((dr - k) / dk, DOUBLE_PLACES);
            m = roundDoubleNoNegative((dg - k) / dk, DOUBLE_PLACES);
            y = roundDoubleNoNegative((db - k) / dk, DOUBLE_PLACES);
        }

        cmyk.setK(k);
        cmyk.setC(c);
        cmyk.setM(m);
        cmyk.setY(y);

        return cmyk;
    }

    public RGB convertCMYKToRGB(CMYK cmyk) {
        RGB rgb = new RGB();

        double dk = 1 - cmyk.getK();

        int r = (int) roundDoubleNoNegative(255 * dk * (1 - cmyk.getC()), RGB_PLACES);
        int g = (int) roundDoubleNoNegative(255 * dk * (1 - cmyk.getM()), RGB_PLACES);
        int b = (int) roundDoubleNoNegative(255 * dk * (1 - cmyk.getY()), RGB_PLACES);

        rgb.setR(r);
        rgb.setG(g);
        rgb.setB(b);

        return rgb;
    }


    public XYZ convertRGBtoXYZ(RGB rgb) {

        XYZ xyz = new XYZ();

        double rn = countG((double) rgb.getR() / 255) * 100;
        double gn = countG((double) rgb.getG() / 255) * 100;
        double bn = countG((double) rgb.getB() / 255) * 100;

        double x = 0.412453 * rn + 0.357580 * gn + 0.180423 * bn;
        double y = 0.212671 * rn + 0.715160 * gn + 0.072169 * bn;
        double z = 0.019334 * rn + 0.119193 * gn + 0.950227 * bn;

        xyz.setX(roundDoubleNoNegative(x, DOUBLE_PLACES));
        xyz.setY(roundDoubleNoNegative(y, DOUBLE_PLACES));
        xyz.setZ(roundDoubleNoNegative(z, DOUBLE_PLACES));

        return xyz;
    }

    public RGB convertXYZToRGB(XYZ xyz) {
        RGB rgb = new RGB();

        double x = xyz.getX();
        double y = xyz.getY();
        double z = xyz.getZ();

        double rn = 3.240481 * x - 1.537152 * y - 0.498536 * z;
        double gn = -0.969255 * x + 1.87599 * y + 0.041556 * z;
        double bn = 0.055647 * x - 0.204041 * y + 1.05731 * z;

        double r = 255 * countReverseG(rn / 100);
        double g = 255 * countReverseG(gn / 100);
        double b = 255 * countReverseG(bn / 100);

        rgb.setR((int) roundDoubleNoNegative(r, RGB_PLACES));
        rgb.setG((int) roundDoubleNoNegative(g, RGB_PLACES));
        rgb.setB((int) roundDoubleNoNegative(b, RGB_PLACES));

        return rgb;
    }


    public CMYK convertXYZtoCMYK(XYZ xyz) {

        return convertRGBtoCMYK(convertXYZToRGB(xyz));
    }

    public XYZ convertCMYKtoXYZ(CMYK cmyk) {

        return convertRGBtoXYZ(convertCMYKToRGB(cmyk));
    }


    private double countG(double x) {
        if (x >= 0.04045) {
            return Math.pow((x + 0.055) / 1.055, 2.4);
        } else {
            return x / 12.92;
        }
    }

    private double countReverseG(double y) {
        if (y >= 0.003130) {
            return Math.pow(y, 1 / 2.4) * 1.055 - 0.055;
        } else {
            return y * 12.92;
        }
    }

    private double roundDoubleNoNegative(double value, int places) {

        if (value < 0) {
            value = 0;
        }
        BigDecimal bd;
        try {

            bd = new BigDecimal(Double.toString(value));
            bd = bd.setScale(places, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " value=" + value);
            return 0;
        }
        return bd.doubleValue();
    }
}