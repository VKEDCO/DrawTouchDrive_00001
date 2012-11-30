package org.vkedco.mobappdev.draw_touch_drive_00001;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DrawTouchDriveMainAct extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_touch_drive_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_draw_touch_drive_main, menu);
        return true;
    }
}
