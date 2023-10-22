package PhotoEdit;


import user.Photo;


/**
 * The Blur class provides functionality to apply a blur effect to a Photo object.
 */

public class Blur {
	
	/**
     * Applies a blur effect on a Photo object.
     *
     * @param photo     The Photo object to apply the blur effect to.
     * @param blurLevel The level of blurriness that is determined by the user through a JSlider.
     */
    
    public static void applyBlur(Photo photo, int blurLevel) {
    	
    	// The photo is converted to an ImageMatrix
        ImageMatrix imageMatrix = photo.convertToImageMatrix();
        
        //The blur effect is applied on the ImageMatrix
        ImageMatrix blurredImage = applyBlurIM(imageMatrix, blurLevel);
        
        //The blurred image is written back to the photo object 
        ImageSecretary.writeFilteredImageToResources(blurredImage, photo.getPath());
        
        
    }
    
    static ImageMatrix applyBlurIM(ImageMatrix image, int blurLevel) {
     
    	int width = image.getWidth();
        int height = image.getHeight();
        int[][] pixels = new int[width][height];
        
        // Original image pixels are copied to an array
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = image.getRGB(i, j);
            }
        }
        
        // Blurring is applied on this new array
        //Two for loops are used to cover all the pixels by addressing them indexes for both dimensions
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int[] rgbSum = {0, 0, 0};
                int numPixels = 0;
                
                //The nested for loops are used to iterate over a square neighborhood of pixels around the current pixel.
                //The isValidPixel() method is called to check if the current neighbor pixel is within the valid bounds.
                //The RGB values of the valid pixels are extracted using the getRGB() method.
                //After processing all the neighbor pixels, the average RGB values are calculated.
                //The average RGB values are used to create a new blurred pixel value using the ImageMatrix.convertRGB() method.
                
                for (int k = i - blurLevel; k <= i + blurLevel; k++) {
                    for (int l = j - blurLevel; l <= j + blurLevel; l++) {
                        if (isValidPixel(k, l, width, height)) {
                            int pixel = image.getRGB(k, l);
                            rgbSum[0] += (pixel >> 16) & 0xFF; // Red
                            rgbSum[1] += (pixel >> 8) & 0xFF; // Green
                            rgbSum[2] += pixel & 0xFF; // Blue
                            numPixels++;
                        }
                    }
                }
                
                int avgRed = rgbSum[0] / numPixels;
                int avgGreen = rgbSum[1] / numPixels;
                int avgBlue = rgbSum[2] / numPixels;
                int blurredPixel = ImageMatrix.convertRGB(avgRed, avgGreen, avgBlue);
                pixels[i][j] = blurredPixel;
            }
        }
        
        // Create a new ImageMatrix with the blurred pixels
        ImageMatrix blurredImage = new ImageMatrix(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                blurredImage.setRGB(i, j, pixels[i][j]);
            }
        }
        
        return blurredImage;
    }
    
    /**
     * Checks if the specified pixel are within the image bounds.
     *
     * @param x      The x-coordinate of the pixel.
     * @param y      The y-coordinate of the pixel.
     * @param width  The width
     */
    
    private static boolean isValidPixel(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    
    
}
