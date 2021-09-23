package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean left, right;
	public int x, y;
	public int width, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 8;
		
	}

	public void tick() { //Lógica
		if (right) {
			x++;
		} else if (left) {
			x--;
		}
		
		
		//Sistema simples de colisão
		if (x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}
		else if (x < 0) {
			x = 0;
		}
	}
	
	public Graphics render(Graphics graph) { //Renderização
		graph.setColor(Color.WHITE);
		graph.fillRect(x, y, width, height);
		return graph;
	}
}
