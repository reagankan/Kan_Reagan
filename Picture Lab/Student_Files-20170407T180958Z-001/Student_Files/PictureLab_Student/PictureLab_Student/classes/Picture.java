import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.io.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set the red and green to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
		pixelObj.setRed(0);
      }
    }
  }
  /** Method to negate */
  public void negate()
  {
    int currRed, currGreen, currBlue;
	Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        currRed = pixelObj.getRed();
		currGreen = pixelObj.getGreen();
		currBlue = pixelObj.getBlue();
		
		pixelObj.setRed(255-currRed);
		pixelObj.setGreen(255-currGreen);
		pixelObj.setBlue(255-currBlue);
      }
    }
  }
  /** Method to turn into shades of gray */
  public void grayscale()
  {
    int avg;
	Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3;
		pixelObj.setRed(avg);
		pixelObj.setGreen(avg);
		pixelObj.setBlue(avg);
      }
    }
  }
  /** Method to make fish more distinct */
  public void fixUnderwater()
  {
    int avg, currRed, currGreen, currBlue;
	Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        currRed = pixelObj.getRed();
		currGreen = pixelObj.getGreen();
		currBlue = pixelObj.getBlue();
		avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3;
		pixelObj.setRed(currBlue);
		//pixelObj.setGreen(currBlue);
		//pixelObj.setBlue(currBlue);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width; col++)
      {
		//width - 1 - col
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-row-1][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from bottom to top */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width; col++)
      {
		//width - 1 - col
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-row-1][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    } 
  }
  /** Method that mirrors the picture around a 
    * diagonal mirror 
    * from topleft to bottomright */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels.length; col++)
      {
        leftPixel = pixels[col][row];
        rightPixel = pixels[row][col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }    
  }
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
		count++;
      }
    }
	System.out.println("count: " + count);
  }
  /** 4 arm snowman */
  public void mirrorArms()
  {
    int leftPoint = 170;
	int rightPoint = 295;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // clone left arm
    for (int row = 153; row < 195; row++)
    {
      for (int col = 100; col < leftPoint; col++)
      {
        
        leftPixel = pixels[row][col];    
		rightPixel = pixels[row + 50][col-2];		
        rightPixel.setColor(leftPixel.getColor());
      }
    }
	// clone right arm
    for (int row = 169; row < 200; row++)
    {
      for (int col = 230; col < rightPoint; col++)
      {
        
        leftPixel = pixels[row][col];    
		rightPixel = pixels[row + 50][col+5];		
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  /** clone seagull */
  public void mirrorGull()
  {
    int rightLimit = 343;
    Pixel original = null;
    Pixel clone = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // clone gull to the left
    for (int row = 224; row < 325; row++)
    {
      for (int col = 225; col < rightLimit; col++)
      {
        
        original = pixels[row][col];    
		clone = pixels[row][col-120];		
        clone.setColor(original.getColor());
      }
    }
  }
    /** 
	* copy fromPic to center of this.pic 
    */
  public void copy(Picture fromPic, 
                 int startRow, int endRow, int startCol, int endCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
     for (int fromRow = startRow, toRow = (toPixels.length/2)-(endRow-startRow)/2; 
         fromRow < endRow && toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = startCol, toCol = (toPixels[toRow].length/2)-(endCol-startCol)/2; 
           fromCol < endCol && toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }    
  }
   /** 
	* copy fromPic to center of this.pic 
    */
  public void copyBottom(Picture grayImg, Picture negImg)
  {
    Pixel fromPixel = null;
	Pixel toPixel = null;
	Pixel[][] toPixels = grayImg.getPixels2D();
	Pixel[][] fromPixels = negImg.getPixels2D();
	for (int fromRow = fromPixels.length/2, toRow = toPixels.length/2; 
		 fromRow < fromPixels.length && toRow < toPixels.length; 
		 fromRow++, toRow++)
	{
	for (int fromCol = 0, toCol = 0; 
		   fromCol < fromPixels[fromRow].length && toCol < toPixels[toRow].length;  
		   fromCol++, toCol++)
	  {
		fromPixel = fromPixels[fromRow][fromCol];
		toPixel = toPixels[toRow][toCol];
		toPixel.setColor(fromPixel.getColor());
	  }
	}    
  }
  /** 
	* use combo of above functions
    */
	public void myCollage(Picture img)
	{
		Picture gray = new Picture(img.getFilename());
		Picture neg = new Picture(img.getFilename());
		
		gray.grayscale();
		neg.negate();
		gray.explore();
		//from neg to gray
		//WHY DOES THIS METHOD NOT NEED OBJECT?
		//because the parameters to the method are not referenced?
		copyBottom(gray, neg);
		
		gray.mirrorDiagonal();
		
		gray.explore();
	}
	/** A9 Exercise 1
	* Method to show large changes in color 
    * Adds top to bottom comparison
    */
  public Picture edgeDetection2(int edgeDist)
  {
    Picture temp = new Picture(this.getFilename());
	Pixel leftPixel = null, 
		  rightPixel = null,
		  topPixel = null,
		  bottomPixel = null;
    Pixel[][] pixels = temp.getPixels2D();
    Color rightColor = null, bottomColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
	for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; 
           row < pixels.length-1; row++)
      {
		topPixel = pixels[row][col];
        bottomPixel = pixels[row+1][col];
        bottomColor = bottomPixel.getColor();
        if (topPixel.colorDistance(bottomColor) > 
            edgeDist)
          topPixel.setColor(Color.BLACK);
      }
    }
	return temp;
  }
  /** A9 Exercise 2
	* Method to show large changes in color 
    * Adds top to bottom comparison
	* AND Diagonal comparison Top Left to Bottom Right
    */
  public Picture edgeDetection3(int edgeDist)
  {
    Picture temp = new Picture(this.getFilename());
	Pixel leftPixel = null, 
		  rightPixel = null;
	
    Pixel[][] pixels = temp.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
	Pixel topPixel = null,
		  bottomPixel = null;
	Color bottomColor = null;
	for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; 
           row < pixels.length-1; row++)
      {
		topPixel = pixels[row][col];
        bottomPixel = pixels[row+1][col];
        bottomColor = bottomPixel.getColor();
        if (topPixel.colorDistance(bottomColor) > 
            edgeDist)
          topPixel.setColor(Color.BLACK);
      }
    }
	/*
		leftPixel : TOP left pixel
		rightPixel : BOTTOM right pixel
	*/
	for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
		leftPixel = pixels[row][col];
        rightPixel = pixels[row+1][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
      }
    }
	return temp;
  }
	
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
	Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
