package particlez;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import lawt.engine.Util;
import lawt.engine.Window;

public class Main {
	
	public static float scale = 1.4f;
	public static Window w;
	
	public static void main(String[] args) {
		w = new Window((int)(1280*scale), (int)(720*scale), "PARTICLEZ");
		Window.DrawCall background = (Graphics g) -> {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor(Color.DARK_GRAY.darker().darker().darker().darker());
			g.fillRect(0, 0, w.getWidth(), w.getHeight());
		};
		w.setDrawCall(background);
		
		generateParticles(300);
		
		while(true){
			if(w.mouseInWindow){
				Particle.mouseX = w.getMouseX();
				Particle.mouseY = w.getMouseY();
				
				if(w.mouseButtonsDown.contains(2) && w.step % 3 == 0) {
					double r = 3;
					r = Util.random(-r, r);
					new Particle(Particle.mouseX + r, Particle.mouseY + r, Color.WHITE);
				}
			}
			
			w.executeFrame();
		}
	}
	
	public static void generateParticles(int n){
		for(int i = 0; i < n; i++){
			int xx = (int)Util.random(w.getWidth());
			int yy = (int)Util.random(w.getHeight());
			new Particle(xx, yy, Color.WHITE);
		}
	}
	
}