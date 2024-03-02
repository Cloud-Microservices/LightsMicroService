package lightsmicroservice.utils;

import java.util.Arrays;

public class Validators {
    public static boolean isBrightnessValid(Integer brightness) {
        if (brightness == null || brightness < 0 || brightness > 100)
            return false;

        return true;
        }

    public static boolean isColorRGBValid(int[] colorRGB) {
        if (colorRGB == null || colorRGB.length != 3)
            return false;

        if(Arrays.stream(colorRGB).allMatch(value -> value < 0 || value > 255))
            return false;

        return true;
        }

}
