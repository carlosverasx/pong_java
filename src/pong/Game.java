package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	//Dimensões da janela
	public static int WIDTH = 260;
	public static int HEIGHT = 180;
	public static int SCALE = 3;
	
	//Layer para renderizar os gráficos corretamente
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	//Instânciando o jogador e inimigo
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		this.addKeyListener(this);
		
		player = new Player(115, HEIGHT-8);
		enemy = new Enemy(115, 0); 
		ball = new Ball(115, 80);
	}

	public static void main (String args[]) {
		Game game = new Game();

		JFrame frame = new JFrame("Pong"); //Objeto do Java para criar janelas.
		frame.setResizable(false); //Impossibilita a redimensionar a janela.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando a janela fechar, a aplicação vai parar de rodar.
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null); //Centralizar a janela.
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		//Rederizando os gráficos
		BufferStrategy bufferstrategy = this.getBufferStrategy(); 
		if (bufferstrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graph = layer.getGraphics();
		graph.setColor(Color.black);
		graph.fillRect(0,0, WIDTH, HEIGHT);
		player.render(graph);
		enemy.render(graph);
		ball.render(graph);
		
		graph = bufferstrategy.getDrawGraphics();
		graph.drawImage(layer, 0,0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bufferstrategy.show(); //Parâmetro para mostrar tudo que está sendo redenzirado
	}
	
	@Override
	public void run() {
		while (true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	//Movimentação do Jogo
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Detecta quando a tecla mensionada é pressionada e executa movimento
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true; 
		} 
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Detecta quando a tecla mensionada para depressionada para o movimento
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
	}
	
}
