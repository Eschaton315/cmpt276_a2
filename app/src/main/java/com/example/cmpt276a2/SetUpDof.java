package com.example.cmpt276a2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmpt276a2.model.lens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.cmpt276a2.MainActivity.EXTRA_ARR;

public class SetUpDof extends AppCompatActivity implements Serializable {
    public static final String EXTRA_LENS = "com.example.cmpt276a2.EXTRA_LENS";

    public static Intent makeIntent(Context context) {
        return new Intent(context, SetUpDof.class);
    }

    List<lens> lensArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_dof);

        Intent intent = getIntent();
        lensArray = (List<lens>) intent.getSerializableExtra(EXTRA_ARR);
        setupLensList();
    }

    private void setupLensList() {
        ArrayAdapter<lens> adapter = new lensAdapter();
        ListView list = (ListView) findViewById(R.id.lensList);
        list.setAdapter(adapter);

    }

    private class lensAdapter extends ArrayAdapter<lens> {
        public lensAdapter() {
            super(SetUpDof.this, R.layout.lens_list, lensArray);
        }

        @Override
        public View getView(int itemNum, View convertView, ViewGroup parent) {
            ListView list = (ListView) findViewById(R.id.lensList);
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.lens_list, parent, false);
            }
            final lens currentLens = lensArray.get(itemNum);


            Button lenBtn = (Button) itemView.findViewById(R.id.item_LensBtn);
            lenBtn.setText(currentLens.getName());

            TextView ApeText = (TextView) itemView.findViewById(R.id.item_Apeture);
            ApeText.setText("F " + currentLens.getMaxAperture());

            TextView lenText = (TextView) itemView.findViewById(R.id.item_flength);
            lenText.setText(currentLens.getFocalLength() + " mm");

            lenBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SetUpDof.this, currentLens.getName(), Toast.LENGTH_SHORT).show();

                    Intent intentDoF2 = SetDof2.makeintent(SetUpDof.this);
                    intentDoF2.putExtra(EXTRA_LENS, (Serializable) currentLens);
                    startActivity(intentDoF2);
                }
            });


            return itemView;
        }

    }
}