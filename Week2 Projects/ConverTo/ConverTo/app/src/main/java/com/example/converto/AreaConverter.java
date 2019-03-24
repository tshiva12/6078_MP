package com.example.converto;

import android.content.Intent;
import android.os.Bundle;
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

public class AreaConverter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        unitsarr.add(getResources().getString(R.string.sqkm));
        unitsarr.add(getResources().getString(R.string.sqmiles));
        unitsarr.add(getResources().getString(R.string.sqm));
        unitsarr.add(getResources().getString(R.string.sqcm));
        unitsarr.add(getResources().getString(R.string.sqmm));
        unitsarr.add(getResources().getString(R.string.sqyard));
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

        if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)) && to.equals((this.getApplicationContext().getResources().getString(R.string.sqkm))))){
            double result = 1.60934*1.60934*input;
            return result;
        }
        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)) && to.equals((this.getApplicationContext().getResources().getString(R.string.sqmiles))))){
            double result = 0.62137*0.62137*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqm)))){
            double result = 1609.34*1609.34*input;
            return result;
        }
        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)))){
            double result = input/(1609.34*1609.34);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)))){
            double result = input*160934*160934;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)))){
            double result = input/(160934*160934);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)))){
            double result = input*1609340*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)))){
            double result = input/(1609340*1609340);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)))){
            double result = input*1760*1760;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmiles)))){
            double result = input/(1760*1760);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqm)))){
            double result = input*1000*1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)))){
            //else if((from.equals("square m")) && (to.equals("square km"))){
            double result = input/(1000*1000);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)))){
            double result = input*100000*100000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)))){
            double result = input/(100000*100000);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)))){
            double result = input*1000000*1000000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)))){
            double result = input/(1000000*1000000);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)))){
            double result = 1093.6133*1093.6133*input;
            return result;
        }
        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqkm)))){
            double result = input/(1093.6133*1093.6133);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)))){
            double result = input*100*100;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqm)))){
            double result = input/(100*100);
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)))){
            double result = input*1000*1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqm)))){
            double result = input/(1000*1000);
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)))){
            double result = 1.09361*1.09361*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqm)))){
            double result = input/(1.09361*1.09361);
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)))){
            //else if((from.equals("square cm")) && (to.equals("square mm"))){
            double result = (input*10*10);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)))){
            double result = input/(10*10);
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)))){
            double result = 0.01094*0.01094*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqcm)))){
            double result = input/(0.01094*0.01094);
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)))){
            double result = 0.001094*0.001094*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.sqyard)) && to.equals(this.getApplicationContext().getResources().getString(R.string.sqmm)))){
            double result = input/(0.001094*0.001094);
            return result;
        }

        if(from.equals(to)){
            return input;
        }
        return 0.0;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
