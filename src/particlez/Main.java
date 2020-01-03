package particlez;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import lawt.engine.Util;
import lawt.engine.Window;

public class Main {
	
	public static float scale = 1.4f;
	
	public static void main(String[] args) {
		Window w = new Window((int)(1280*scale), (int)(720*scale), "PARTICLEZ");
		Window.DrawCall background = (Graphics g) -> {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor(Color.DARK_GRAY.darker().darker().darker().darker());
			g.fillRect(0, 0, w.getWidth(), w.getHeight());
		};
		w.setDrawCall(background);
		
		for(int i = 0; i < 300; i++){
			int xx = (int)Util.random(w.getWidth());
			int yy = (int)Util.random(w.getHeight());
			new Particle(xx, yy, Color.WHITE);
		}
		
		while(true){
			if(w.mouseInWindow){
				Particle.mouseX = w.getMouseX();
				Particle.mouseY = w.getMouseY();
			}
			
			w.executeFrame();
		}
	}

}
