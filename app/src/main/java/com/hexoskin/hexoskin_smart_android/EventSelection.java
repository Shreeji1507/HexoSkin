package com.hexoskin.hexoskin_smart_android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.hexoskin.hexoskin_smart_android.CourseSelection;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java. util. Date;
import java. sql. Timestamp;


public class EventSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView selected_textview = (TextView) findViewById(R.id.txtSelectedCourseDisplay);
        String selected_course = getIntent().getStringExtra("selected_course").toString();
        selected_textview.setText(selected_course.toString());

        //final Spinner events_spinner = (Spinner) findViewById(R.id.spnEvents);


        final String[] courses = getResources().getStringArray(R.array.courses_array);

//        Button confirm_button = (Button) findViewById(R.id.btnConfirm);
//
//        confirm_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                TextView error_textview = (TextView) findViewById(R.id.txtError);
//                error_textview.setText("");
////                if (events_spinner.getSelectedItem().toString().equals(courses[0].toString()) )
////                {
////
////                    error_textview.setText("You must select one event!!");
////                    //error_textview.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
////
////                }
//
//            }
//        });

        final ToggleButton event1_toggle = (ToggleButton) findViewById(R.id.tbtnEvent1);
        final ToggleButton event2_toggle =  (ToggleButton) findViewById(R.id.tbtnEvent2);
        final ToggleButton event3_toggle =  (ToggleButton) findViewById(R.id.tbtnEvent3);

        final ToggleButton start_course_toggle = (ToggleButton) findViewById(R.id.tbtnStartCourse);

        event1_toggle.setEnabled(false);
        event2_toggle.setEnabled(false);
        event3_toggle.setEnabled(false);

        if (selected_course.toString().equals(courses[1].toString()))
        {
            ArrayAdapter events_adapter =
                    ArrayAdapter.createFromResource(this, R.array.events_course_1,
                            android.R.layout.simple_spinner_item);
            setText(event1_toggle, event2_toggle, event3_toggle, events_adapter);
        }
        else if (selected_course.toString().equals(courses[2].toString()))
        {
            ArrayAdapter events_adapter =
                    ArrayAdapter.createFromResource(this, R.array.events_course_2,
                            android.R.layout.simple_spinner_item);
            setText(event1_toggle, event2_toggle, event3_toggle, events_adapter);

        }
        else if (selected_course.toString().equals(courses[3].toString()))
        {
            ArrayAdapter events_adapter =
                    ArrayAdapter.createFromResource(this, R.array.events_course_3,
                            android.R.layout.simple_spinner_item);
            setText(event1_toggle, event2_toggle, event3_toggle, events_adapter);
        }




        start_course_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    enableButtons(event1_toggle, event2_toggle, event3_toggle);
//                    event1_toggle.setEnabled(true);
//                    event2_toggle.setEnabled(true);
//                    event3_toggle.setEnabled(true);
                    updateTime();
                }
                else{
                    disableButtons(event1_toggle, event2_toggle, event3_toggle);
//                    event1_toggle.setEnabled(false);
//                    event2_toggle.setEnabled(false);
//                    event3_toggle.setEnabled(false);
                    updateTime();
                }
            }
        });

        /**
         * on checked event for first event toggle button
         * when the event 3 is selected, disable other two toggle buttons, otherwise enable
         * all toggles
         */
        event1_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    disableButtons(event2_toggle, event3_toggle, start_course_toggle);
//                    event2_toggle.setEnabled(false);
//                    event3_toggle.setEnabled(false);
//                    start_course_toggle.setEnabled(false);
                    updateTime();
                } else {

                    enableButtons(event2_toggle, event3_toggle, start_course_toggle);
//                    event2_toggle.setEnabled(true);
//                    event3_toggle.setEnabled(true);
//                    start_course_toggle.setEnabled(true);
                    updateTime();

                }
            }
        });


        /**
         * on checked event for second event toggle button
         * when the event 3 is selected, disable other two toggle buttons, otherwise enable
         * all toggles
         */
        event2_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    disableButtons(event1_toggle, event3_toggle, start_course_toggle);
//                    event1_toggle.setEnabled(false);
//                    event3_toggle.setEnabled(false);
//                    start_course_toggle.setEnabled(false);
                    updateTime();
                } else {
                    enableButtons(event1_toggle, event3_toggle, start_course_toggle);
//                    event1_toggle.setEnabled(true);
//                    event3_toggle.setEnabled(true);
//                    start_course_toggle.setEnabled(true);
                    updateTime();
                }

            }
        });

        /**
         * on checked event for third event toggle button
         * when the event 3 is selected, disable other two toggle buttons, otherwise enable
         * all toggles
         */
        event3_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    disableButtons(event1_toggle, event2_toggle, start_course_toggle);
//                    event1_toggle.setEnabled(false);
//                    event2_toggle.setEnabled(false);
//                    start_course_toggle.setEnabled(false);
                    updateTime();
                } else {
                    enableButtons(event1_toggle, event2_toggle, start_course_toggle);
//                    event1_toggle.setEnabled(true);
//                    event2_toggle.setEnabled(true);
//                    start_course_toggle.setEnabled(true);
                    updateTime();
                }
            }
        });

    }

    public void disableButtons(Button button1, Button button2, Button button3){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }

    public void enableButtons(Button button1, Button button2, Button button3){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }

    /**
     * method to update the time each time a event is selected/released
     */
    public void updateTime(){

        final DateFormat FORMAT
                = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Date date= new Date();
        long dateTime = date.getTime();
        Timestamp currentTime = new Timestamp(dateTime);
        Toast toast = Toast.makeText(context, FORMAT.format(currentTime), duration);
        toast.setGravity(Gravity.BOTTOM, 20, 20);
        toast.show();
    }

    /**
     * method with arguments to set the text, textOn and textOff,
     * it is assumed here that there will be three events with each course
     * @param toggleButton1 : Toggle button 1 for first event
     * @param toggleButton2 : Toggle button 2 for second event
     * @param toggleButton3 : Toggle button 3 for third event
     * @param arrayAdapter : array for events from strings.xml
     */
    public void setText(ToggleButton toggleButton1, ToggleButton toggleButton2,
                        ToggleButton toggleButton3, ArrayAdapter arrayAdapter){
        toggleButton1.setText(arrayAdapter.getItem(1).toString());
        toggleButton1.setTextOff(arrayAdapter.getItem(1).toString());
        toggleButton1.setTextOn(arrayAdapter.getItem(1).toString());
        toggleButton2.setText(arrayAdapter.getItem(2).toString());
        toggleButton2.setTextOff(arrayAdapter.getItem(2).toString());
        toggleButton2.setTextOn(arrayAdapter.getItem(2).toString());
        toggleButton3.setText(arrayAdapter.getItem(3).toString());
        toggleButton3.setTextOff(arrayAdapter.getItem(3).toString());
        toggleButton3.setTextOn(arrayAdapter.getItem(3).toString());
    }

}
