package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by user on 12/31/15.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{
    CardView card1,card2,card3,card4;
    TextView totalCount,vacancy;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.first_layout, container, false);
        card1 =(CardView)myView.findViewById(R.id.total_card);
        card2 = (CardView)myView.findViewById(R.id.vacancy_card);
        card3 = (CardView)myView.findViewById(R.id.total_rent_card);
        card4 = (CardView)myView.findViewById(R.id.remaining_rent_card);
        totalCount = (TextView)myView.findViewById(R.id.count_total);
        vacancy = (TextView)myView.findViewById(R.id.total_vacancy);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        String total_count = Integer.toString(MainActivity.appDB.myDAO().getOccupantsCount());
        String total_vacancy = Integer.toString(MainActivity.appDB.myDAO().getVacancyCount());
        totalCount.setText(total_count);
        vacancy.setText(total_vacancy);
        return myView;
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.total_card:
                Fragment secondFragment = new SecondFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, secondFragment);
                transaction.commit();
                MainActivity.showFAB();
                break;
            case R.id.vacancy_card:
                Intent intent = new Intent(getContext(),VacancyActivity.class);
                startActivity(intent);
                break;
            case R.id.total_rent_card:
                break;
            case R.id.remaining_rent_card:
                break;
        }
    }
}
