package main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1630939998703733371L;
	
	public static final int WIDTH = 640 , HEIGHT = WIDTH / 12 * 9;

	public synchronized void start() {
		
		new Window(WIDTH , HEIGHT , "AaRat" , this);
		start();
		
	}
	
	public void run() {
		
		
		
	}
	
	public static void main(String args[]) {
		
		new Game();
		
	}
	
}
