package com.zvikaya.explosion;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ExplosionManager {


	private static ArrayList<ExplosionType> explosion = new ArrayList<ExplosionType>();

	
	public void draw(Graphics2D g){

		for(int i=0;i<explosion.size();i++){
			explosion.get(i).draw(g);
		}
	}

	public void update(double delta){

		for(int i=0;i<explosion.size();i++){
			explosion.get(i).update(delta);
			if(explosion.get(i).destroy()){
				explosion.remove(i);
			}
		}

	}

	public static void createPixelExplosion(double xPos,double yPos ){
		
		ExplosionType et = new PixalExplosion(xPos, yPos);
		explosion.add(et);
		
		
	}

}
