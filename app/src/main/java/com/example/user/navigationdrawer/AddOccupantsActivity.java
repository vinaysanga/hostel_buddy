package com.example.user.navigationdrawer;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddOccupantsActivity extends AppCompatActivity implements View.OnClickListener{
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
                    snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialRedDark,null));
                    snackbar.show();
                }else if(MainActivity.appDB.myDAO().existsRooms(Integer.parseInt(room_no))==0){
                    Snackbar snackbar = Snackbar.make(view, "Room does not exist !!\nPlease add. ", Snackbar.LENGTH_LONG);
                    View snackbarview = snackbar.getView();
                    snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialRedDark,null));
                    snackbar.show();
                }
                else {
                    int vacancy = MainActivity.appDB.myDAO().getVacancy(Integer.parseInt(room_no));
                    if (vacancy-- > 0) {
                        Occupants occupants = new Occupants(null, name, phone, room_no);
                        try {
                            MainActivity.appDB.myDAO().insertOccupants(occupants);
                            Snackbar snackbar = Snackbar.make(view, "Inserted Successfully!!", Snackbar.LENGTH_LONG);
                            MainActivity.appDB.myDAO().updateVacancy(room_no,Integer.toString(vacancy));
                            View snackbarview = snackbar.getView();
                            snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialGreenDark,null));
                            snackbar.show();
                        } catch (SQLiteConstraintException e) {
                            Snackbar snackbar = Snackbar.make(view, "The Phone No. already exists", Snackbar.LENGTH_LONG);
                            View snackbarview = snackbar.getView();
                            snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialRedDark,null));
                            snackbar.show();
                        }
                    }
                    else {
                        Snackbar snackbar = Snackbar.make(view, "Sorry, there is no vacancy in Room No. "+room_no+" !!", Snackbar.LENGTH_LONG);
                        View snackbarview = snackbar.getView();
                        snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialRedDark,null));
                        snackbar.show();
                    }
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
        }
    }
}
