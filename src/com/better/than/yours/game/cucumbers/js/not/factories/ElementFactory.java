package com.better.than.yours.game.cucumbers.js.not.factories;

import javafx.scene.control.Slider;

/**
 * Created by mati on 2016-05-14.
 */
public class ElementFactory {
    static public Slider makeSlider(int minVal, int maxVal, int defaultVal, int majorTick, int minorTick){
        Slider slider = new Slider();
        slider.setMin(minVal);
        slider.setMax(maxVal);
        slider.setValue(defaultVal);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(majorTick);
        slider.setMinorTickCount(minorTick);
        slider.setBlockIncrement(10);
        slider.setMinWidth(500);
        slider.setMinHeight(100);
        return slider;
    }
}
