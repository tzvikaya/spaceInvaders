package com.zvikaya.display;

import java.awt.BorderLayout;

import javax.swing.JApplet;

public class DisplayApplet extends JApplet {

	private static final long serialversionID = 1L;
	private Display display = new Display();
 	
	
	public void init(){
		setLayout(new BorderLayout());
		add(display);
		
	}
	
	
	public void start(){
		display.start();
	}
	
	public void stop(){
		display.stop();
	}
}
