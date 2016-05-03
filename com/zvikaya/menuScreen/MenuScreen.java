package com.zvikaya.menuScreen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.zvikaya.State.StateMachine;
import com.zvikaya.State.SuperStateMachine;
import com.zvikaya.display.Display;

public class MenuScreen extends SuperStateMachine implements KeyListener{

	private Font titleFont =  new Font("Ariel", Font.PLAIN, 64);
	private Font statrtFont =   new Font("Ariel", Font.PLAIN, 32);
	private Font nameFont =   new Font("Ariel", Font.PLAIN, 22);
	
	private String tittle = "SPACE INVADRERS";
	private String start = "Press Enter";
	private String name1 = "Zvika yamin id:052821147";


	
	public MenuScreen(StateMachine stateMachine) {
		super(stateMachine);
		
	}

	@Override
	public void update(double delta) {
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(titleFont);
		int tittleWidth = g.getFontMetrics().stringWidth(tittle);
		g.setColor(Color.yellow);
		g.drawString(tittle, ((Display.WIDTH/2)- (tittleWidth/2))-2, (Display.HEIGHT/2)-123);
		g.setColor(Color.green);
		g.drawString(tittle, (Display.WIDTH/2)- (tittleWidth/2), (Display.HEIGHT/2)-125);
		
		g.setFont(statrtFont);
		g.setColor(Color.white);
		int startWidth = g.getFontMetrics().stringWidth(start);
		g.drawString(start, (Display.WIDTH/2)- (tittleWidth/2), (Display.HEIGHT/2)+75);
		
		g.setFont(nameFont);
		g.setColor(Color.white);
		int nameFont = g.getFontMetrics().stringWidth(name1);
		
		g.drawString("made by:", (Display.WIDTH/2)- (tittleWidth/2), (Display.HEIGHT/2)+200);
		g.drawString(name1, (Display.WIDTH/2)- (tittleWidth/2), (Display.HEIGHT/2)+220);
	
		
		

	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(this);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER){
			getStateMachine().setState((byte)1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
