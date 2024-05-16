package board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pattern extends Scale{
	
	public int x, y, size;
	public BufferedImage img;
	
	public Pattern(int x, int y, int size) {
		super(x, y, size);
	}
	
	public BufferedImage getImg(String pattern) throws IOException {
		if (pattern == "diag") {
			img = ImageIO.read(new File("pic/diag.jpg"));
		} else if (pattern == "horiz") {
			img = ImageIO.read(new File("pic/horiz.jpg"));
		} else if (pattern == "vertic") {
			img = ImageIO.read(new File("pic/vertic.jpg"));
		} 
		return img;
	}
	
}
