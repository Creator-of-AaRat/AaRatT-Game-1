package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    
    public static int WIDTH = 800 , HEIGHT = 608;
    public String title = "AaRat";
    
    private Thread thread;
    private boolean isRunning = false;
    
    //Instances
    private Handler handler; 
    private KeyInput input;
    
    public Game() {
        
        //Construct
        new Window(WIDTH , HEIGHT , title , this);
        start();
        
        init();
        // Below here
        handler.addObject(new Player(100 , 100 , ID.Player, null));
        
        
    }
    
    private void init() {
    	
    	handler = new Handler();
    	input = new KeyInput();
    	this.addKeyListener(input);
    	
    	handler.addObject(new Player(100 , 100 , ID.Player , input));
    }
    
    private synchronized void start() {
        
        if (isRunning) return;
        
        thread = new Thread(this);
        thread.start();
        isRunning = true;
        
    }
    
    private synchronized void stop() {
        
        if (!isRunning) return;
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }
    
    //gameloop
    public void run() {
        
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountofTicks = 60.0;
        double ns = 1000000000 / amountofTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1) {
                
                tick();
                delta--;
                
            }
            
            render();
            frames++;
            
            
        }
        
        stop();
        
    }
    
    private void tick() {
        
        //Updates the game
    	handler.tick();
        
    }
    
    private void render() {
        
        //Renders the game
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            
            this.createBufferStrategy(3);
            return;
            
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //Meat and Bones of our rendering
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        bs.show();
        g.dispose();
        
    }
    
    public static void main(String args []) {
        
        new Game();
        
    }
    
}