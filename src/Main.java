import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame implements KeyListener, ActionListener{
	private Tela tela;
	private Som som;
	private Barra barra;
	private Bola bola;
	private Timer timer;
	private Desenho kappa;
	private int pontuaçao = 0;
	private boolean play = true;
	Random gerador = new Random();
	static final long serialVersionUID = 1L;
	ArrayList<Bloco> blocosAzul = new ArrayList<Bloco>();
	ArrayList<Bloco> blocosVerm = new ArrayList<Bloco>();
	ArrayList<Bloco> blocosVerd = new ArrayList<Bloco>();
	


	public boolean getPlay() {
		return play;
	}


	public void setPlay(boolean play) {
		this.play = play;
	}


	public int getPontuaçao() {
		return pontuaçao;
	}


	public void setPontuaçao(int pontuaçao) {
		this.pontuaçao = pontuaçao;
	}


	public Main(){
		int x = gerador.nextInt(570);
		if(x==0) {
			x++;
		}
		int y = gerador.nextInt(2);
		som = new Som("arquivos/fundo1.mp3");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela = new Tela(0,0, 600,600, "arquivos/fundo.png");
		kappa = new Desenho(100, 40, 400, 543, "arquivos/kappa fundo.png");
		barra = new Barra(250, 550, 100, 11, "arquivos/barra vermelha.png");
		bola = new Bola(x, 270, 30, 35, "arquivos/kappa ball.png");
		for(int k = 1; k < 9; k++){
			blocosVerm.add(new Bloco((k*60), 100, 50, 50, "arquivos/Bloco vermelho.png"));
		}
		for(int k = 1; k < 9; k++){
			blocosAzul.add(new Bloco((k*60), 160, 50, 50, "arquivos/Bloco azul.png"));
		}
		for(int k = 1; k < 9; k++){
			blocosVerd.add(new Bloco((k*60), 220, 50, 50, "arquivos/Bloco verde.png"));
		}
		if(y == 0) {
			bola.setMovX(bola.getMovX()*-1);
		}
		setResizable(false);
		this.addKeyListener(this);
		timer = new Timer(5, this);
		timer.start();
		som.start();
	}

	
	public static void main(String[] args) {
		
		Main t = new Main();
		t.setTitle("Brick");
		t.setSize(600,600);
		t.createBufferStrategy(1);		
		t.setVisible(true);
		t.createBufferStrategy(2);
		t.setLocationRelativeTo(null);
		
	}
	
	public void renderizarGraphics() {
		if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
	    Graphics g = getBufferStrategy().getDrawGraphics();
	     
	    Graphics g2 = g.create();
	    g2.setColor(Color.WHITE);        
	    g2.fillRect(0, 0, getWidth(), getHeight());
	    
	    renderizarImagens(g2);
	    renderizarTexto(g2);
	     
	    g.dispose(); 
	    g2.dispose();
	}
	
	public void renderizarImagens(Graphics g2) {
		tela.desenhar(g2);
		if(getPontuaçao() == 360) {
			kappa.desenhar(g2);
		}
		
		
		if(getPontuaçao() != 360) {
			bola.desenhar(g2);
			barra.desenhar(g2);
		}
		
		
		for(int i = 0; i < blocosVerm.size(); i++) {
			blocosVerm.get(i).desenhar(g2);
		}
		for(int i = 0; i < blocosAzul.size(); i++) {
			blocosAzul.get(i).desenhar(g2);
		}
		for(int i = 0; i < blocosVerd.size(); i++) {
			blocosVerd.get(i).desenhar(g2);
		}
	}
	
	public void renderizarTexto(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 25 ));
		g.drawString(""+getPontuaçao(), 550,50);
		
		if(bola.getPlay() == false && getPlay() == true) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30 ));
			g.drawString("- Aperte ENTER -", 180,340);
			g.setFont(new Font("serif", Font.BOLD, 15 ));
		}
		if(bola.getY() >= 600 - 40) {
			bola.setPlay(false);
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30 ));
			g.drawString("  - Game Over -", 190,300);
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20 ));
			g.drawString("  Pontuação: "+getPontuaçao()  , 225,330);
			g.drawString("- Aperte ESPAÇO para jogar novamente -", 120,360);
		}
		if(getPontuaçao() == 360) {
			bola.setPlay(false);
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30 ));
			g.drawString(" - Você Venceu -", 190,300);
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20 ));
			g.drawString("  Pontuação: "+getPontuaçao()  , 225,330);
			g.drawString("- Aperte ESPAÇO para jogar novamente -", 120,360);
		}
	}
	
	public void paint(Graphics g) {
		this.renderizarGraphics();
		repaint();
		
	}
	
	public void keyPressed(KeyEvent evt) {

		if(bola.getPlay() == false) {
			
			if (evt.getKeyCode() == KeyEvent.VK_SPACE){
				int x = gerador.nextInt(570);
				if(x==0) {
					x++;
				}
				int y = gerador.nextInt(2);
				blocosVerm.clear();
				blocosAzul.clear();
				blocosVerd.clear();
	        	bola.setPlay(false);
	        	tela = new Tela(0,0, 600,600, "arquivos/fundo.png");
	    		barra = new Barra(250, 550, 100, 11, "arquivos/barra vermelha.png");
	    		bola = new Bola(x, 270, 30, 35, "arquivos/kappa ball.png");
	    		for(int k = 1; k < 9; k++){
	    			blocosVerm.add(new Bloco((k*60), 100, 50, 50, "arquivos/Bloco vermelho.png"));
	    		}
	    		for(int k = 1; k < 9; k++){
	    			blocosAzul.add(new Bloco((k*60), 160, 50, 50, "arquivos/Bloco azul.png"));
	    		}
	    		for(int k = 1; k < 9; k++){
	    			blocosVerd.add(new Bloco((k*60), 220, 50, 50, "arquivos/Bloco verde.png"));
	    		}
	    		bola.setMovX(1);
	    		bola.setMovY(1);
	    		setPontuaçao(0);
	        	setPlay(true);
	        	if(y == 0) {
	    			bola.setMovX(bola.getMovX()*-1);
	    		}
	        	
	        }
		}
		if(getPlay() == true) {
			if (evt.getKeyCode() == KeyEvent.VK_ENTER){
	        	bola.setPlay(true);
	        	setPlay(false);
	        	
	        
	        	
	        }
		}
			
		if(bola.getPlay() == true) {
			if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
	        	barra.moverDireita();
	        	repaint();
	        }
	        
	        if (evt.getKeyCode() == KeyEvent.VK_LEFT){
	        	barra.moverEsquerda();
	        	repaint();
	        }
			
		}
		
        
	}
	public void colisoes() {
		int count = 0;
		if(bola.getY() < barra.getY() - 15 && bola.getMovY() >= 1) {
			if(bola.intersects(barra))  {
				bola.setMovY(bola.getMovY()*-1);
				repaint();
			}
			
		}
		
		
		for(int k = 0; k < blocosVerm.size(); k++) {
			if(bola.intersects(blocosVerm.get(k))) {
				count++;
				setPontuaçao(getPontuaçao() + 20);
				if(bola.getX() + 27.5  >= blocosVerm.get(k).getX() && bola.getX()  <= blocosVerm.get(k).getX() + 47.5) {
					if(count != 2) {
						bola.setMovY(bola.getMovY()*-1);
					}
				}
				else {
					if(count != 2) {
						bola.setMovX(bola.getMovX()*-1);
					}
				}
				blocosVerm.remove(k);
				repaint();
			}	
		}
		for(int k = 0; k < blocosAzul.size(); k++) {		
			if(bola.intersects(blocosAzul.get(k))) {
				count++;
				setPontuaçao(getPontuaçao() + 15);
				if(bola.getX() + 27.5  >= blocosAzul.get(k).getX() && bola.getX()  <= blocosAzul.get(k).getX() + 47.5) {
					if(count != 2) {
						bola.setMovY(bola.getMovY()*-1);
					}
				}
				else {
					if(count != 2) {
						bola.setMovX(bola.getMovX()*-1);
					}
				}
				blocosAzul.remove(k);
				repaint();
			}	
		} 
		for(int k = 0; k < blocosVerd.size(); k++) {
			if(bola.intersects(blocosVerd.get(k))) {
				count++;
				setPontuaçao(getPontuaçao() + 10);
				if( bola.getX() + 27.5  >= blocosVerd.get(k).getX() && bola.getX()  <= blocosVerd.get(k).getX() + 47.5 ) {
					if(count != 2) {
						bola.setMovY(bola.getMovY()*-1);
					}
					
				}
				
				else {
					if(count != 2) {
						bola.setMovX(bola.getMovX()*-1);
					}
					
					
				}
				blocosVerd.remove(k);
				repaint();
			}
			
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dificuldade(); 
		colisoes();
		
		
	}
	public void dificuldade() {
		
		if(getPontuaçao() >= 100 && getPontuaçao() < 250) {
			bola.setDif(1.20);
		}
		if(getPontuaçao() >= 250){
			bola.setDif(1.5);

		}
	}
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}
