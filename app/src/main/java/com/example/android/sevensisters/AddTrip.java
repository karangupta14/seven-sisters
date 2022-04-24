package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.sevensisters.data.MyDbHandler;
import com.example.android.sevensisters.data.Trip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class AddTrip extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Button add_trip_button = findViewById(R.id.add_trip_button);

        Spinner spinnerStates=findViewById(R.id.state_spinner);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.state_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerStates.setAdapter(adapter);
        //String state_selected = spinnerStates.getSelectedItem().toString();
        TextView locations = findViewById(R.id.state_locations);
        //String []locationsArray = {"Tawang", "Ziro Valley", "Roing", "Sela Pass", "Namdapha National Park", "Bomdila", "Dirang", "Tezu",
        //      "Pasighat", "Nuranang Falls", "Mechuka", "Itanagar", "Along", "Bhalukpong", "Sangti", "Changlang", "Bumla Pass", "Anini", "Daporijo",
        //    "Hayuliang Village", "Pakhui Wildlife Sanctuary", "Gorichen Peak"};


        // initialize selected language array
        //boolean selectedLanguage[] = new boolean[langArray.length];
        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> locationList = new ArrayList<>();
                String state_selected = spinnerStates.getSelectedItem().toString();
                String []locationsArray={"Tawang", "Ziro Valley", "Roing", "Sela Pass", "Namdapha National Park", "Bomdila", "Dirang", "Tezu",
                        "Pasighat", "Nuranang Falls", "Mechuka", "Itanagar", "Along", "Bhalukpong", "Sangti", "Changlang", "Bumla Pass", "Anini", "Daporijo",
                        "Hayuliang Village", "Pakhui Wildlife Sanctuary", "Gorichen Peak"};;
                if(state_selected.equals("Arunachal Pradesh")) {
                    locationsArray = new String[]{"Tawang", "Ziro Valley", "Roing", "Sela Pass", "Namdapha National Park", "Bomdila", "Dirang", "Tezu",
                            "Pasighat", "Nuranang Falls", "Mechuka", "Itanagar", "Along", "Bhalukpong", "Sangti", "Changlang", "Bumla Pass", "Anini", "Daporijo",
                            "Hayuliang Village", "Pakhui Wildlife Sanctuary", "Gorichen Peak"};
                }
                else if(state_selected.equals("Assam")){
                    locationsArray = new String[]{"Kaziranga National Park","Manas National Park","Kamakhya Temple","Majuli Island", "Hoollongapar Gibbon Wildlife Sanctuary",
                            "Kakochang Waterfalls","Tocklai Tea Research Centre", "Assam State Zoo And Botanical Garden",
                            "Pobitora Wildlife Sanctuary","Orang National Park","Umananda Island","Dipor Bil","Guwahati Planetarium","Hajo"
                            ,"Nameri National Park","Padam Pukhuri","Haflong Lake","Haflong Hill","Panimoor Falls","Dima Hasao","Dibru Saikhowa National Park",
                            "Sivasagar","Silchar","Dibrugarh","Tinsukia","Sualkuchi","Lilabari","Karimganj","Bongaigaon","Hailakandi","Darang","Digboi"};
                }
                else if(state_selected.equals("Manipur")){
                    locationsArray = new String[]{"Loktak Lake","Keibul Lamjao National Park","Tharon Cave","Kangla Fort","Singda Dam",
                            "INA Memorial Complex","Shree Govindajee Temple","Manipur Zoological Garden","Manipur State Museum",
                            "Ima Keithel Woman’s Market","Rasmancha","Jorebangla Temple","Dal Madol","Andro","Leimaram Waterfall",
                            "Dzukou Valley","Shirui Kashung Peak","Waithou Lake"};
                }
                else if(state_selected.equals("Meghalaya")){
                    locationsArray = new String[]{"Cherrapunji","Balpakram National Park","Shillong","Elephant Falls",
                            "Double-decker Living Root Bridge","Don Bosco Museum","Kyllang Rock","Mawlynnong Village","Baghmara",
                            "Nohkalikai Falls","Mawsmai Cave","Umiam Lake","Tura","Jowai","Nongpoh","Dawki","Williamnagar",
                            "Laitlum Canyon","Mawsynram","Mawphlang Sacred Forest"};
                }
                else if(state_selected.equals("Mizoram")) {
                    locationsArray = new String[]{"Aizawl", "Lunglei", "Mamit", "Kolasib", "Reiek", "Vantawng Waterfalls", "Falkawan Village", "Phawngpui", "Tamdil Lake", "Hmuifang", "Serchhip", "Champhai", "Saiha", "Murleng National Park", "Dampa Tiger Reserve", "Phawngpui Peak"};
                }
                else if(state_selected.equals("Nagaland")) {
                    locationsArray =new String[]{"Kohima", "Dimapur", "Mokokchung", "Mon", "Wokha", "Khonoma Green Village", "Benreu", "Meluri", "Tuensang", "Longleng", "Kiphire", "Zunheboto", "Phek", "Dzukou Valley", "Touphema Village", "Naga Heritage Village", "Pfutsero – Mystic Escapes Amid Mountain", "Japfu Peak – Valley Of Flowers", "Kachari Ruins – Archaeological Remains", "Chumukedima Village – Refreshing Escapades"};
                }
                else if(state_selected.equals("Tripura")){
                    locationsArray = new String[]{"Agartala – Capital City","Amarpura – Ideal Picnic Spot","Melaghar – Vibrant Town","Kailashahar – Ancient Capital","Dharamnagar – Naturally Beautiful Landscapes","Udaipur – AKA Rangamati","Ambassa – Old Temples And Traditions","Jampui Hills – Scenic Views","Unakoti – Timeless Ruins","Sepahijala Wildlife Sanctuary – Local Wildlife","Neermahal Palace – Scintillating Water Palace","Tripura Government Museum – Culture Of Tripura","Ujjayanta Palace – Views Of Mughal Garden"};
                }
                boolean selectedLocation[] = new boolean[locationsArray.length];
                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AddTrip.this);

                // set title
                builder.setTitle("Select Language");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(locationsArray, selectedLocation, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            locationList.add(i);
                            // Sort array list
                            Collections.sort(locationList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            locationList.remove(Integer.valueOf(i));
                        }
                    }
                });

                String[] finalLocationsArray = locationsArray;
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < locationList.size(); j++) {
                            // concat array value
                            stringBuilder.append(finalLocationsArray[locationList.get(j)]);
                            // check condition
                            if (j != locationList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        locations.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLocation.length; j++) {
                            // remove all selection
                            selectedLocation[j] = false;
                        }
                        // clear location list
                        locationList.clear();
                        // clear text view value
                        locations.setText("");
                    }
                });
                // show dialog
                builder.show();
            }
        });

        TextView start_date = findViewById(R.id.start_date);
        TextView end_date = findViewById(R.id.end_date);
        Button set_start_date = findViewById(R.id.set_start_date);
        Button set_end_date = findViewById(R.id.set_end_date);
        final int[] start_day = {1};
        final int[] start_month = {1};
        final int[] start_year={1};
        set_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(AddTrip.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                start_day[0] =dayOfMonth;
                                start_month[0] =monthOfYear;
                                start_year[0] =year;
                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                start_date.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });
        set_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(AddTrip.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                end_date.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        ImageButton add_trip = findViewById(R.id.add_trip_icon);
        //destination
        add_trip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locations_list = locations.getText().toString();
                Log.i(TAG, "onClick: locations " +locations_list+"--------------");
                String state_selected = spinnerStates.getSelectedItem().toString();
                Log.i(TAG, "onClick: state " +state_selected+"------------------");
                String st_date = start_date.getText().toString();
                Log.i(TAG, "onClick: start " +st_date+"-------------------------");
                String en_date = end_date.getText().toString();
                Log.i(TAG, "onClick: end " +en_date+"----------------------------");
                MyDbHandler dbHandler = new MyDbHandler(AddTrip.this);
                Trip trip = new Trip(state_selected,locations_list,st_date,en_date);
                dbHandler.addTrip(trip);
                finish();
                Log.i(TAG, "onClick: Trip added to database----------");
            }
        });
    }
}