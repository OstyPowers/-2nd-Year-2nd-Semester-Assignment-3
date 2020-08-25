package com.example.sokbanv1;

import android.graphics.Point;

public enum Direction {
	UP (0,-1),
	DOWN (0,1),
	LEFT (-1,0),
	RIGHT (1,0);
	
	private final Point p;
	
	private Direction(int x, int y) {
		p = new Point (x,y);
	}
	
	public Point offset() {
		return p;
	}
	
	public Point getPoint() {
		return p;
	}
	
	

}
