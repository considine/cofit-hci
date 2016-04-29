package com.example.johnpconsidine.bengalworkouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WorkoutsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_page);

        Button button = (Button)  findViewById(R.id.testButton);
        assert button != null;
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(WorkoutsPage.this, "TEST CONTENT", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

}
