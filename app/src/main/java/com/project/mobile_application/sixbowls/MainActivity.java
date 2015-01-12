package com.project.mobile_application.sixbowls;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends ActionBarActivity{

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //imposta l'orientamento dello schermo
        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //nascondi statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      Intent i=new Intent(MainActivity.this,GameActivity.class);
                                      i.putExtra("gameType","p1vsp2");
                                      startActivity(i);
                                  }
                              }
        );

        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      Intent i=new Intent(MainActivity.this,GameActivity.class);
                                      i.putExtra("gameType","p1vsAI");
                                      startActivity(i);
                                  }
                              }
        );
    }


    public void playGame(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gameType","p1vsp2");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
