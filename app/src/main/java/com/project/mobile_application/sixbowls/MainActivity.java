package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends Activity{//ActionBarActivity{

    Button buttonP1VsP2;
    Button buttonP1VsAi;
    Button buttonStats;
    Button buttonRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //set screen orintation into landscape mode
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //hide statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        buttonP1VsP2=(Button)findViewById(R.id.button_p1_vs_p2);
        buttonP1VsP2.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      Intent i=new Intent(MainActivity.this,GameActivity.class);
                                      i.putExtra("gameType","p1vsp2");
                                      startActivity(i);
                                  }
                              }
        );

        buttonP1VsAi=(Button)findViewById(R.id.button_p1_vs_ai);
        buttonP1VsAi.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      Intent i=new Intent(MainActivity.this,GameActivityHumanVSAndroid.class);
                                      i.putExtra("gameType","p1vsAndroid");
                                      startActivity(i);
                                  }
                              }
        );

        buttonStats = (Button)findViewById(R.id.button_stats);
        buttonStats.setOnClickListener(new View.OnClickListener()
                                           {
                                               public void onClick(View v)
                                               {
                                                   Intent i = new Intent(MainActivity.this,RecordsActivity.class);
                                                   startActivity(i);
                                               }
                                           }
        );

        buttonRules = (Button)findViewById(R.id.button_rules);
        buttonRules.setOnClickListener(new View.OnClickListener()
                                       {
                                           public void onClick(View v)
                                           {
                                               Intent i = new Intent(MainActivity.this,InstructionsActivity.class);
                                               startActivity(i);
                                           }
                                       }
        );

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
