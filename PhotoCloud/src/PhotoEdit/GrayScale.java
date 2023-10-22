package PhotoEdit;

import user.Photo;

public class GrayScale {
	
	/**
	 * Apply grayscale filter to the given photo with the specified grayscale level.
	 * @param photo The photo to apply grayscale filter to.
	 * @param grayscaleLevel The level of grayscale to apply.
	 */
	
public static void applyGrayscale(Photo photo, int grayscaleLevel) {
    	
        ImageMatrix imageMatrix = photo.convertToImageMatrix();
        ImageMatrix grayscaledImage = applyGrayscaleIM(imageMatrix, grayscaleLevel);
        
        ImageSecretary.writeFilteredImageToResources(grayscaledImage, photo.getPath());     
        
    }
	
	/**
	 * Apply grayscale filter to the given image matrix with the specified grayscale level.
	 * @param imageMatrix The image matrix to apply grayscale filter to.
	 * @param modifier The modifier to adjust the grayscale intensity.
	 * @return The grayscaled image matrix.
	 */
    public static ImageMatrix applyGrayscaleIM(ImageMatrix imageMatrix, double modifier) {
    	
    	//The height and with of the original image matrix is retrieved.
        int width = imageMatrix.getWidth();
        int height = imageMatrix.getHeight();

        //A new image matrix with the original height and width is created.
        ImageMatrix grayscaleImage = new ImageMatrix(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	
            	
                int rgb = imageMatrix.getRGB(x, y);
                
                // Individual red green and blue values of the original matrix was extracted and stored.
                int red = imageMatrix.getRed(x, y);
                int green = imageMatrix.getGreen(x, y);
                int blue = imageMatrix.getBlue(x, y);
                
                //The RGB values are converted to grayscale using a weighted average method. 
                //The values of red, green, and blue are multiplied by the corresponding weights 
                //(0.2900, 0.580, 0.120) and summed to obtain a grayscale value gray.

                int gray = (int) (red * 0.2900 + green * 0.580 + blue * 0.120);
                int newRGB = ImageMatrix.convertRGB(gray, gray, gray);

                // The modifier is applied.
                int modifiedRGB = applyModifier(newRGB, modifier, 0.10);

                grayscaleImage.setRGB(x, y, modifiedRGB);
            }
        }

        return grayscaleImage;
    }


	/**
	 * Apply modifier to the RGB values based on the modifier determined by the User.
	 * @param rgb The RGB value to modify.
	 * @param modifier The modifier to adjust the RGB values.
	 * @param maxModifier The modification limit for each RGB component.
	 * @return The modified RGB value.
	 */
    public static int applyModifier(int rgb, double modifier, double maxModifier) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        red = (int) (red * modifier);
        green = (int) (green * modifier);
        blue = (int) (blue * modifier);

        // Limit the maximum value to 1/4 of the original value
        red = Math.min(red, (int) (red * maxModifier));
        green = Math.min(green, (int) (green * maxModifier));
        blue = Math.min(blue, (int) (blue * maxModifier));

        return ImageMatrix.convertRGB(red, green, blue);
    }
}


