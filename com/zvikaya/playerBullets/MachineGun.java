package com.zvikaya.playerBullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import com.zvikaya.display.Display;
import com.zvikaya.gameScreen.BasicBlocks;

public class MachineGun extends PlayerWeaponType {

	private Rectangle bullet;
	private final double speed = 2.5d;
//	
//	
	public MachineGun(double xPos, double yPos, int width , int height){
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setHeight(height);
		this.setWidth(width);
		
		this.bullet=  new Rectangle((int)getxPos(),(int)getyPos(),getWidth(),getHeight());
		
		
	}
//	
//	
//	
//	@Override
	public void draw(Graphics2D g) {
		if(bullet == null)
			return;
		g.setColor(Color.GREEN);
		g.fill(bullet);
	}
//
//	@Override
	public void update(double delta, BasicBlocks blocks) {
		if(bullet == null)
			return;
		this.setyPos(getyPos() -(delta*speed));
		bullet.y = (int)this.getyPos();
		wallCollide(blocks);
		isOutofBounds();
	}
//
//	@Override
	public boolean collisionRect(Rectangle rect) {
		if(this.bullet == null){
			return false;
		}
		if(bullet.intersects(rect)){
			this.bullet =null;
			return true;
		}
		
		return false;
	}
//
//	@Override
	public boolean collisionPoly(Polygon poly) {
//
//		if(this.bullet == null)
//			return false;
//		if(bullet.intersects(bullet)){
//			this.bullet = null;
//			return true;
//		}
		return false;
	}


	public boolean destroy() {
		if(bullet == null)
			return true;
		
		return false;
	}
//
//	@Override
	protected void wallCollide(BasicBlocks blocks) {
		for(int i=0; i<blocks.wall.size();i++){
			if(bullet.intersects(blocks.wall.get(i))){
				blocks.wall.remove(i);
				bullet = null;
				return;
			}
		}
	}
//
//	@Override
	protected void isOutofBounds() {
		if(this.bullet == null)
			return ;
		
		if(bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x < 0 ||bullet.x > Display.WIDTH){
			bullet = null;
		}
		
	}

}
