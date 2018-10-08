package com.example.user.navigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditOccupantsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3;
    Button savebtn;
    String oid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_occupants);
        oid = getIntent().getExtras().getString("Oid");
        String name = getIntent().getExtras().getString("Name");
        String phone = getIntent().getExtras().getString("Phone");
        String room = getIntent().getExtras().getString("Room");
        e1 = (EditText)findViewById(R.id.edit_occ_name);
        e2 = (EditText)findViewById(R.id.edit_occ_phn);
        e3 = (EditText)findViewById(R.id.edit_occ_room);
        savebtn = (Button)findViewById(R.id.edit_occ_save_btn);
        e1.setText(name);
        e2.setText(phone);
        e3.setText(room);
        savebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = e1.getText().toString();
        String phone = e2.getText().toString();
        String room = e3.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("Name",name);
        intent.putExtra("Phone",phone);
        intent.putExtra("Room",room);
        intent.putExtra("Oid",oid);
        setResult(2,intent);
        finish();
    }
}
