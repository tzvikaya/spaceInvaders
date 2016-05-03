package com.zvikaya.handler;

import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;

import com.zvikaya.enemyBullets.EnemyWeaponType;
import com.zvikaya.explosion.ExplosionManager;
import com.zvikaya.gameScreen.BasicBlocks;
import com.zvikaya.gameScreen.Player;

public class EnemyBulletHandler {

	private ArrayList<EnemyWeaponType> weaponTypes = new ArrayList<>();
	
	public void addBullet(EnemyWeaponType weaponType){
		this.weaponTypes.add(weaponType);
	}
	
	public void draw(Graphics2D g){
		for (EnemyWeaponType enemyWeaponType : weaponTypes) {
			enemyWeaponType.draw(g);
		}
	}
	public void update(double delta, BasicBlocks blocks,Player player){
		for(int i=0; i<weaponTypes.size(); i++){
			weaponTypes.get(i).update(delta, blocks, player);
			if(weaponTypes.get(i).collision(player.getRect())){
				ExplosionManager.createPixelExplosion(weaponTypes.get(i).getxPos(),weaponTypes.get(i).getyPos());

				weaponTypes.remove(i);
				player.hit();
			}
		}
		
	}

	public void reset() {
	weaponTypes.clear();
		
	}
	
	
	
	
	
	
	
}
