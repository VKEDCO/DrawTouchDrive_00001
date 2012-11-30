package org.vkedco.mobappdev.draw_touch_drive_00001;

/*************************************************
 * Circle class defines Circle objects drawn
 * by TicTacToeView defined in TicTacToeView.java.  
 * 
 * The user can drag and drop circles all
 * over canvas (e.g., place them on the board).
 * 
 * Bugs to vladimir dot kulyukin at gmail dot com
 *************************************************
 */

public class Circle extends Shape {
	final float mRadius;
	
	public Circle(float x, float y, float r) {
		super(x, y);
		mRadius = r;
	}
	
	final float getRadius() {
		return mRadius;
	}
}
