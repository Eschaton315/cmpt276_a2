package com.example.cmpt276a2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cmpt276a2.model.lens;
import com.example.cmpt276a2.model.lensManager;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {
    public static final String EXTRA_ARR = "com.example.cmpt276a2.EXTRA_ARR";

    private List<lens> lensArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpDefLens();

        setupDoFBtn();
        setupEditBtn();

    }


    public void setUpDefLens() {
        lensArray.add(new lens("Canon", 1.8, 50, 1));
        lensArray.add(new lens("Tamron", 2.8, 90, 2));
        lensArray.add(new lens("Sigma", 2.8, 200, 3));
        lensArray.add(new lens("Nikon", 4, 200, 4));

    }


    private void setupEditBtn() {
        Button editBtn = (Button) findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Lens Settings", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void setupDoFBtn() {
        Button dofBtn = (Button) findViewById(R.id.DepthBtn);
        dofBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Set Up DoF", Toast.LENGTH_SHORT)
                        .show();

                //start up Dof setting from A1

                Intent intentDoF = SetUpDof.makeIntent(MainActivity.this);
                intentDoF.putExtra(EXTRA_ARR, (Serializable) lensArray);
                startActivity(intentDoF);
            }
        });
    }
}