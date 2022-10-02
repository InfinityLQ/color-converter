package com.zlatamigas.colorconverter.controller;

import com.zlatamigas.colorconverter.entity.*;
import com.zlatamigas.colorconverter.service.ColorConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

   
    public ColorPicker colorPicker;

    @FXML
    public Rectangle rectColorArea;

    @FXML
    public Slider sliderCMYKc;
    @FXML
    public Slider sliderCMYKm;
    @FXML
    public Slider sliderCMYKy;
    @FXML
    public Slider sliderCMYKk;
    @FXML
    public Slider sliderXYZx;
    @FXML
    public Slider sliderXYZy;
    @FXML
    public Slider sliderXYZz;
    @FXML
    public Slider sliderRGBr;
    @FXML
    public Slider sliderRGBg;
    @FXML
    public Slider sliderRGBb;
    @FXML

    public TextField etCMYKc;
    @FXML
    public TextField etCMYKm;
    @FXML
    public TextField etCMYKy;
    @FXML
    public TextField etCMYKk;
    @FXML
    public TextField etXYZx;
    @FXML
    public TextField etXYZy;
    @FXML
    public TextField etXYZz;
    @FXML
    public TextField etRGBr;
    @FXML
    public TextField etRGBg;
    @FXML
    public TextField etRGBb;

    private ColorConverter colorConverter;
    private boolean isChanging = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorConverter = new ColorConverter();

        sliderRGBr.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            int r = newValue.intValue();
            int g = (int) sliderRGBg.getValue();
            int b = (int) sliderRGBb.getValue();
            parameterChangedRGB(r, g, b);
        });
        sliderRGBg.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            int r = (int) sliderRGBr.getValue();
            int g = newValue.intValue();
            int b = (int) sliderRGBb.getValue();
            parameterChangedRGB(r, g, b);
        });
        sliderRGBb.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            int r = (int) sliderRGBr.getValue();
            int g = (int) sliderRGBg.getValue();
            int b = newValue.intValue();
            parameterChangedRGB(r, g, b);
        });

        sliderCMYKc.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double c = newValue.doubleValue();
            double m = sliderCMYKm.getValue();
            double y = sliderCMYKy.getValue();
            double k = sliderCMYKk.getValue();
            parameterChangedCMYK(c, m, y, k);
        });
        sliderCMYKm.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double c = sliderCMYKc.getValue();
            double m = newValue.doubleValue();
            double y = sliderCMYKy.getValue();
            double k = sliderCMYKk.getValue();
            parameterChangedCMYK(c, m, y, k);
        });
        sliderCMYKy.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double c = sliderCMYKc.getValue();
            double m = sliderCMYKm.getValue();
            double y = newValue.doubleValue();
            double k = sliderCMYKk.getValue();
            parameterChangedCMYK(c, m, y, k);
        });
        sliderCMYKk.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double c = sliderCMYKc.getValue();
            double m = sliderCMYKm.getValue();
            double y = sliderCMYKy.getValue();
            double k = newValue.doubleValue();
            parameterChangedCMYK(c, m, y, k);
        });

        sliderXYZx.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double x = newValue.doubleValue();
            double y = sliderXYZy.getValue();
            double z = sliderXYZz.getValue();
            parameterChangedXYZ(x, y, z);
        });
        sliderXYZy.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double x = sliderXYZx.getValue();
            double y = newValue.doubleValue();
            double z = sliderXYZz.getValue();
            parameterChangedXYZ(x, y, z);
        });
        sliderXYZz.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isChanging) {
                return;
            }
            double x = sliderXYZx.getValue();
            double y = sliderXYZy.getValue();
            double z = newValue.doubleValue();
            parameterChangedXYZ(x, y, z);
        });

        etRGBr.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = (int) Double.parseDouble(etRGBr.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 255) {
                    valueFromEF = 255;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            int r = (int) valueFromEF;
            int g = (int) sliderRGBg.getValue();
            int b = (int) sliderRGBb.getValue();
            parameterChangedRGB(r, g, b);
        });
        etRGBg.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = (int) Double.parseDouble(etRGBg.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 255) {
                    valueFromEF = 255;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            int r = (int) sliderRGBr.getValue();
            int g = (int) valueFromEF;
            int b = (int) sliderRGBb.getValue();
            parameterChangedRGB(r, g, b);
        });
        etRGBb.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = (int) Double.parseDouble(etRGBb.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 255) {
                    valueFromEF = 255;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            int r = (int) sliderRGBr.getValue();
            int g = (int) sliderRGBg.getValue();
            int b = (int) valueFromEF;
            parameterChangedRGB(r, g, b);
        });

        etXYZx.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etXYZx.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 95) {
                    valueFromEF = 95;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double x = valueFromEF;
            double y = sliderXYZy.getValue();
            double z = sliderXYZz.getValue();

            etXYZx.setText(String.format("%.6f", valueFromEF));
            parameterChangedXYZ(x, y, z);
        });
        etXYZy.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etXYZy.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 100) {
                    valueFromEF = 100;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double x = sliderXYZx.getValue();
            double y = valueFromEF;
            double z = sliderXYZz.getValue();

            etXYZy.setText(String.format("%.6f", valueFromEF));
            parameterChangedXYZ(x, y, z);
        });
        etXYZz.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etXYZz.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 109) {
                    valueFromEF = 109;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double x = sliderXYZx.getValue();
            double y = sliderXYZy.getValue();
            double z = valueFromEF;

            etXYZz.setText(String.format("%.6f", valueFromEF));
            parameterChangedXYZ(x, y, z);
        });

        etCMYKc.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etCMYKc.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 1) {
                    valueFromEF = 1;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double c = valueFromEF;
            double m = sliderCMYKm.getValue();
            double y = sliderCMYKy.getValue();
            double k = sliderCMYKk.getValue();
            parameterChangedCMYK(c, m, y, k);
        });
        etCMYKm.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etCMYKm.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 1) {
                    valueFromEF = 1;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double c = sliderCMYKc.getValue();
            double m = valueFromEF;
            double y = sliderCMYKy.getValue();
            double k = sliderCMYKk.getValue();
            parameterChangedCMYK(c, m, y, k);
        });
        etCMYKy.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etCMYKy.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 1) {
                    valueFromEF = 1;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double c = sliderCMYKc.getValue();
            double m = sliderCMYKm.getValue();
            double y = valueFromEF;
            double k = sliderCMYKk.getValue();
            parameterChangedCMYK(c, m, y, k);
        });
        etCMYKk.setOnAction(actionEvent -> {
            if (isChanging) {
                return;
            }

            double valueFromEF;
            try {
                valueFromEF = Double.parseDouble(etCMYKk.getText());
                if (valueFromEF < 0) {
                    valueFromEF = 0;
                } else if (valueFromEF > 1) {
                    valueFromEF = 1;
                }

            } catch (NumberFormatException e) {
                valueFromEF = 0;
            }

            double c = sliderCMYKc.getValue();
            double m = sliderCMYKm.getValue();
            double y = sliderCMYKy.getValue();
            double k = valueFromEF;
            parameterChangedCMYK(c, m, y, k);
        });

        parameterChangedRGB(0, 0, 0);
    }

    public void parameterChangedRGB(int r, int g, int b) {

        isChanging = true;

        Color color = Color.rgb(r, g, b);
        RGB rgb = new RGB(r, g, b);
        CMYK cmyk = colorConverter.convertRGBtoCMYK(rgb);
        XYZ xyz = colorConverter.convertRGBtoXYZ(rgb);

        rectColorArea.setFill(color);
        colorPicker.setValue(color);

        resetParameters(rgb, cmyk, xyz);

        isChanging = false;
    }

    public void parameterChangedCMYK(double c, double m, double y, double k) {

        isChanging = true;

        CMYK cmyk = colorConverter.toDoublePlacesCMYK(new CMYK(c, m, y, k));
        RGB rgb = colorConverter.convertCMYKToRGB(cmyk);
        XYZ xyz = colorConverter.convertCMYKtoXYZ(cmyk);

        Color color = Color.rgb(rgb.getR(), rgb.getG(), rgb.getB());

        rectColorArea.setFill(color);
        colorPicker.setValue(color);

        resetParameters(rgb, cmyk, xyz);

        isChanging = false;
    }

    public void parameterChangedXYZ(double x, double y, double z) {

        isChanging = true;

        XYZ xyz = colorConverter.toDoublePlacesXYZ(new XYZ(x, y, z));
        RGB rgb = colorConverter.convertXYZToRGB(xyz);

        int r = rgb.getR();
        int g = rgb.getG();
        int b = rgb.getB();

        if (r > 255 || g > 255 || b > 255) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid convert XYZ to RGB!");
            alert.setHeaderText(String.format("Invalid RGB(%d, %d, %d)!", r, g, b));
            alert.setContentText(String.format("Trying convert XYZ(%.6f, %.6f, %.6f).", x, y, z));
            alert.showAndWait();

            isChanging = false;
            return;
        }

        CMYK cmyk = colorConverter.convertXYZtoCMYK(xyz);

        Color color = Color.rgb(r, g, b);

        rectColorArea.setFill(color);
        colorPicker.setValue(color);

        resetParameters(rgb, cmyk, xyz);

        isChanging = false;
    }


    public void parameterChangedColorPicker(ActionEvent e) {

        Color color = colorPicker.getValue();

        BigDecimal bd = new BigDecimal(Double.toString(color.getRed() * 255));
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        int r = (int) bd.doubleValue();

        bd = new BigDecimal(Double.toString(color.getGreen() * 255));
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        int g = (int) bd.doubleValue();

        bd = new BigDecimal(Double.toString(color.getBlue() * 255));
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        int b = (int) bd.doubleValue();

        RGB rgb = new RGB(r, g, b);
        CMYK cmyk = colorConverter.convertRGBtoCMYK(rgb);
        XYZ xyz = colorConverter.convertRGBtoXYZ(rgb);

        rectColorArea.setFill(color);
        colorPicker.setValue(color);

        resetParameters(rgb, cmyk, xyz);
    }

    private void resetParameters(RGB rgb, CMYK cmyk, XYZ xyz) {

        sliderRGBr.setValue(rgb.getR());
        sliderRGBg.setValue(rgb.getG());
        sliderRGBb.setValue(rgb.getB());

        etRGBr.setText(String.valueOf(rgb.getR()));
        etRGBg.setText(String.valueOf(rgb.getG()));
        etRGBb.setText(String.valueOf(rgb.getB()));

        sliderCMYKc.setValue(cmyk.getC());
        sliderCMYKm.setValue(cmyk.getM());
        sliderCMYKy.setValue(cmyk.getY());
        sliderCMYKk.setValue(cmyk.getK());

        etCMYKc.setText(String.format("%.6f", cmyk.getC()));
        etCMYKm.setText(String.format("%.6f", cmyk.getM()));
        etCMYKy.setText(String.format("%.6f", cmyk.getY()));
        etCMYKk.setText(String.format("%.6f", cmyk.getK()));

        sliderXYZx.setValue(xyz.getX());
        sliderXYZy.setValue(xyz.getY());
        sliderXYZz.setValue(xyz.getZ());

        etXYZx.setText(String.format("%.6f", xyz.getX()));
        etXYZy.setText(String.format("%.6f", xyz.getY()));
        etXYZz.setText(String.format("%.6f", xyz.getZ()));
    }
}