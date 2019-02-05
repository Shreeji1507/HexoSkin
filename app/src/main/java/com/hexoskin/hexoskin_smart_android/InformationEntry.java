package com.hexoskin.hexoskin_smart_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InformationEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_entry);
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

        final Button event_selection_button = (Button) findViewById(R.id.btnEventSelection);
        event_selection_button.setEnabled(false);

        final EditText group_id_input = (EditText) findViewById(R.id.editTextGroupId);
        final EditText first_name_input = (EditText) findViewById(R.id.editTextFirstName);
        final EditText last_name_input = (EditText) findViewById(R.id.editTextLastName);

        group_id_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableButton(group_id_input, first_name_input, last_name_input, event_selection_button);
            }

            @Override
            public void afterTextChanged(Editable s) {
                disableButton(group_id_input, first_name_input, last_name_input, event_selection_button);
            }
        });

        first_name_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableButton(group_id_input, first_name_input, last_name_input, event_selection_button);
            }

            @Override
            public void afterTextChanged(Editable s) {
                disableButton(group_id_input, first_name_input, last_name_input, event_selection_button);
            }
        });

        last_name_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableButton(group_id_input, first_name_input, last_name_input, event_selection_button);
            }

            @Override
            public void afterTextChanged(Editable s) {
                disableButton(group_id_input, first_name_input, last_name_input, event_selection_button);
            }
        });

        event_selection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InformationEntry.this, EventSelection.class);
                i.putExtra("selected_course",
                            getIntent().getStringExtra("selected_course").toString());
                startActivity(i);
            }
        });
    }

    /**
     * a function that is used to decide if something is entered in the input
     * text-box and enables the button only if all the input boxes are not empty
     * @param editText1 : input text box 1
     * @param editText2 : input text box 2
     * @param editText3 : input text box 3
     * @param button    : button to go to the next screen
     */
    public void enableButton(EditText editText1, EditText editText2, EditText editText3,
                             Button button){

        if (editText1.getText().toString().length() > 0 &&
                editText2.getText().toString().length() > 0 &&
                editText3.getText().toString().length() > 0)
        {
            button.setEnabled(true);
        }
    }

    /**
     * a function that is used to decide if something is entered in the input
     * text-box and disables the button if any one of them is empty
     * @param editText1 : input text box 1
     * @param editText2 : input text box 2
     * @param editText3 : input text box 3
     * @param button    : button to go to the next screen
     */
    public void disableButton(EditText editText1, EditText editText2, EditText editText3,
                             Button button){

        if (editText1.getText().toString().length() == 0 ||
                editText2.getText().toString().length() == 0 ||
                editText3.getText().toString().length() == 0)
        {
            button.setEnabled(false);
        }
    }

}
