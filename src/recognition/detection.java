package recognition;


import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.rectangle;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.objdetect.CascadeClassifier;



public class detection {
	
	//algorithm for face detection.
	public void calculate()
	{
		 CascadeClassifier faceTemplate = new CascadeClassifier("lbpcascade_frontalface.xml");
		 
		
		 
		 Mat image = imread("3636984-smiling-young-professional-pointing-at-you.jpg");
		 
		 
		 // Detect faces in the image.
		 // MatOfRect is a special container class for Rect.
		 MatOfRect faceDetections = new MatOfRect();
		 faceTemplate.detectMultiScale(image, faceDetections);
		 System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		 
		 
		     //Draw a bounding box around each face.
		    for (Rect rect : faceDetections.toArray()) 
		    {
		        rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		    }
		 
		    
		    String filename = String.format("newFaceDetectionCreated.jpg");
		    File file = new File(filename);
		    filename = file.toString();
		    
		  
		    imwrite(filename, image);
		    
		    //Convert the Mat object to a BufferedImage to display on the jFrame.
		    BufferedImage imgToShow = bufferedImage(image);
		    
		    JFrame imagePane = new JFrame("Faces Detected");
		    imagePane.add(new JLabel(new ImageIcon(imgToShow)));
		    imagePane.pack();
		    imagePane.setVisible(true);
		    
		   System.out.println("Image Written \n");
		 
		 
	}
	
	//   Referenced question from stackoverflow:
	//   http://stackoverflow.com/questions/30258163/display-image-in-mat-with-jframe-opencv-3-00
	public static BufferedImage bufferedImage(Mat m) {
	    int type = BufferedImage.TYPE_BYTE_GRAY;
	    if ( m.channels() > 1 ) {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }
	    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	    m.get(0,0,((DataBufferByte)image.getRaster().getDataBuffer()).getData()); // get all the pixels
	    return image;
	}
	
	
	
}
