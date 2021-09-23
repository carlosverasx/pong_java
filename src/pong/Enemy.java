package pong;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Rectangle;

public class Enemy {
	
	public double x,y;
	public int width, height;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 8;
		
	}

	public void tick() { //Vai toda lógica do inimigo
		x += (Game.ball.x - x - 6) * 0.2;
		
		//Rectangle rect1 = new Rectangle((int) x, (int) y, width, height);
		
		//Sistema simples de colisão
		if (x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
				}
		else if (x < 0) {
			x = 0;
		}
	}
	
	public void render (Graphics graph) { //Função onde será programada a renderização
		graph.setColor(Color.RED);
		graph.fillRect((int)x, (int)y, width, height);
	}
}
