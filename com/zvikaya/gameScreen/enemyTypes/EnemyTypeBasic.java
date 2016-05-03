package com.zvikaya.gameScreen.enemyTypes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.zvikaya.Timer.Timer;
import com.zvikaya.display.Display;
import com.zvikaya.enemyBullets.EnemyBasicBullet;
import com.zvikaya.gameScreen.BasicBlocks;
import com.zvikaya.gameScreen.GameScreen;
import com.zvikaya.gameScreen.Player;
import com.zvikaya.handler.EnemyBulletHandler;
import com.zvikaya.sound.Sound;
import com.zvikaya.sprite.SpriteAnimation;

public class EnemyTypeBasic extends EnemyType{
	
	private Rectangle rect;
	private SpriteAnimation enemySprite;
	private double speed = 1.0d;
	
	private int shootTime;
	private Timer shootTimer;
	
	private Sound explosionSound;
//	
//	
//	
//	
	public EnemyTypeBasic(double xPos,double yPos,int row,int columns, EnemyBulletHandler bulletHandler ){//int columns){
		super(bulletHandler);
		enemySprite  = new SpriteAnimation(xPos, yPos,row,columns,300,"/com/zvikaya/images/Invaders.png");
		enemySprite.setWidth(25);
		enemySprite.setHieght(25);
		
		
		enemySprite.setLimit(2);
		
		this.setRect(new Rectangle((int)enemySprite.getxPos(),(int)enemySprite.getyPos(),enemySprite.getWidth(),enemySprite.getHieght()));
		enemySprite.setLoop(true);
		
		shootTimer = new Timer();
		shootTime = new Random().nextInt(12000);
		
		explosionSound = new Sound("/com/zvikaya/sounds/explosion.wav");
	}
//	
//
//	@Override
	public void draw(Graphics2D g) {
		enemySprite.draw(g);
	}
//
//	@Override
	public void update(double delta, Player player, BasicBlocks blocks) {
		enemySprite.update(delta);
		
		enemySprite.setxPos(enemySprite.getxPos() - (delta*speed));
		this.getRect().x = (int)enemySprite.getxPos();
		
		if(shootTimer.timeEvent(shootTime)){
			getBulletHandler().addBullet(new EnemyBasicBullet(getRect().x, getRect().y));
			shootTime = new Random().nextInt(1200);
		}
	}
//
//	@Override
	public void changeDirection(Double delta) {
		speed *= -1.15d;//Enemy goose in opposite  direction
		enemySprite.setxPos(enemySprite.getxPos()-(delta * speed));
		this.getRect().x = (int)enemySprite.getxPos();
		
		enemySprite.setyPos(enemySprite.getyPos()+(delta *15));//Moving down
		this.getRect().y = (int)enemySprite.getyPos();
	}
//
//	@Override
	public boolean deathScene() {
	
		if(!enemySprite.isPlay()){
			return false;
		}
		///////////////////////////////
		if(enemySprite.isPrintAnimetionDestroyed()){
			if(!explosionSound.isPlaying()){
				
				explosionSound.play();
			}
			return true;
		}
		///////////////////
		return false;
	}
//
//	@Override
	public boolean collide(int i, Player player, BasicBlocks blocks,ArrayList<EnemyType> enemys) {
		if(enemySprite.isPlay()){
			if(enemys.get(i).deathScene()){
				enemys.remove(i);
			}
			return false;
		}
		
		
		for(int w=0;w<player.playerWeaponds.weaponds.size();w++){//bullet collide with monster!!
			if(enemys != null && player.playerWeaponds.weaponds.get(w).collisionRect(((EnemyTypeBasic)enemys.get(i)).getRect())){
				enemySprite.resetLimit();
				enemySprite.setAnimationSpeed(120);
				enemySprite.setPlay(true, true);
				GameScreen.SCORE++;
				
				return true;
			}
		}
		
		
		return false;
	}
//
//	@Override
	public boolean isOutOfBounds() {
		if(rect.x> 0 && rect.x < Display.WIDTH - rect.width)
			return false;
			
			
		return true;
//		return false;
		
	}
//
//
	public Rectangle getRect() {
		return rect;
	}
//
//
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	

}
