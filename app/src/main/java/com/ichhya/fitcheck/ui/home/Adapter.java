package com.ichhya.fitcheck.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ichhya.fitcheck.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Adapter extends ArrayAdapter<Challenge> {

    private Activity context;
    private ArrayList<Challenge> list;

    public Adapter(Activity context, ArrayList<Challenge> list){
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View view = inflater.inflate(R.layout.challenge_inflator,null);

        TextView challenge_name=view.findViewById(R.id.challenge_name),
                challenge_by = view.findViewById(R.id.challenge_by),
                days_left = view.findViewById(R.id.days_left),
                status=view.findViewById(R.id.status);

        Challenge challenge = list.get(position);

        Calendar calendar = Calendar.getInstance();
        Date current_date = calendar.getTime();

        Long difference = ((current_date.getTime())-((challenge.getChallengeDate()).getTime()))/8640000;

        Long remaining_days = (challenge.getChallengeDays())-difference;

        challenge_name.setText(challenge.getChallengeName());
        challenge_by.setText(challenge.getChallengeBy());

        if (remaining_days>0){
            status.setText(String.valueOf(remaining_days));
            days_left.setText("days left");
        }else {
            status.setText("Completed");
        }

        //Toast.makeText(context,challenge.getChallengeName(),Toast.LENGTH_LONG).show();

        return view;
    }
}
