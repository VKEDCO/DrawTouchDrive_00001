package org.vkedco.mobappdev.draw_touch_drive_00001;

/*************************************************
 * Bugs, comments to vladimir dot kulyukin at gmail dot com
 *************************************************
 */

public class BoardGeometry {
	
	final int mTLX;   	// top left x
	final int mTLY;		// top left y
	final int mBRX;		// bottom right x
	final int mBRY;		// bottom right y
	final int mWidth;	// board width
	final int mHeight;	// board height

	public BoardGeometry(int tlx, int tly, int brx, int bry, int rowh, int colw) {
		mTLX = tlx;
		mTLY = tly;
		mBRX = brx;
		mBRY = bry;
		mHeight = rowh;
		mWidth = colw;
	}
	
	final int getTLX() { return mTLX; }
	final int getTLY() { return mTLY; }
	final int getBRX() { return mBRX; }
	final int getBRY() { return mBRY; }
	final int getWidth()  { return mWidth; }
	final int getHeight() { return mHeight; }
}
