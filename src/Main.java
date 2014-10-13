import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
	double[] hist = new double[256];
	File img;
	Board board;
	JFrame myFrame;
	Color cWhite=new Color(255,255,255);
	Color cBlack=new Color(0,0,0);
	int white;
	int black;
	Main() {
		white=cWhite.getRGB();
		black=cBlack.getRGB();
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		board = new Board();
		board.setVisible(true);
		board.setFocusable(true);
		myFrame.add(board);
		img = new File("mel.jpg");

		try {
			board.in = ImageIO.read(img);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main f = new Main();
		f.board.repaint();
		f.board.gray=f.grayscaleImage(f.board.in);
		f.board.lpat = f.board.deepCopy(f.board.gray);
		//f.calculateBinaryPattern(f.board.in);
		f.calculateCircularBinaryPattern(f.board.in);
		// System.out.println(Arrays.toString(f.hist));
		// Dimension d = new Dimension(1920, 1080);
		// myFrame.setSize(d);

	}

	private void calculateBinaryPattern(BufferedImage image) {
		// first convert to intensity only.
		int bin = 0;		
		// now fill histogram according to LBP definition.
		for (int x = 1; x < image.getWidth() - 1; x++) {
			for (int y = 1; y < image.getHeight() - 1; y++) {
				int cpx = image.getRGB(x, y);
				for (int i=x-1;i<=x+1;i++){
					for (int j=y-1;j<=y+1;j++){
						if (image.getRGB(i, j) >= cpx) {
							board.lpat.setRGB(i, j, white);
						} else {
							board.lpat.setRGB(i, j, black);
						}
						
					}
				}
				board.repaint();				
				
				bin = 0;
			}
		}	
	}
	
	private void calculateCircularBinaryPattern(BufferedImage image) {
		// first convert to intensity only.
		int bin = 0;		
		// now fill histogram according to LBP definition.
		for (int x = 5; x < image.getWidth() - 5; x+=3) {
			for (int y = 5; y < image.getHeight() - 5; y+=3) {
				int cpx = image.getRGB(x, y);
			
				for (int k=0;k<16;k++){
					int i=(int)(x+5*Math.sin(2*Math.PI*k/8));
					int j=(int)(y+5*Math.cos(2*Math.PI*k/8));				
					if (image.getRGB(i, j) >= cpx) {
							board.lpat.setRGB(i, j, white);
					} else {
							board.lpat.setRGB(i, j, black);
					}
					
					bin++;
				}
				board.repaint();					
			}
		}
		board.ovalCount=bin;
	}

	public BufferedImage grayscaleImage(BufferedImage img) {
		BufferedImage grayImg=board.deepCopy(img);
		for (int x = 0; x < img.getWidth(); ++x)
			for (int y = 0; y < img.getHeight(); ++y) {
				int rgb = img.getRGB(x, y);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);

				int grayLevel = (r + g + b) / 3;
				int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
				grayImg.setRGB(x, y, gray);
			}
		return grayImg;
	}

}
