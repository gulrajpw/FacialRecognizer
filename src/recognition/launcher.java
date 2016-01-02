package recognition;

import org.opencv.core.Core;

public class launcher {

	public static void main(String args[])
	{
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    System.out.println("Loading system .. \n");
	    detection faceDetection = new detection();
	    faceDetection.calculate();
	    
	}
	
}
