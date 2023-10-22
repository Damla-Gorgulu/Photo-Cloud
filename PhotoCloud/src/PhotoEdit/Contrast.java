package PhotoEdit;

import user.Photo;


public class Contrast {
	/**
	 * The provided photo is contrasted
	 * @param photo The photo to be contrasted
	 * @param contrastLevel The contrast level to apply that is determined by the user
	 */
public static void applyContrast(Photo photo, int contrastLevel) {
    	
        ImageMatrix imageMatrix = photo.convertToImageMatrix();
        ImageMatrix contrastedImage = applyContrastIM(imageMatrix, contrastLevel);        
        ImageSecretary.writeFilteredImageToResources(contrastedImage, photo.getPath());     
        
    }


	/**
	 * Applies the contrast filter to the provided image matrix.
	 * @param imageMatrix The image matrix to apply the contrast adjustment to.
	 * @param contrastLevel The contrast level to apply (-100 to 100).
	 * @return The resulting image matrix after applying the contrast adjustment.
	 */
    public static ImageMatrix applyContrastIM(ImageMatrix imageMatrix, int contrastLevel) {
        int width = imageMatrix.getWidth();
        int height = imageMatrix.getHeight();

        ImageMatrix contrastedImage = new ImageMatrix(width, height);

        // The contrast modifier is calculated
        double modifier = Math.pow((contrastLevel + 120) / 100.0, 2);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = imageMatrix.getRGB(x, y);
                int red = imageMatrix.getRed(x, y);
                int green = imageMatrix.getGreen(x, y);
                int blue = imageMatrix.getBlue(x, y);

                // The contrasted component of each color is adjusted individually
                red = adjustContrast(red, modifier);
                green = adjustContrast(green, modifier);
                blue = adjustContrast(blue, modifier);

                int newRGB = ImageMatrix.convertRGB(red, green, blue);
                contrastedImage.setRGB(x, y, newRGB);
            }
        }

        return contrastedImage;
    }
    
	/**
	 * The contrast of a color component is adjusted based on the modifier that is calculated.
	 * @param value The original color component value (0-255).
	 * @param modifier The contrast modifier.
	 * @return The adjusted color component value.
	 */

    private static int adjustContrast(int value, double modifier) {

        double normalizedValue = value / 255.0;
        double centeredValue = normalizedValue - 0.5;
        double adjustedValue = centeredValue * modifier;
        double normalizedAdjustedValue = adjustedValue + 0.5;
        int finalValue = (int) (normalizedAdjustedValue * 255.0);
        return Math.max(0, Math.min(255, finalValue));
    }
}

