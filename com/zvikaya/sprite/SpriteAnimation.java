package com.zvikaya.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.zvikaya.Timer.Timer;

public class SpriteAnimation {

	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte currentSprite;
	
	
	private boolean loop = false;
	private boolean play = false;
	private boolean destroyAfterAnimtion = false;
	
	private Timer timer;
	private int animationSpeed;
	private double xPos,yPos;
	private int width,hieght;
	private int limit;
	
	public SpriteAnimation(double xPos,double yPos,int rows,int columns,int animationSpeed,String imPath){//, int rows,int columns,int animationSpeed, String imPath){
		
		this.animationSpeed = animationSpeed;
		this.xPos = xPos;
		this.yPos = yPos;
//		this.width = width;
//		this.hieght = height;
//		timer = new Timer();
		
		
		try{
			URL ur1 = this.getClass().getResource(imPath);
			BufferedImage pSprite = ImageIO.read(ur1);
			int spriteWidth = pSprite.getWidth()/columns;
			int spriteHeight = pSprite.getHeight()/rows;
			
			for(int y=0;y<rows;y++){
				for(int x=0;x<columns;x++){
					addSprite(pSprite, 0+(x*spriteWidth),0+ (y*spriteHeight), spriteWidth, spriteHeight);
				}
			}
			
			
			for(int i=0;i<2;i++){
				addSprite(pSprite, 0+(i*200), 0, 200, 120);
			}
		}catch(IOException e ){};		
//		try{
//			URL ur1 = this.getClass().getResource(imPath);
//			BufferedImage pSprite = ImageIO.read(ur1);
//			int spriteWidth = pSprite.getWidth()/columns;
//			int spriteHeight = pSprite.getHeight()/rows;
//			
//			for(int y=0;y<rows;y++){
//				for(int x=0;x<rows;x++){
//					addSprite(pSprite, 0+(x*spriteWidth),0+ (y*spriteHeight), spriteWidth, spriteHeight);
//				}
//			}
//			
//			
//			for(int i =0; i <2 ; i++){
//				addSprite(pSprite, 0+(i*200), 0, 200, 120);//Monster sprite size
//			}
//		}catch(IOException e ){};
		timer = new Timer();
		limit = sprites.size()-1;
		
	}
//	
	public void draw(Graphics2D g){
		if(isPrintAnimetionDestroyed())
			return;
		
		//g.drawImage(sprites.get(currentSprite),(int)getxPos(),(int)getxPos(),(int)getyPos(),null);

		g.drawImage(sprites.get(currentSprite), (int)getxPos(),(int)getyPos(),width,hieght,null);
		//g.drawImage(sprites.get(currentSprite), (int)getxPos(),(int)getyPos(),150,150,null);
//		
	}
//	
	public void update(double delta){
		if(isPrintAnimetionDestroyed())
			return;
	
		if(loop && !play)
			loopAnimation();
		
		if(play && !loop)
			playAnimation();
		
	}

	public void stopAnumation(){
		loop = false;
		play = false;
	}
	
	public void resetSprite(){
		loop = false;
		play = false;
		currentSprite  =0;
	}
	
	private void loopAnimation(){
		
		 if(timer.isTimerReady(animationSpeed)&& currentSprite == limit ) {
			currentSprite =0;
			timer.resetTimer();
		}
		 else if(timer.timeEvent(animationSpeed)&& currentSprite != limit ){
			currentSprite++;
		}
		 
	}
	
	public void playAnimation(){
		
		if(timer.isTimerReady(animationSpeed)&& currentSprite != limit && !isDestroyAfterAnimtion() ){
		play = false;
		currentSprite =0;
		}
		else if(timer.isTimerReady(animationSpeed)&& currentSprite == limit && isDestroyAfterAnimtion() ){
			sprites = null;
		}
		else if(timer.timeEvent(animationSpeed)&& currentSprite != limit){
			currentSprite++;
			
		}
		
		
//		if(timer.timeEvent(animationSpeed)&& currentSprite !=   && !isDestroyAfterAnimtion()){
//			play = false;
//			currentSprite =0;
//		}
//		else if(timer.isTimerReady(animationSpeed)&& currentSprite == limit && isDestroyAfterAnimtion()) {
//			sprites = null;
//		}
//		else if(timer.timeEvent(animationSpeed)&& currentSprite != limit ){
//			currentSprite++;
//		}
	}
//	
	
	
	public byte getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(byte currentSprite) {
		this.currentSprite = currentSprite;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean isPrintAnimetionDestroyed(){
		if(sprites == null)
			return true;
		return false;
	}
	
	public void addSprite(BufferedImage spriteMap , int xPos,int yPos,int width,int height){
		sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
	}
//	
	public void setPlay(boolean play,boolean destroyAfterAnimtion){
		if(loop){
			loop = false;
		}
		this.play = play;
		//this.destroyAfterAnimtion = destroyAfterAnimtion;
		this.setDestroyAfterAnimtion(destroyAfterAnimtion);
//		
//		
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean isDestroyAfterAnimtion() {
		return destroyAfterAnimtion;
	}

	public void setDestroyAfterAnimtion(boolean destroyAfterAnimtion) {
		this.destroyAfterAnimtion = destroyAfterAnimtion;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHieght() {
		return hieght;
	}

	public void setHieght(int hieght) {
		this.hieght = hieght;
	}
	public int getAnimationSpeed() {
		return animationSpeed;
	}
	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		if(limit>0){
			
			this.limit = limit-1;
		}
		else{
			this.limit = limit;
		}
	}
	public boolean isPlay() {
		return play;
	}

	public void resetLimit(){
		limit = sprites.size()-1;
	}
	
//	
//	public boolean isPlay() {
//		return play;
//	}
//
//	public void setPlay(boolean play) {
//		this.play = play;
//	}
//
//	public int getAnimationSpeed() {
//		return animationSpeed;
//	}
//
//	public void setAnimationSpeed(int animationSpeed) {
//		this.animationSpeed = animationSpeed;
//	}
//
//	public int getLimit() {
//		return limit;
//	}
//
//	public void setLimit(int limit) {
//		if(limit>0 ){
//			this.limit = limit - 1;
//		}
//		else{
//			this.limit = limit;
//		}
//	}
//
//	public ArrayList<BufferedImage> getSprites() {
//		return sprites;
//	}
//	
	
	
}
