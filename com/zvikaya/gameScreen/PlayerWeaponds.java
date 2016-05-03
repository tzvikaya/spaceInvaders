package com.zvikaya.gameScreen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.tree.ExpandVetoException;

import com.zvikaya.Timer.Timer;
import com.zvikaya.explosion.ExplosionManager;
import com.zvikaya.playerBullets.MachineGun;
import com.zvikaya.playerBullets.PlayerWeaponType;
import com.zvikaya.sound.Sound;




public class PlayerWeaponds {

	private Timer timer;
	private ExplosionManager explosionManager;
	public ArrayList<PlayerWeaponType> weaponds = new ArrayList<PlayerWeaponType>();
	private Sound shootSound;
	
	public PlayerWeaponds(){
		explosionManager = new ExplosionManager();
		timer = new Timer();
		shootSound = new Sound("/com/zvikaya/sounds/shootInSpace.wav");
	}
	
	public void draw(Graphics2D g){
		
		explosionManager.draw(g);
		for(int i = 0; i < weaponds.size(); i++){
			weaponds.get(i).draw(g);
		}
	}
	
	public void update(double delta, BasicBlocks blocks){
		
		explosionManager.update(delta);
		for(int i = 0; i < weaponds.size(); i++){
			weaponds.get(i).update(delta, blocks);
			if(weaponds.get(i).destroy()) {
				ExplosionManager.createPixelExplosion(weaponds.get(i).getxPos(), weaponds.get(i).getyPos());
				weaponds.remove(i);
			}
		}
	}
	
	public void shootBullet(double xPos, double yPos, int width, int height){
		if(timer.timeEvent(250) && weaponds.size() < 5) {//rate of fire and number of bullets
			if (shootSound.isPlaying()) {
				shootSound.stop();
			}
			shootSound.play();
			weaponds.add(new MachineGun(xPos + 22, yPos + 15, width, height));
		}
	}

	public void reset() {
		weaponds.clear();
		
	}
}


