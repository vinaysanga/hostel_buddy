package com.example.user.navigationdrawer;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OccupantsActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ed1,ed2,ed3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupants);
        ed1 = (EditText) findViewById(R.id.occ_name);
        ed2 = (EditText) findViewById(R.id.occ_phn);
        ed3 = (EditText) findViewById(R.id.occ_room);
        btn = (Button) findViewById(R.id.occ_add_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.occ_add_btn:
                String name = ed1.getText().toString();
                String phone = ed2.getText().toString();
                String room_no = ed3.getText().toString();
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)|| TextUtils.isEmpty(room_no)) {
                    Snackbar snackbar = Snackbar.make(view,"Fields can't be empty !!",Snackbar.LENGTH_LONG);
                    View snackbarview = snackbar.getView();
                    snackbarview.setBackgroundColor(0xFFD50000);
                    snackbar.show();
                }
                else {
                    Occupants occupants = new Occupants(name,phone,room_no);
                    try {
                        long id = MainActivity.appDB.myDAO().insertOccupants(occupants);
                        Snackbar snackbar = Snackbar.make(view,"Inserted Successfully!! ID = " + id , Snackbar.LENGTH_LONG);
                        View snackbarview = snackbar.getView();
                        snackbarview.setBackgroundColor(0xFF00C853      );
                        snackbar.show();
                    } catch (Exception e) {
                        String error = e.getClass().toString();
                        Snackbar snackbar = Snackbar.make(view, error, Snackbar.LENGTH_LONG);
                        View snackbarview = snackbar.getView();
                        snackbarview.setBackgroundColor(0xFFD50000);
                        snackbar.show();
                    }
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
        }
    }
}
