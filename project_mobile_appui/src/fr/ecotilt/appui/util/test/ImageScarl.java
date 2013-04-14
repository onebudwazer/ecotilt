package fr.ecotilt.appui.util.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import fr.ecotilt.appui.util.ImageUtil;

public class ImageScarl {

	public static void main(String[] args) throws IOException {

		System.out.println("*************************************");
		ImageUtil instance = new ImageUtil("/fr/project/appui/rsc/2013.jpg");
		// BufferedImage scaledImage =
		// Scalr.resize(instance.getInstanceBufferedImage(), 400);
		BufferedImage scaledImage = Scalr.resize(
				instance.getInstanceBufferedImage(), Method.SPEED, 500,
				Scalr.OP_ANTIALIAS, Scalr.OP_BRIGHTER);
		File outputfile = new File("C:\\test.jpg");
		ImageIO.write(scaledImage, "jpg", outputfile);
	}
}
