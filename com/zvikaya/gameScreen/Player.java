package com.zvikaya.gameScreen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.zvikaya.display.Display;

public class Player implements KeyListener{

	
	private final double speed = 5.0d;
	private int health;
	
	
	private BufferedImage pSprite;
	private Rectangle rect;//for collision
	private double xPos,yPos , startXpos,startYpos;
	private int width , height;
	private BasicBlocks blocks;
	
	private boolean left = false , right =false, shoot = false;
	
	
	public PlayerWeaponds playerWeaponds;
	
	
	public Player(double xPos,double yPos, int width,int height,BasicBlocks blocks){
		this.xPos = xPos;
		this.yPos = yPos;
		this.startXpos = xPos;
		this.startYpos = yPos;
		
		this.width = width;
		this.height = height;
		this.health =3;
//		
		rect = new Rectangle((int)xPos,(int) yPos+25,width,height-25);
//		
		try{
			URL ur1 = this.getClass().getResource("/com/zvikaya/images/player1.png");
			pSprite = ImageIO.read(ur1);
		}catch(IOException e ){};
		
		this.blocks = blocks;
		playerWeaponds = new PlayerWeaponds();
	}
//	
	public void draw(Graphics2D g){
		g.drawImage(pSprite, (int)xPos, (int)yPos, width, height, null);
		playerWeaponds.draw(g);
		
	}
//	
	public void update(double delta){
//		
		
		if(right && !left && xPos < Display.WIDTH -width){
			xPos += speed*delta;
			rect.x = (int)xPos;
		}
			
		if(!right && left && xPos>10){
			xPos -= speed*delta;
			rect.x=(int)xPos;
		}
		
		playerWeaponds.update(delta, blocks);
		
//		if(right && !left && xPos < Display.WIDTH -width){
//			xPos+=speed * delta;
//			rect.x = (int)xPos;
//		}
//		if(!right && left && xPos>10){
//			xPos -= speed * delta;
//			rect.x = (int)xPos;
//		}
//		playerWeaponds.update(delta, blocks);
//		
		if(shoot){
			playerWeaponds.shootBullet(xPos, yPos, 5, 5);
		}
	}
//	
	public void keyPressed(KeyEvent e) {
//
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT ){
				right = true;
		}
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT ){
//			if(xPos > 10) // borders
				left = true;
		}
		 if(key == KeyEvent.VK_SPACE){//System.out.println("cccc");
			shoot = true;
		}
		
	}
//
//
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT ){
				right = false;
		}
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT ){
				left = false;
		}	
		 if(key == KeyEvent.VK_SPACE){
			shoot = false;
		}
	}
//
//
	public void keyTyped(KeyEvent e) {

		
	}
	public void hit(){
		setHealth(getHealth()-1);
	}
	public int getHealth(){  
		return health;
	}
	
	public void setHealth(int helt){
		this.health = helt;
	}
	
	public Rectangle getRect(){
		return rect;
	}
	public void reset() {
		health= 3;
		left = false;
		right = false;
		shoot = false;
		
		xPos = startXpos;
		yPos = startYpos;
		rect.x= (int) xPos;
		rect.y= (int)yPos+25;
		playerWeaponds.reset();
		
	}

}
