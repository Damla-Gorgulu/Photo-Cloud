package PhotoEdit;


import user.Photo;

public class Brightness {
	
	/**
	 * Applies the brightness filter to the photo object.
	 * @param photo The photo to  brighten
	 * @param brightnessLevel The brightness level to apply that is determine by the user 
	 */
	
public static void applyBrightness(Photo photo, int brightnessLevel) {
    	
        ImageMatrix imageMatrix = photo.convertToImageMatrix();
        ImageMatrix brightenedImage = applyBrightnessIM(imageMatrix, brightnessLevel);      
        ImageSecretary.writeFilteredImageToResources(brightenedImage, photo.getPath());     
        
    }

	/**
	 * Applies the brightness filter to the provided image matrix.
	 * @param imageMatrix The image matrix to brighten
	 * @param brightnessLevel The brightness level to apply that is determined by the user
	 * @return The brightened image matrix 
	 */
	public static ImageMatrix applyBrightnessIM(ImageMatrix imageMatrix, int brightnessLevel) {
	    int width = imageMatrix.getWidth();
	    int height = imageMatrix.getHeight();

	    ImageMatrix brightenedImage = new ImageMatrix(width, height);

	    // The brightness level is calculated
	    double modifier = Math.pow(2, brightnessLevel / 50.0) - 1;

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            int rgb = imageMatrix.getRGB(x, y);
	            int red = imageMatrix.getRed(x, y);
	            int green = imageMatrix.getGreen(x, y);
	            int blue = imageMatrix.getBlue(x, y);

	            // The brightness of each color component is adjusted individually
	            red = (int) (red + modifier * (255 - red));
	            green = (int) (green + modifier * (255 - green));
	            blue = (int) (blue + modifier * (255 - blue));

	            // Checks whether the RGB values are within a valid range.
	            red = Math.max(0, Math.min(255, red));
	            green = Math.max(0, Math.min(255, green));
	            blue = Math.max(0, Math.min(255, blue));

	            int newRGB = ImageMatrix.convertRGB(red, green, blue);
	            brightenedImage.setRGB(x, y, newRGB);
	        }
	    }

	    return brightenedImage;
	}

}

