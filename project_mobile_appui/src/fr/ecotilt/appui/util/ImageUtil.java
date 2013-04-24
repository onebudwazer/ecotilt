package fr.ecotilt.appui.util;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import fr.ecotilt.appui.rsc.Ressources;

public class ImageUtil {

	private URL					url;
	private static final int	SIZE_IMG	= 400;

	/**
	 * Chemin de la ressource IMAGE en rapport au projet appui
	 * @param path
	 */
	public ImageUtil(String path) {
		url = Ressources.class.getResource(path);
	}

	/**
	 * Donne l'instance BufferedImage Original
	 * 
	 * @return
	 */
	public BufferedImage getInstanceBufferedImage() {
		try {
			BufferedImage originalImage = ImageIO.read(url);
			return originalImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getImgToByteScalr() {
		BufferedImage originalImage = getInstanceBufferedImage();
		BufferedImage finalImage = Scalr.resize(originalImage,
				Method.AUTOMATIC, SIZE_IMG, Scalr.OP_ANTIALIAS,
				Scalr.OP_BRIGHTER);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(finalImage, "jpg", baos);
			byte[] imageInByte = baos.toByteArray();
			baos.flush();
			return imageInByte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getImgToByteOriginal() {
		BufferedImage originalImage = getInstanceBufferedImage();
		@SuppressWarnings("unused")
		Dimension originSizeImg = new Dimension(originalImage.getWidth(),
				originalImage.getHeight());
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// ImageIO.write(finalImage, "png", new
			// File("C:\\eclipse\\test.png"));
			ImageIO.write(originalImage, "png", baos);
			byte[] imageInByte = baos.toByteArray();
			baos.flush();
			return imageInByte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private static BufferedImage resizeImage(BufferedImage originalImage,
			Dimension size) {

		BufferedImage resizedImage = new BufferedImage((int) size.getWidth(),
				(int) size.getHeight(), originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, (int) size.getWidth(),
				(int) size.getHeight(), null);
		g.dispose();
		return resizedImage;
	}

	@SuppressWarnings("unused")
	private BufferedImage resizeImageWithHint(BufferedImage originalImage,
			Dimension size) {

		BufferedImage resizedImage = new BufferedImage((int) size.getWidth(),
				(int) size.getHeight(), originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();

		g.drawImage(originalImage, 0, 0, (int) size.getWidth(),
				(int) size.getHeight(), null);

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_SPEED);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.dispose();
		return resizedImage;
	}

	public Dimension keepRatio(Dimension origin, Dimension bound) {

		int originalWidth = (int) origin.getWidth();
		int originalHeight = (int) origin.getHeight();

		int boundWidth = (int) bound.getWidth();
		int boundHeight = (int) bound.getHeight();

		int newWidth = originalWidth;
		int newHeight = originalHeight;

		if (originalWidth > boundWidth) {
			newWidth = boundWidth;
			newHeight = (newWidth * originalHeight) / originalWidth;
		}

		if (originalHeight > boundHeight) {
			newHeight = boundHeight;
			newWidth = (newHeight * originalWidth) / originalHeight;
		}

		return new Dimension(newWidth, newHeight);
	}

	public static void main(String[] args) throws URISyntaxException {
		System.out.println("*************************************");
		ImageUtil instance = new ImageUtil("/fr/project/appui/rsc/server.png");
		instance.getImgToByteOriginal();
	}

	// System.out.println(generateByteImg("C:\\eclipse\\test.jpg"));
	// public byte[] extractBytes (String ImageName) throws IOException {
	// File imgPath = new File(ImageName);
	// BufferedImage bufferedImage = ImageIO.read(imgPath);
	// WritableRaster raster = bufferedImage .getRaster();
	// DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
}
