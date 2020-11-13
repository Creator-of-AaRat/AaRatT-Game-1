package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	
	private float _acc = 1f;
	private float _dcc = 0.5f;
	private KeyInput input;
	
	
	
	public Player (float x, float y , ID id , KeyInput input) {
		
		super(x , y , id);
		this.input = input;
		
		
	}
	
	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		//horizontal movement
		// keys 0 = true right
		// keys 1 = true left 
    	if(input != null)
    	{
			if (input.keys[0]) velX += _acc;
			else if (input.keys[1]) velX -= _acc;
			else if(!input.keys[0] && !input.keys[1]) {
				
				if(input.keys[0]) velX -= _dcc;
				else if (velX < 0) velX += _dcc;
				
			}
    	
		
			//vertical movement
			//keys 2 = true up
			if(input.keys[2]) velY += _acc;
			else if (input.keys[3]) velY -= _acc;
			else if(!input.keys[2] && !input.keys[1]) {
				
				if(velY > 0) velY -= _dcc;
				else if(velY < 0) velY += _dcc;
				
			}
    	}
		
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}
	
}
