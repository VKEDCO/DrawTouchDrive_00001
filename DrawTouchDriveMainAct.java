package org.vkedco.mobappdev.draw_touch_drive_00001;

/*************************************************
* Bugs to vladimir dot kulyukin at gmail dot com
**************************************************
*/

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DrawTouchDriveMainAct extends Activity {
	
	TicTacToeView mTTTView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_touch_drive_main);
        mTTTView = (TicTacToeView) this.findViewById(R.id.pntr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_draw_touch_drive_main, menu);
        return true;
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		if ( mTTTView != null ) {
			mTTTView.disableDrawing();
			mTTTView = null;
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if ( mTTTView != null ) {
			mTTTView.disableDrawing();
			mTTTView = null;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if ( mTTTView != null ) {
			mTTTView.disableDrawing();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		if ( mTTTView != null ) {
			mTTTView.enableDrawing();
		}
		else {
			mTTTView = (TicTacToeView) this.findViewById(R.id.pntr);
			mTTTView.enableDrawing();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if ( mTTTView != null ) {
			mTTTView.disableDrawing();
		}
	}
    
}
