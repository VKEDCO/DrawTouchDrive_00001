package org.vkedco.mobappdev.draw_touch_drive_00001;

/*************************************************
 * Cross class defines Cross objects drawn
 * by TicTacToeView defined in TicTacToeView.java.  
 * 
 * The user can drag and drop crosses (touch drive) 
 * over canvas (e.g., place them on the board).
 * 
 * Bugs to vladimir dot kulyukin at gmail dot com
 *************************************************
 */

public class Cross extends Shape {
	final float mSIDE;
	
	public Cross(float topLeftX, float topLeftY, float side) {
		super(topLeftX, topLeftY);
		mSIDE = side;
	}
	
	final float getBRX()  { return mCurrentX + mSIDE; }
	final float getBRY()  { return mCurrentY + mSIDE; }
	final float getMidX() { return mCurrentX + mSIDE/2.0f; }
	final float getMidY() { return mCurrentY + mSIDE/2.0f; }
	final float getTLX()  { return mCurrentX; }
	final float getTLY()  { return mCurrentY; }
}

