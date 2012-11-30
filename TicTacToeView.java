package org.vkedco.mobappdev.draw_touch_drive_00001;

/*************************************************
 * TicTacToeView.java customizes View. 
 *
 * The user can drag and drop circles and crosses
 * over canvas (e.g., place them on the board).
 * 
 * Bugs to vladimir dot kulyukin at gmail dot com
 *************************************************
 */

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TicTacToeView extends View {
	final Paint mBackgroundPaint;
	final Paint mForeCirclePaint;
	final Paint mForeCrossPaint;
	final Paint mForeBoardPaint;
	final BoardGeometry mBoardGeometry;
	ArrayList<Circle> mCircles;
	ArrayList<Cross> mCrosses;
	private boolean mDrawingEnabled = true;

	public TicTacToeView(Context context, AttributeSet atrs) {
		super(context, atrs);

		mBoardGeometry = new BoardGeometry(50, 100, 260, 310, 70, 70);
		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(Color.GREEN);

		mForeBoardPaint = new Paint();
		mForeBoardPaint.setColor(Color.BLACK);
		mForeBoardPaint.setAntiAlias(true);

		mForeCirclePaint = new Paint();
		mForeCirclePaint.setColor(Color.RED);
		mForeCirclePaint.setAntiAlias(true);

		mForeCrossPaint = new Paint();
		mForeCrossPaint.setColor(Color.BLUE);
		mForeCrossPaint.setAntiAlias(true);

		createCircles();
		createCrosses();
	}

	private void createCircles() {
		mCircles = new ArrayList<Circle>();
		mCircles.add(new Circle(30, 30, 20));
		mCircles.add(new Circle(120, 30, 20));
	}

	private void createCrosses() {
		mCrosses = new ArrayList<Cross>();
		mCrosses.add(new Cross(30, 350, 20));
		mCrosses.add(new Cross(120, 350, 20));
	}

	// redraws the entire canvas
	public void draw(Canvas canvas) {
		if ( mDrawingEnabled ) {
			final int width = canvas.getWidth();
			final int height = canvas.getHeight();
			// 1. draw background rectangle that covers the entire
			// canvas
			canvas.drawRect(0, 0, width, height, mBackgroundPaint);
			// 2. draw board on the canvas
			drawBoard(canvas);
			// 3. draw red circles on canvas
			drawCirclesOnCanvas(canvas);
			// 4. draw blue crosses on canvas
			drawCrossesOnCanvas(canvas);
			// 5. force redraw
			invalidate();
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		// 1. get the x and y of MotionEvent
		float x = event.getX();
		float y = event.getY();
		// 2. find circle closest to x and y
		Circle cr = findCircleClosestToTouchEvent(x, y);
		// 3. find cross closest to x and y
		Cross cx = findCrossClosestToTouchEvent(x, y);
		// 4. compute euclid distances to find which is
		// closer - circle or cross
		float dtcr = euclidDist(cr.getCurrentX(), cr.getCurrentY(), x, y);
		float dtcx = euclidDist(cx.getMidX(), cx.getMidY(), x, y);
		// 5. if distance to closest circle is smaller
		// handle the circle; otherwise, handle the cross
		if (dtcr < dtcx) {
			handleTouchedCircle(event, cr);
		} else {
			handleTouchedCross(event, cx);
		}
		return true;
	}

	// draw all Circle objects in mCircles
	private void drawCirclesOnCanvas(Canvas canvas) {
		for (Circle c : mCircles) {
			canvas.drawCircle(c.getCurrentX(), c.getCurrentY(), c.getRadius(),
					mForeCirclePaint);
		}
	}
	
	// draw all Cross objects in mCrosses
	private void drawCrossesOnCanvas(Canvas canvas) {
		for (Cross cx : mCrosses) {
			canvas.drawLine(cx.getTLX(), cx.getTLY(), cx.getBRX(), cx.getBRY(),
					mForeCrossPaint);
			canvas.drawLine(cx.getTLX(), cx.getBRY(), cx.getBRX(), cx.getTLY(),
					mForeCrossPaint);
		}
	}

	private static float euclidDist(float x1, float y1, float x2, float y2) {
		return android.util.FloatMath.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1)
				* (y2 - y1));
	}

	private Circle findCircleClosestToTouchEvent(float x, float y) {
		Circle c = mCircles.get(0);
		float dist = euclidDist(c.getCurrentX(), c.getCurrentY(), x, y);
		float tempdist = 0;
		for (Circle cr : mCircles) {
			tempdist = euclidDist(cr.getCurrentX(), cr.getCurrentY(), x, y);
			if (tempdist < dist) {
				c = cr;
				dist = tempdist;
			}
		}
		return c;
	}

	private Cross findCrossClosestToTouchEvent(float x, float y) {
		Cross c = mCrosses.get(0);
		float dist = euclidDist(c.getMidX(), c.getMidY(), x, y);
		float tempdist = 0;
		for (Cross cx : mCrosses) {
			tempdist = euclidDist(cx.getMidX(), cx.getMidY(), x, y);
			if (tempdist < dist) {
				c = cx;
				dist = tempdist;
			}
		}
		return c;
	}

	private void handleTouchedCircle(MotionEvent me, Circle c) {
		final float me_x = me.getX();
		final float me_y = me.getY();
		final int action = me.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			c.setActionDownX(c.getCurrentX());
			c.setActionDownY(c.getCurrentY());
			c.setActionMoveOffsetX(me_x);
			c.setActionMoveOffsetY(me_y);
			break;
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
			c.setCurrentX(c.getActionDownX() + me_x - c.getActionMoveOffsetX());
			c.setCurrentY(c.getActionDownY() + me_y - c.getActionMoveOffsetY());
			break;
		case MotionEvent.ACTION_CANCEL:
			c.restoreStartPosition();
			break;
		}
	}

	private void handleTouchedCross(MotionEvent me, Cross c) {
		final float me_x = me.getX();
		final float me_y = me.getY();
		final int action = me.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			c.setActionDownX(c.getCurrentX());
			c.setActionDownY(c.getCurrentY());
			c.setActionMoveOffsetX(me_x);
			c.setActionMoveOffsetY(me_y);
			break;
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
			c.setCurrentX(c.getActionDownX() + me_x - c.getActionMoveOffsetX());
			c.setCurrentY(c.getActionDownY() + me_y - c.getActionMoveOffsetY());
			break;
		case MotionEvent.ACTION_CANCEL:
			c.restoreStartPosition();
			break;
		}
	}

	private void drawBoard(Canvas canvas) {
		// draw borders
		canvas
			.drawLine(mBoardGeometry.getTLX(), mBoardGeometry.getTLY(),
					  mBoardGeometry.getBRX(), mBoardGeometry.getTLY(), 
					  mForeBoardPaint);
		canvas
			.drawLine(mBoardGeometry.getBRX(), mBoardGeometry.getTLY(),
					  mBoardGeometry.getBRX(), mBoardGeometry.getBRY(), 
					  mForeBoardPaint);
		canvas
			.drawLine(mBoardGeometry.getBRX(), mBoardGeometry.getBRY(), 
					  mBoardGeometry.getTLX(), 
					  mBoardGeometry.getTLY() + 3*mBoardGeometry.getHeight(), 
					  mForeBoardPaint);
		canvas
			.drawLine(mBoardGeometry.getTLX(), 
					  mBoardGeometry.getTLY() + 3*mBoardGeometry.getHeight(), 
					  mBoardGeometry.getTLX(),
				      mBoardGeometry.getTLY(), 
				      mForeBoardPaint);

		// draw rows
		canvas
			.drawLine(mBoardGeometry.getTLX(), 
					  mBoardGeometry.getTLY() + mBoardGeometry.getHeight(), 
					  mBoardGeometry.getBRX(),
				      mBoardGeometry.getTLY() + mBoardGeometry.getHeight(),
				      mForeBoardPaint);
		canvas
			.drawLine(mBoardGeometry.getTLX(), 
					  mBoardGeometry.getTLY() + 2*mBoardGeometry.getHeight(), 
					  mBoardGeometry.getBRX(),
				      mBoardGeometry.getTLY() + 2*mBoardGeometry.getHeight(),
				      mForeBoardPaint);

		// draw columns
		canvas
			.drawLine(mBoardGeometry.getTLX() + mBoardGeometry.getWidth(),
					  mBoardGeometry.getTLY(), 
					  mBoardGeometry.getTLX() + mBoardGeometry.getWidth(), 
					  mBoardGeometry.getBRY(),
					  mForeBoardPaint);
		canvas
			.drawLine(mBoardGeometry.getTLX() + 2*mBoardGeometry.getWidth(),
					  mBoardGeometry.getTLY(), 
					  mBoardGeometry.getTLX() + 2*mBoardGeometry.getWidth(), 
					  mBoardGeometry.getBRY(),
					  mForeBoardPaint);
	}
}
