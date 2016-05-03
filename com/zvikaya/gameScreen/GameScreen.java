package com.zvikaya.gameScreen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.logging.Level;

import com.zvikaya.State.StateMachine;
import com.zvikaya.State.SuperStateMachine;
import com.zvikaya.Timer.TicksTimer;
import com.zvikaya.display.Display;
import com.zvikaya.handler.EnemyBulletHandler;
import com.zvikaya.levels.Level1;

public class GameScreen extends SuperStateMachine{

	
	private Player player;
	private BasicBlocks blocks;
	private Level1 level;
	private EnemyBulletHandler bulletHendler;
	
	public static int SCORE =0; 
	
	
	private Font gameScreen = new Font("Arial",Font.PLAIN,48);
	private TicksTimer gameOverTimer = new TicksTimer(100);
	private TicksTimer compliteTimer = new TicksTimer(100);

	
	
	public GameScreen(StateMachine stateMachine ){
		super(stateMachine);
		blocks = new BasicBlocks();
		bulletHendler = new EnemyBulletHandler();
		player = new Player(Display.WIDTH/2-50, Display.HEIGHT-75, 50, 50,blocks);
//		player = new Player(Display.WIDTH/2-50, Display.HEIGHT-75, 50, 50,blocks);
		level = new Level1(player,bulletHendler);
//		
	}
	public void update(double delta) {
		player.update(delta);
		level.update(delta, blocks);
		
		if(level.isGameOver()){
			gameOverTimer.tick(delta);
			if(gameOverTimer.isEventReady()){
				level.reset();
				blocks.reset();
				getStateMachine().setState((byte)0);
				SCORE =0;
			}
		}
		
		if(level.iscomplite()){
			compliteTimer.tick(delta);
			if(compliteTimer.isEventReady()){
				level.reset();
				
			}
		}
	}
//
//
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString("Score: "+ SCORE + "", 5, 15);
		
		g.setColor(Color.red);
		g.drawString("Health: "+ player.getHealth() + "", 5, 35);
		
		
		player.draw(g);
		blocks.draw(g);
		level.draw(g);
		
		if(level.isGameOver()){
			g.setColor(Color.red);
			g.setFont(gameScreen);
			String gameOver = "GAME OVER!!";
			int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
			g.drawString(gameOver,(Display.WIDTH/2) - (gameOverWidth/2), Display.HEIGHT/2);
		}
		
		if(level.iscomplite()){
			g.setColor(Color.green);
			g.setFont(gameScreen);
			String complite = "<<<<MISSION COMPLETE>>>>";
			int compliteWidth = g.getFontMetrics().stringWidth(complite);
			g.drawString(complite,(Display.WIDTH/2) - (compliteWidth/2), Display.HEIGHT/2);
		}
		
	}


	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
//		
	}

}
