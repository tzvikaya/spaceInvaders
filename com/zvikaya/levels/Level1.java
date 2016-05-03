package com.zvikaya.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.zvikaya.gameScreen.BasicBlocks;
import com.zvikaya.gameScreen.Player;
import com.zvikaya.gameScreen.enemyTypes.EnemyType;
import com.zvikaya.gameScreen.enemyTypes.EnemyTypeBasic;
import com.zvikaya.handler.EnemyBulletHandler;
import com.zvikaya.sound.Sound;

public class Level1 implements SuperLevel {

	private Player player;
	private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
	private EnemyBulletHandler bulletHandler;
	
	
	private Sound beep , boop;
	private boolean beepboop;
	
	
	
	public Level1(Player player, EnemyBulletHandler bulletHendler){
		this.player = player;
		this.bulletHandler = bulletHendler;
		addEnemies();
		
		
		beep = new Sound("/com/zvikaya/sounds/beep.wav");
		boop = new Sound("/com/zvikaya/sounds/boop.wav");

	}
//	
//	@Override
	public void draw(Graphics2D g) {
		if(enemies == null)
			return;
		
		
			for(int i = 0; i<enemies.size();i++){
				enemies.get(i).draw(g);
			
			}
			bulletHandler.draw(g);
			
//		
	}
//
//	@Override
	public void update(double delta, BasicBlocks blocks) {
		
		if(enemies == null)
			return;
		
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).update(delta, player, blocks);
		
		}
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).collide(i, player, blocks, enemies);
		
		}
		hasDiractionChange(delta);
		bulletHandler.update(delta, blocks, player);
		
	}

	@Override
	public void hasDiractionChange(double delta) {
		
		if(enemies == null)
			return;
		for(int i = 0; i<enemies.size();i++){
			if(enemies.get(i).isOutOfBounds()){
				changDurectionAllEnemys(delta);
			}
		}
		
	}
//
//	@Override
	public void changDurectionAllEnemys(double delta) {
		for(int i = 0; i<enemies.size();i++){
			enemies.get(i).changeDirection(delta);
		}
		if(beepboop){
			beepboop =false;
			boop.play();
		}
		else {
			beepboop = true;
			beep.play();
		}
	}

//	@Override
	public boolean isGameOver() {

		return player.getHealth()<=0;
	}

//	@Override
	public void destroy() {

		
	}

//	@Override
	public void reset() {
		player.reset();
		enemies.clear();
		addEnemies();
		
		bulletHandler.reset();
	}
	public void addEnemies(){
		for(int y = 0 ; y<5 ; y++){
			for(int x=0;x<10;x++){
				EnemyType e = new EnemyTypeBasic(150+(x*40), 25+(y*40), 1, 3, bulletHandler);// enemy size & gaps
				enemies.add(e);
			}
		}
	}
	@Override
	public boolean iscomplite() {
		
		return enemies.isEmpty();
	}
	
}
