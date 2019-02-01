package com.hexoskin.hexoskin_smart_android;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;

import org.w3c.dom.Text;

public class CourseSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
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

        final Spinner courses_spinner = (Spinner) findViewById(R.id.spnCourses);
        // an ArrayAdapter using the array from the string.xml
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.courses_array, android.R.layout.simple_spinner_item);

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
//                R.array.courses_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courses_spinner.setAdapter(adapter);
        final Button event_selection_button = (Button) findViewById(R.id.btnEventSelection);

        courses_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_course = (String) parent.getItemAtPosition(position);
//                StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
//                SpannableStringBuilder ssBuilder = new SpannableStringBuilder(selected_course);
                TextView status_textview = (TextView) findViewById(R.id.txtStatus);
                if (position == 0) {
                    status_textview.setText("Please select one course!");
                    event_selection_button.setEnabled(false);
                }else {
                    status_textview.setText("You selected: '" + selected_course + "'");
                    event_selection_button.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String cc = (String) courses_spinner.getSelectedItem();

        //t.setText(cc);

//        String s = adapter.getItem(1).toString();
//
//        View v = findViewById(R.id.view);

        //t.setText(s);

        //Button submit_button = (Button) findViewById(R.id.btnSubmit);




        //event_selection_button.setVisibility(Button.INVISIBLE);

//        submit_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                event_selection_button.setEnabled(false);
//                TextView status_textview = (TextView) findViewById(R.id.txtStatus);
//                //TextView selected_textview = (TextView) findViewById(R.id.txtSelectedCourse);
//                if (courses_spinner.getSelectedItem() == adapter.getItem(0) )
//                {
//                    //t.setTextColor(Color.red(255));
//                    status_textview.setText("You must select a course!!");
//                    status_textview.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
//                    //event_selection_button.setEnabled(false);
//                    event_selection_button.setVisibility(Button.INVISIBLE);
//
//                }
//                else
//                {
//                    //t.setTextColor(Color.green(255));
//                    status_textview.setText("You selected : " + courses_spinner.getSelectedItem().toString());
//                    status_textview.setTextColor(getResources().getColor(android.R.color.holo_green_light));
//                    //event_selection_button.setEnabled(true);
//                    event_selection_button.setEnabled(true);
//                    event_selection_button.setVisibility(Button.VISIBLE);
//
//                    //selected_textview.setText(courses_spinner.getSelectedItem().toString());
//
//                }
//            }
//        });

        event_selection_button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Intent i = new Intent(CourseSelection.this, EventSelection.class);
                i.putExtra("selected_course", courses_spinner.getSelectedItem().toString());
                //i.putExtra( "adapter", adapter.toString());
                startActivity(i);
                //startActivity(new Intent(CourseSelection.this, EventSelection.class));
            }
        });

        //t.setText(cc.toString());

//        if (cc != adapter.getItem(0) )
//        {
//            t.setText("Please enter one");
//        }
//        else
//        {
//            t.setText("You selected :" + cc);
//        }



    }

}
