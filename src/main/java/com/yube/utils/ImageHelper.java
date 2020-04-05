package com.yube.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ImageHelper {

    public static Image cropCentralPart(Image image, double widthPercent, double heightPercent) {
        if (widthPercent < 0 || widthPercent > 1 || heightPercent < 0 || heightPercent > 1)
            throw new IllegalArgumentException("widthPercent and heightPercent should be in range from 0 to 1");
        double width = image.getWidth();
        double height = image.getHeight();
        double newWidth = width * widthPercent;
        double newHeight = height * heightPercent;
        double widthDiff = width - newWidth;
        double heightDiff = height - newHeight;
        return cropImage(image, (int) (widthDiff / 2), (int)(heightDiff / 2), (int) newWidth, (int) newHeight);
    }

    public static Image cropImage(Image image, int x, int y, int width, int height) {
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, x, y, width, height);
    }
}
