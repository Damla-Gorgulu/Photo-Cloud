package PhotoEdit;

import user.Photo;

public class Sharpen {
	/**
	 * Apply sharpening filter to the given photo with the specified sharpening level.
	 * @param photo The photo to apply sharpening filter to.
	 * @param sharpenLevel The level of sharpening to apply.
	 */
	
public static void applySharpen(Photo photo, int sharpenLevel) {
    	
        ImageMatrix imageMatrix = photo.convertToImageMatrix();
        ImageMatrix sharpenedImage = applySharpenIM(imageMatrix, sharpenLevel);
        
        ImageSecretary.writeFilteredImageToResources(sharpenedImage, photo.getPath());     
        
    }

/**
 * Apply sharpening filter to the given image matrix with the specified sharpening level.
 * @param image The image matrix to apply sharpening filter to.
 * @param sharpenLevel The level of sharpening to apply.
 * @return The sharpened image matrix.
 */
public static ImageMatrix applySharpenIM(ImageMatrix image, int sharpenLevel) {
	
        // Blurring is applied to the image tby calling the applyBlurIM static method of the class Blur
        ImageMatrix blurredImage = Blur.applyBlurIM(image, sharpenLevel);

        //the width and height are retrieved.
        int width = image.getWidth();
        int height = image.getHeight();
        
        //A 2D array to stored the sharpened pixel values is initialized
        int[][] sharpenedPixels = new int[width][height];

        // Subtract the blurred image from the original image
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
            	
            	//The original pixel value is retrieved
                int originalPixel = image.getRGB(i, j);
                
                //The blurred pixel value is retrieved.
                int blurredPixel = blurredImage.getRGB(i, j);
                
                //Subtracted to from each other to display the details ıf the information
                int difference = originalPixel - blurredPixel;
                
                //The details of the photo magnified by the sharpenlevel is added to form the final image.
                int sharpenedPixel = originalPixel + difference * sharpenLevel;

                //This part was to check whether any of the pixels become completely red, green or blue. 
                int red = (sharpenedPixel >> 16) & 0xFF;
                int green = (sharpenedPixel >> 8) & 0xFF;
                int blue = sharpenedPixel & 0xFF;
                if (red <= 50 || green <= 50 || blue <= 50 || red >= 100 || green >= 100 || blue >= 100) {
                	// If the pixel is completely green, red or blue, the original pixel is used in place of
                	// the sharpened one when the Image Matrix is created.
                    sharpenedPixel = originalPixel; // Stop sharpening for this pixel
                }

                sharpenedPixels[i][j] = sharpenedPixel;
            }
        }

        // An image matrix is created with the modified pixels.
        ImageMatrix sharpenedImage = new ImageMatrix(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sharpenedImage.setRGB(i, j, sharpenedPixels[i][j]);
            }
        }

        return sharpenedImage;
    }
}





