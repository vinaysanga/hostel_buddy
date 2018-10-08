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

public class AddRoomsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed1,ed2,ed3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        ed1 = (EditText) findViewById(R.id.no_rooms_start);
        ed2 = (EditText) findViewById(R.id.no_rooms_end);
        ed3 = (EditText) findViewById(R.id.max_occ);
        btn = (Button) findViewById(R.id.room_add_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.room_add_btn:
                int i;
                String field1=ed1.getText().toString(),field2=ed2.getText().toString(),field3=ed3.getText().toString();
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                if(TextUtils.isEmpty(field1) || TextUtils.isEmpty(field2)|| TextUtils.isEmpty(field3)) {
                    Snackbar snackbar = Snackbar.make(view,"Fields can't be empty !!",Snackbar.LENGTH_LONG);
                    View snackbarview = snackbar.getView();
                    snackbarview.setBackgroundColor(0xFFD50000);
                    snackbar.show();
                }
                else {
                    Integer rooms_start = Integer.parseInt(field1);
                    Integer rooms_end = Integer.parseInt(field2);
                    Integer max_limit = Integer.parseInt(field3);
                    try {
                        for (i = rooms_start; i <= rooms_end; i++) {
                            Rooms room = new Rooms(i, max_limit);
                            MainActivity.appDB.myDAO().insertRooms(room);
                        }
                        if (i == rooms_end + 1) {
                            Snackbar snackbar = Snackbar.make(view, "Rooms added successfully !! ", Snackbar.LENGTH_LONG);
                            View snackbarview = snackbar.getView();
                            snackbarview.setBackgroundColor(getResources().getColor(R.color.materialGreenDark));
                            snackbar.show();
                        } else {
                            Snackbar snackbar = Snackbar.make(view, "Some Rooms couldn't be added !!", Snackbar.LENGTH_LONG);
                            View snackbarview = snackbar.getView();
                            snackbarview.setBackgroundColor(getResources().getColor(R.color.materialRedDark));
                            snackbar.show();
                        }
                    }
                    catch (SQLiteConstraintException e){
                        Snackbar snackbar = Snackbar.make(view, "Room No(s). already exists ", Snackbar.LENGTH_LONG);
                        View snackbarview = snackbar.getView();
                        snackbarview.setBackgroundColor(getResources().getColor(R.color.materialRedDark));
                        snackbar.show();
                    }
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
        }
    }
}
