
public class DesenhoMovel extends Desenho {

	public DesenhoMovel() {}
	
	public DesenhoMovel(double x, double y, double l, double a, String path) {
		super(x, y, l, a, path);
	}
	
	public void moverDireita() {
		if(this.getX() < 500) {
			this.setX(this.getX() + 20);
		}	
	}
	
	public void moverEsquerda() {
		if(this.getX() > 0 ) {
			this.setX(this.getX() - 20);
		}	
	}
	
	

}
