package board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BingoBoard extends Scale{
	
	public int x, y, size;
	public BufferedImage img;
	
	public BingoBoard(int x, int y, int size){
		super(x, y, size);
	}
	
	public BufferedImage getImg() throws IOException {
		img = ImageIO.read(new File("pic/board.jpg"));
		return img;
	}
}