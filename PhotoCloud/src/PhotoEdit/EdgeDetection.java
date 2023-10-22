package PhotoEdit;

import java.awt.Color;

import user.Photo;


public class EdgeDetection {
	
	/**
	 * Applies edge detection to the provided photo.
	 * @param photo The photo to apply edge detection to.
	 * @param edgedetectionLevel The edge detection level to apply.
	 */
	
public static void applyEdgeDetection(Photo photo, int edgedetectionLevel) {
    	
        ImageMatrix imageMatrix = photo.convertToImageMatrix();
        ImageMatrix edgedetectedImage = applyEdgeDetectionIM(imageMatrix, edgedetectionLevel);
        
        ImageSecretary.writeFilteredImageToResources(edgedetectedImage, photo.getPath());     
        
    }
	
	/**
	 * Applies edge detection to the provided image matrix.
	 * @param imageMatrix The image matrix to apply edge detection to.
	 * @param threshold The threshold for detecting edges.
	 * @return The resulting image matrix after applying edge detection.
	 */

	public static ImageMatrix applyEdgeDetectionIM(ImageMatrix imageMatrix, int threshold) {
	    int width = imageMatrix.getWidth();
	    int height = imageMatrix.getHeight();

	    ImageMatrix edgeImage = new ImageMatrix(width, height);

	    // Convert the image to grayscale
	    ImageMatrix grayscaleImage = GrayScale.applyGrayscaleIM(imageMatrix, 1.0);

	    // Two 3x3 kernels (sobelX and sobelY) that are defined to be used for the edge detection process. 
	    // These kernels represent the horizontal and vertical gradients.

	    int[][] sobelX = {
	        {-1, 0, 1},
	        {-2, 0, 2},
	        {-1, 0, 1}
	    };

	    int[][] sobelY = {
	        {-1, -2, -1},
	        {0, 0, 0},
	        {1, 2, 1}
	    };

	    //	The pixels detected as edges are colored white 
	    // 	Other pixels are preserved as their grayscaled versions
	    //	For each pixel, gradients are computed by convolving them with the sobelX and sobelY kernels.
	    
	    for (int x = 1; x < width - 1; x++) {
	        for (int y = 1; y < height - 1; y++) {
	            int gx = computeConvolution(grayscaleImage, sobelX, x, y);
	            int gy = computeConvolution(grayscaleImage, sobelY, x, y);

	            int gradient = (int) Math.sqrt(gx * gx + gy * gy);

	            if (gradient > threshold) {
	                edgeImage.setRGB(x, y, Color.WHITE.getRGB()); 
	            } else {
	                edgeImage.setRGB(x, y, grayscaleImage.getRGB(x, y)); 
	            }
	        }
	    }

	    return edgeImage;
	}

	/**
	 * Computes the convolution of a kernel with the image matrix at the specified position.
	 * @param imageMatrix The image matrix to compute the convolution on.
	 * @param kernel The kernel to use for convolution.
	 * @param x The x-coordinate of the pixel to compute convolution for.
	 * @param y The y-coordinate of the pixel to compute convolution for.
	 * @return The result of the convolution.
	 */

		// Convolution involves multiplying the kernel values with the corresponding pixel values in the neighborhood 
		// of the current pixel and summing them.
	private static int computeConvolution(ImageMatrix imageMatrix, int[][] kernel, int x, int y) {
	    int result = 0;
	    int kernelSize = kernel.length;

	    for (int i = 0; i < kernelSize; i++) {
	        for (int j = 0; j < kernelSize; j++) {
	            int pixel = imageMatrix.getRed(x + i - 1, y + j - 1);
	            int weight = kernel[i][j];
	            result += pixel * weight;
	        }
	    }

	    return result;
	}

}
