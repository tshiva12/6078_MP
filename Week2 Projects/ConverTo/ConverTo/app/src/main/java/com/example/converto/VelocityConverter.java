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

public class VelocityConverter extends AppCompatActivity
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
    @Override
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
        unitsarr.add(getResources().getString(R.string.kmph));
        unitsarr.add(getResources().getString(R.string.milesperh));
        unitsarr.add(getResources().getString(R.string.meterpers));
        unitsarr.add(getResources().getString(R.string.feetpers));
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

        if((from.equals(this.getApplicationContext().getResources().getString(R.string.milesperh)) && to.equals(this.getApplicationContext().getResources().getString(R.string.kmph)))){
            double result = input*1.60934;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.kmph)) && to.equals(this.getApplicationContext().getResources().getString(R.string.milesperh)))){
            double result = input*0.62137;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.milesperh)) && to.equals(this.getApplicationContext().getResources().getString(R.string.meterpers)))){
            double result = input*1609.34/3600;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.meterpers)) && to.equals(this.getApplicationContext().getResources().getString(R.string.milesperh)))){
            double result = input*3600/1609.34;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.milesperh)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feetpers)))){
            double result = input*5280/3600;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feetpers)) && to.equals(this.getApplicationContext().getResources().getString(R.string.milesperh)))){
            double result = input*3600/5280;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.kmph)) && to.equals(this.getApplicationContext().getResources().getString(R.string.meterpers)))){
            double result = input*1000/3600;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.meterpers)) && to.equals(this.getApplicationContext().getResources().getString(R.string.kmph)))){
            double result = input*3600/1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.kmph)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feetpers)))){
            double result = input*3280.84/3600;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feetpers)) && to.equals(this.getApplicationContext().getResources().getString(R.string.kmph)))){
            double result = input*3600/3280.84;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.meterpers)) && to.equals(this.getApplicationContext().getResources().getString(R.string.feetpers)))){
            double result = input*3.28084;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.feetpers)) && to.equals(this.getApplicationContext().getResources().getString(R.string.meterpers)))){
            double result = input/3.28084;
            return result;
        }

        if(from.equals(to)){
            return input;
        }
        return 0;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


