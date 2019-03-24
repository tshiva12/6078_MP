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

public class VolumeConverter extends AppCompatActivity
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
        unitsarr.add(getResources().getString(R.string.litres));
        unitsarr.add(getResources().getString(R.string.millilitres));
        unitsarr.add(getResources().getString(R.string.cubicm));
        unitsarr.add(getResources().getString(R.string.cubiccm));
        unitsarr.add(getResources().getString(R.string.cubicmm));
        unitsarr.add(getResources().getString(R.string.cubicfeet));
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
        if((from.equals(this.getApplicationContext().getResources().getString(R.string.litres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)))){
            double result =  input*1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.litres)))){
            double result =  input/1000;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.litres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)))){
            double result =  input*0.001;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.litres)))){
            double result =  input*1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.litres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)))){
            double result =  1000*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.litres)))){
            //else if((from.equals("cubic cm")) && (to.equals("litre"))){
            double result =  0.001*input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.litres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)))){
            double result =  input*1000000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.litres)))){
            double result =  input/1000000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.litres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)))){
            double result =  input*0.03531;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.litres)))){
            //else if((from.equals("cubic feet")) && (to.equals("litre"))){
            double result =  input*28.31685;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)))){
            double result =  input*100*100*100;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)))){
            double result =  input/1000000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)))){
            double result =  input*1000*1000*1000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)))){
            double result =  input/(1000*1000*1000);
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)))){
            double result =  input*1000000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)))){
            double result =  input/1000000;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)))){
            double result =  input*35.31467;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicm)))){
            double result =  input/35.31467;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)))){
            double result =  input*1000;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)))){
            double result =  input/1000;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)))){
            double result =  input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)))){
            double result =  input;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)))){
            double result =  input/28316.84659;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubiccm)))){
            double result =  input*28316.84659;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)))){
            double result =  input*0.001;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)))){
            double result =  input*1000;
            return result;
        }


        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)))){
            double result =  input/28316846.592;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicmm)))){
            double result =  input*28316846.592;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)) && to.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)))){
            double result =  input*28316.84659;
            return result;
        }

        else if((from.equals(this.getApplicationContext().getResources().getString(R.string.millilitres)) && to.equals(this.getApplicationContext().getResources().getString(R.string.cubicfeet)))){
            double result =  input/28316.84659;
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

