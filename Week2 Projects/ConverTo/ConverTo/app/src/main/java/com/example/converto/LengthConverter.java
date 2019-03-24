package com.example.converto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LengthConverter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;
    private EditText input;
    private Spinner from;
    private Spinner to;
    private Button convert;
    private EditText result;
    ArrayAdapter<String> unitsarr;
    private String unitfrom;
    private String unitto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_converter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        from = (Spinner)findViewById(R.id.from);
        from.setOnItemSelectedListener(this);
        to = (Spinner)findViewById(R.id.to);
        to.setOnItemSelectedListener(this);
        unitsarr = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        unitsarr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(unitsarr);
        to.setAdapter(unitsarr);
        unitsarr.setNotifyOnChange(true);
        result = (EditText)findViewById(R.id.result);
        result.setKeyListener(null);
        result.setClickable(false);
        convert = (Button)findViewById(R.id.convert);
        convert.setOnClickListener(this);
        input = (EditText)findViewById(R.id.input);
        fillSpinnerWith();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.length_converter, menu);
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
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_length) {
            Intent intent = new Intent(this, LengthConverter.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_weight) {
            Intent intent1 = new Intent(this, WeightConverter.class);
            startActivity(intent1);
        } else if (id == R.id.nav_temperature) {
            Intent intent2 = new Intent(this, TemperatureConverter.class);
            startActivity(intent2);
//        }
        } else if (id == R.id.nav_area) {
            Intent intent2 = new Intent(this, AreaConverter.class);
            startActivity(intent2);
        } else if (id == R.id.nav_velocity) {
            Intent intent2 = new Intent(this, VelocityConverter.class);
            startActivity(intent2);
        } else if (id == R.id.nav_volume) {
            Intent intent2 = new Intent(this, VolumeConverter.class);
            startActivity(intent2);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void fillSpinnerWith(){
        unitsarr.clear();
        unitsarr.add(getResources().getString(R.string.mile));
        unitsarr.add(getResources().getString(R.string.km));
        unitsarr.add(getResources().getString(R.string.m));
        unitsarr.add(getResources().getString(R.string.cm));
        unitsarr.add(getResources().getString(R.string.mm));
        unitsarr.add(getResources().getString(R.string.inch));
        unitsarr.add(getResources().getString(R.string.feet));
    }
    public void onClick(View v){
        unitfrom = from.getSelectedItem().toString();
        unitto = to.getSelectedItem().toString();
        if(v == convert){
            if(!input.getText().toString().equals("")){
                double in = Double.parseDouble(input.getText().toString());
                double result1 = Convert(unitfrom, unitto, in);
                result.setText(Double.toString(result1));
            }
            else {
                result.setText("");
            }
        }
    }
    public double Convert(String from, String to, double input) {
        if((from.equals(this.getApplicationContext().getResources().getString(R.string.km)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mile)))){
            double result = 0.62137*input;
            return result;
        }
        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mile)) && to.equals(this.getApplicationContext().getResources().getString(R.string.km)))){
            double result = 1.60934*input;
            return result;
        }
        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mile)) && to.equals(this.getApplicationContext().getResources().getString(R.string.m)))){
            double result = 1609.34*input;
            return result;
        }
        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.m)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mile)))){
            double result = input/1609.34;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mile)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cm)))){
            double result = 160934*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mile)))){
            double result = input/160934;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mile)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mm)))){
            double result = input*1609340;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mile)))){
            double result = input/1609340;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mile)) && to.equals(this.getApplicationContext().getResources().getString(R.string.inch)))){
            double result = 63360*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.inch)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mile)))){
            double result = input/63360;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mile)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feet)))){
            double result = 5280*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mile)))){
            double result = input/5280;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.km)) && to.equals(this.getApplicationContext().getResources().getString(R.string.m)))){
            double result = input*1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.m)) && to.equals(this.getApplicationContext().getResources().getString(R.string.km)))){
            double result = 0.001*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.km)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cm)))){
            double result = 100000*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.km)))){
            double result = input/100000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.km)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mm)))){
            double result = 1000000*input;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.km)))){
            double result = input/1000000;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.km)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feet)))){
            double result = input*3280.84;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.km)))){
            double result = input/3280.84;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.km)) && to.equals(this.getApplicationContext().getResources().getString(R.string.inch)))){
            double result = input*39370.1;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.inch)) && to.equals(this.getApplicationContext().getResources().getString(R.string.km)))){
            double result = input/39370.1;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.m)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cm)))){
            double result = 100*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.m)))){
            double result = input/100;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.m)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mm)))){
            double result = 1000*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.m)))){
            double result = input/1000;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.m)) && to.equals(this.getApplicationContext().getResources().getString(R.string.inch)))){
            double result = 100*input/2.54;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.inch)) && to.equals(this.getApplicationContext().getResources().getString(R.string.m)))){
            double result = 2.54*input/100;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.m)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feet)))){
            double result = input*3.28084;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.m)))){
            double result = input/3.28084;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mm)))){
            double result = 10*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cm)))){
            double result = input/10;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.inch)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cm)))){
            double result = 2.54*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.inch)))){
            double result = input/2.54;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feet)))){
            double result = input*0.03281;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cm)))){
            double result = input*30.48;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feet)))){
            double result = 0.00328*input;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mm)))){
            double result = input*304.8;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.mm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.inch)))){
            double result = input/25.4;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.inch)) && to.equals(this.getApplicationContext().getResources().getString(R.string.mm)))){
            double result = input*25.4;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.inch)))){
            double result = 12*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.inch)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feet)))){
            double result = input/12;
            return result;
        }
        if(from.equals(to)){
            return input;
        }
        return 0.0;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

