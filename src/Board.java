import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage gray;
	Image image;
	BufferedImage in;
	BufferedImage lpat;
	Rectangle ovals[]=new Rectangle[(int)Math.pow(10, 8)];
	int ovalCount;
	
	public Board() {
		try {
			image = ImageIO.read(new File("mel.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g=gray.getGraphics();
//		if (in!=null)
//			g.drawImage(in, 50, 50, in.getWidth()/3,in.getHeight()/3,null);
//		if (gray!=null)
//			g.drawImage(gray, in.getWidth()/3+150, 50, gray.getWidth()/3,gray.getHeight()/3,null);
		if (lpat!=null){
			g.drawImage(lpat, 50, 50,null);
		
		}
			//g.drawImage(lpat, 2*in.getWidth()/3+250, 50, lpat.getWidth()/3,lpat.getHeight()/3,null);
		
	}

	public BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
