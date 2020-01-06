package particlez;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import lawt.engine.Entity;
import lawt.engine.Util;

public class Particle extends Entity{
	
	@SuppressWarnings("unused")
	private double size = 7, spd = 0.1, v = Util.random(1);
	private static List<Particle> particles = new ArrayList<Particle>();
	
	public static int numParticles() {
		return particles.size();
	}
	
	public static double mouseX, mouseY;
	
	public Particle(double x, double y, Color col) {
		super(x, y, col);
		particles.add(this);
		this.setEdgeBehavior(Entity.EdgeBehavior.WRAP);
	}
	
	public void accelerate(double direction, double speed){
		accelerateX(Math.cos(direction)*speed);
		accelerateY(Math.sin(direction)*speed);
	}

	@Override
	public void update() {
		for(int i = 0; i < particles.size(); ++i){
			Particle p = particles.get(i);
			if(p != this){
				double dist = Util.dist(x, y, p.x, p.y);
				double theta = Util.angle(x, y, p.x, p.y);
				
				accelerate(theta, spd/(dist));
			}
		}
		
		if(currentWindow.mouseButtonsDown.contains(1)){
			double theta = Util.angle(x, y, mouseX, mouseY);
			accelerate(theta, spd);
		}
		
		if(currentWindow.mouseButtonsDown.contains(3)){
			double theta = Util.angle(x, y, mouseX, mouseY);
			accelerate(theta + Math.PI, spd);
		}
		
		double velVec = Util.dist(0, 0, getVelX(), getVelY());
		size = velVec + 2;
		col = Color.getHSBColor((float)(velVec/30.0) + 0.5f, 0.5f, 1.0f);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(col);
		g.fillOval((int)(x-size/2.0), (int)(y-size/2.0), (int)(size), (int)(size));
	}

}