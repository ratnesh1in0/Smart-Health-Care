package com.example.shp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ClinicsFragment extends AppCompatActivity {

    // Define the Teacher Firebase DatabaseReference
    private DatabaseReference ref;

    // Define a String ArrayList for the teachers
    private ArrayList<String> clinicList = new ArrayList<>();

    // Define a ListView to display the data
    private ListView listView;

    // Define an ArrayAdapter for the list
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_clinics);
        getSupportActionBar().setTitle("SHP Patient");

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Associate the Teacher Firebase Database Reference with the database's teacher object
        ref = FirebaseDatabase.getInstance().getReference();
        ref = ref.child("Clinic");

        // Associate the teachers' list with the corresponding ListView
        listView = (ListView) findViewById(R.id.list_clinic);

        // Set the ArrayAdapter to the ListView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clinicList);
        listView.setAdapter(arrayAdapter);

        // Attach a ChildEventListener to the teacher database, so we can retrieve the teacher entries
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // Get the value from the DataSnapshot and add it to the teachers' list
                Clinic clinic = (Clinic) dataSnapshot.getValue(Clinic.class);
                String clinicString = String.valueOf(clinic);
                arrayAdapter.add(clinicString);

                // Notify the ArrayAdapter that there was a change
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

