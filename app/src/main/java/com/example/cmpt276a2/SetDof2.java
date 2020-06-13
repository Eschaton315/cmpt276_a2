package com.example.cmpt276a2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmpt276a2.model.lens;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.cmpt276a2.MainActivity.EXTRA_ARR;
import static com.example.cmpt276a2.SetUpDof.EXTRA_LENS;

public class SetDof2 extends AppCompatActivity {

    public static Intent makeintent(Context context) {
        return new Intent(context, SetDof2.class);
    }

    //set up variables

    lens selectedLens;
    double aperture;
    double distance;

    EditText ApeInput;
    EditText DisInput;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_dof2);
        Intent intent = getIntent();
        selectedLens = (lens) intent.getSerializableExtra(EXTRA_LENS);

        final TextView textView = (TextView) findViewById(R.id.rep_LensRep);
        textView.setText("Lens: " + selectedLens.getName() + "\nMax Aperture: F " + selectedLens.getMaxAperture() + "\nFocal Length: " + selectedLens.getFocalLength() + "mm");

        ApeInput = (EditText) findViewById(R.id.Edit_Aperture);
        DisInput = (EditText) findViewById(R.id.Edit_Distance);
        submitBtn = (Button) findViewById(R.id.item_okBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aperture = Double.valueOf(ApeInput.getText().toString());
                distance = Double.valueOf(DisInput.getText().toString());
                if (aperture >= selectedLens.getMaxAperture()) {
                    Toast.makeText(SetDof2.this, "PASS", Toast.LENGTH_SHORT).show();
                    //calculations

                    double hyperFocalDist = ((Math.pow(selectedLens.getFocalLength(), 2)) / (0.029 * aperture));
                    double nearFocalPoint = ((hyperFocalDist * (distance * 1000)) / (hyperFocalDist + ((distance * 1000) - selectedLens.getFocalLength())));
                    double farFocalPoint;
                    if ((distance * 1000) > hyperFocalDist)
                        farFocalPoint = Double.POSITIVE_INFINITY;
                    else
                        farFocalPoint = ((hyperFocalDist * (distance * 1000)) / (hyperFocalDist - ((distance * 1000) - selectedLens.getFocalLength())));
                    double depthField = farFocalPoint - nearFocalPoint;

                    final TextView DofText = (TextView) findViewById(R.id.Text_DepthField);
                    DofText.setText("Depth of Field: " + formatM(depthField / 1000) + " m");

                } else {
                    if (aperture < selectedLens.getMaxAperture()) {
                        Toast.makeText(SetDof2.this, "FAIL: EXCEEDED MAX F VALUE", Toast.LENGTH_SHORT).show();
                    }
                    if (distance < 0) {
                        Toast.makeText(SetDof2.this, "FAIL: INVALID DISTANCE", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

    public String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}