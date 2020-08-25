package com.example.sokbanv1;

import android.graphics.Point;

public abstract class Placeable {
	protected String symbol;
	protected int x;
	protected int y;
	
	public String toString() {
		return this.symbol;
		
	}
	
	public Point getPosition() {
		Point p = new Point(this.x, this.y);
		return p;
	}

}
