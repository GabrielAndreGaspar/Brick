
public class Bola extends DesenhoAnimado {
	private double movX = 1;
	private double MovY = 1;
	private Thread t = new Thread(this);
	private boolean play = false;
	
	public Bola() {}	
	
	public Bola(double x, double y, double l, double a, String path) {
		super(x, y, l, a, path);
		t.start();
		
	}


	public double getMovY() {
		return MovY;
	}

	public void setMovY(double movY) {
		MovY = movY;
	}

	public double getMovX() {
		return movX;
	}

	public void setMovX(double movX) {
		this.movX = movX;
	}
	
	public void moveBola() {
		this.setX(getX() + getMovX());
		this.setY(getY() + getMovY());
		if(getX()  <= 0 || getX() >= 600 - 30) {
			setMovX(getMovX()*-1);
		}
		if(getY() <= 30) {
			setMovY(getMovY()*-1);
		}
		
		
	}
	
	public void run() {
		while(true) {
			if(play) {
				moveBola();
			}
			
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean getPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}
	
	public void setDif(double x) {
		if(getMovX() < 0) {
			setMovX(x*-1);
		}
		else {
			setMovX(x);
		}
		if(getMovY() < 0) {
			setMovY(x*-1);
		}
		else{
			setMovY(x);
		}
	}

}
