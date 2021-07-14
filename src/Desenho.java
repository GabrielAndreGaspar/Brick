import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Desenho {
	private double x, y, altura, largura;
	private  BufferedImage img;
	public Desenho() {}
	
	public Desenho(double x, double y, double l, double a, String path) {
		this.setX(x);
		this.setY(y);
		this.setLargura(l);
		this.setAltura(a);
		
		File fileImg = new File(path);
		try {
			img = ImageIO.read(fileImg); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public BufferedImage getImg() {
		return img;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setImg(String path) {

		File fileImg = new File(path);
		try {
			img = ImageIO.read(fileImg);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void desenhar(Graphics g) {
			g.drawImage(this.getImg(), (int)this.getX(), (int)this.getY(), null);
	}
	
	public boolean intersects(Desenho r) {
		double tw = this.largura;
		double th = this.altura;
		double rw = r.largura;
		double rh = r.altura;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.x;
        double ty = this.y;
        double rx = r.x;
        double ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
	
}
