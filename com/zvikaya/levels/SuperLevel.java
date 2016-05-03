package com.zvikaya.levels;

import java.awt.Graphics2D;

import com.zvikaya.gameScreen.BasicBlocks;

public interface SuperLevel {

	void draw(Graphics2D g);
	void update(double delta,BasicBlocks blocks);
	void hasDiractionChange(double delta);
	void changDurectionAllEnemys(double delta );
	
	
	boolean isGameOver();
	boolean iscomplite();
	
	void destroy();
	void reset();
	
	
	
}
