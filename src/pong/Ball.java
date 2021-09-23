package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Ball {
	
	public double x,y;
	public int width, height;
	
	public double directionx, directiony;
	public double speed = 1.7;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 7;
		this.height = 7;
		
		int angle = new Random().nextInt((145 - 45) + 46);
		
		directionx = Math.cos(Math.toRadians(angle));
		directiony = Math.sin(Math.toRadians(angle));
	}

	public void tick() { //Vai toda lógica do inimigo
		
		//Criar colisões com as paredes
		if (x+(directionx*speed) + width >+ Game.WIDTH) { 
			directionx *= -1;
		} else if (x + (directionx*speed) < 0){
			directionx *=  -1;
		}
		
		//Criar colisões no jogador e inimigo
		if (y >= Game.HEIGHT) {
			//Ponto do inimigo
			System.out.println("Ponto do Inimigo");
			new Game();
			return;
			
		} else if (y < 0) {
			//Ponto do jogador
			System.out.println("Ponto do Jogador");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int) (x+(directionx*speed)), (int) (y+(directiony*speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if (bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt((145 - 45) + 46);
			
			directionx = Math.cos(Math.toRadians(angle));
			directiony = Math.sin(Math.toRadians(angle));
			
			if (directiony > 0) {
				directiony *= -1;
			}
			
		} else if (bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt((145 - 45) + 46);
			
			directionx = Math.cos(Math.toRadians(angle));
			directiony = Math.sin(Math.toRadians(angle));
			
			if (directiony < 0) {
				directiony *= -1;
			}
			
		}
		
		x+= directionx*speed;
		y+= directiony*speed;
		
	}
	
	public void render (Graphics graph) { //Função onde será programada a renderização
		graph.setColor(Color.WHITE);
		graph.fillOval((int)x, (int)y, width, height);
	}
}
