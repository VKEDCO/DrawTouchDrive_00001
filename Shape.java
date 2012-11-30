package org.vkedco.mobappdev.draw_touch_drive_00001;

public class Shape {
	
	protected float mStartX   = 0f;
	protected float mStartY   = 0f;
	protected float mCurrentX = 30f; 	// current x coordinate of ChessPiece
	protected float mCurrentY = 30f; 	// current y coordinate of ChessPiece
	protected float mActionDownX;   	// x coordinate of ChessPiece of an action down
	protected float mActionDownY;   	// y coordinate of ChessPiece of an action down
	protected float mActionMoveOffsetX; // x coordinate of a move action
	protected float mActionMoveOffsetY; // y coordinate of a move action
	
	public Shape(float x, float y) {
		mStartX   = x;
		mStartY   = y;
		mCurrentX = x;
		mCurrentY = y;
	}
	
	public float getStartX() { return mCurrentX; }
	
	public float getStartY() { return mCurrentY; }
	
	public void setStartX(float x) { mStartX = x; }
	
	public void setStartY(float y) { mStartY = y; }
	
	public float getCurrentX() { return mCurrentX; }
	
	public float getCurrentY() { return mCurrentY; }
	
	public void setCurrentX(float x) { mCurrentX = x; }
	
	public void setCurrentY(float y) { mCurrentY = y; }
	
	public void setActionMoveOffsetX(float x) { mActionMoveOffsetX = x; }
	
	public void setActionMoveOffsetY(float y) { mActionMoveOffsetY = y; }
	
	public float getActionMoveOffsetX() { return mActionMoveOffsetX; }
	
	public float getActionMoveOffsetY() { return mActionMoveOffsetY; }
	
	public void setActionDownX(float x) { mActionDownX = x; }
	
	public void setActionDownY(float y) { mActionDownY = y; }
	
	public float getActionDownX() { return mActionDownX; }
	
	public float getActionDownY() { return mActionDownY; }
	
	public void restoreStartPosition() {
		mCurrentX = mStartX;
		mCurrentY = mStartY;
	}

}
