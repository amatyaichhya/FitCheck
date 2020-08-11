package com.ichhya.fitcheck.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ichhya.fitcheck.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    FloatingActionButton add;

    ListView list_View;

    ArrayList<Challenge> challenge_list;

    DatabaseReference databaseReference;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        challenge_list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("challenge");

        add = root.findViewById(R.id.add);

        list_View = root.findViewById(R.id.list_view);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addChallenge();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                challenge_list.clear();

                for (DataSnapshot challengeSnapshot : snapshot.getChildren()){

                    Challenge challenge = challengeSnapshot.getValue(Challenge.class);

                    challenge_list.add(challenge);
                }
                Log.e("size is",""+ challenge_list.size());
//                Log.e("list is",""+challenge_list);

                ArrayAdapter adapter = new Adapter(getActivity(),challenge_list);
                list_View.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void addChallenge(){

        final Dialog dialog = new Dialog(getActivity(),R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setTitle("Add Challenge");

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_addchallenge,null);

        Button add = view.findViewById(R.id.add);
        Button cancel = view.findViewById(R.id.cancel);

        final EditText name = view.findViewById(R.id.name);
        final EditText challenge_by = view.findViewById(R.id.challengeby);
        final EditText days = view.findViewById(R.id.days);
        final EditText waist = view.findViewById(R.id.waist);
        final EditText thigh = view.findViewById(R.id.thigh);
        final EditText lower_thigh = view.findViewById(R.id.lowerthigh);
        final EditText calf = view.findViewById(R.id.calf);
        final EditText arm = view.findViewById(R.id.arm);
        final EditText weight = view.findViewById(R.id.weight);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty(name) && isEmpty(days) && isEmpty(challenge_by) && isEmpty(waist) && isEmpty(thigh) && isEmpty(lower_thigh) && isEmpty(calf) && isEmpty(arm) && isEmpty(weight)){

                    String nameVal = name.getText().toString();

                    String challenge_byVal = challenge_by.getText().toString();

                    String a = days.getText().toString();
                    int daysVal = Integer.parseInt(a);

                    String b = waist.getText().toString();
                    double waistVal = Double.parseDouble(b);

                    String c = thigh.getText().toString();
                    double thighVal = Double.parseDouble(c);

                    String d = lower_thigh.getText().toString();
                    double lower_thighVal = Double.parseDouble(d);

                    String e = calf.getText().toString();
                    double calfVal = Double.parseDouble(e);

                    String f = arm.getText().toString();
                    double armVal = Double.parseDouble(f);

                    String g = weight.getText().toString();
                    double weightVal = Double.parseDouble(g);
//
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                    String date = df.format(Calendar.getInstance().getTime());

                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();

                    String id = databaseReference.push().getKey();

                    Challenge challenge = new Challenge(id, nameVal, challenge_byVal, daysVal, date);

                    databaseReference.child(id).setValue(challenge);

                    dialog.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.setContentView(view);
        dialog.show();

    }

    public boolean isEmpty(EditText view) {
        if (view.getText().toString().length() > 0) {
            return true;
        } else {
            view.setError("This field cannot be empty");
            //password.setError("Enter Password");
            return false;
        }
    }
}