package ca.sfu.assignment2correct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.DOFcalculator;
import model.Lens;

public class DepthOfFieldActivity extends AppCompatActivity {
EditText cOfConf, DtoSubj, ChAp;
TextView txt1, txt2, txt3, txt4;
private Button calcBtn;
    private double circleOfconfusion;
    private double distanceToSubj;
    private double chosenAp;
    private String model;
    private int fp;
    private double ap;
    private double hyperfocaldist;
    private double nearFocalPoint;
    private double farFocalPoint;
    private double depthOffield;
    Toolbar toolbar;

    public static Intent makeIntent(Context c){
        return new Intent(c, DepthOfFieldActivity.class);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depth_of_field);
        toolbar = findViewById(R.id.toolbar2);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        setUpEndActivityBtn();
        setUpCalculateBtn();
        catchIntents();
    }

    private void calcDofField() {
        DOFcalculator depthOffieldCalc = new DOFcalculator(distanceToSubj,chosenAp,
                new Lens(model,ap,fp), circleOfconfusion);
        hyperfocaldist = depthOffieldCalc.getHyperfocaldistance();
        nearFocalPoint = depthOffieldCalc.getNearfocalpoint();
        farFocalPoint = depthOffieldCalc.getFarfocalpoint();
        depthOffield = depthOffieldCalc.getDepthOfField();
    }

    private void setUpCalculateBtn() {
        calcBtn = findViewById(R.id.calculate);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1 = findViewById(R.id.Nearfocalsoln);
                txt2 = findViewById(R.id.nearfocalsoln);
                txt3 = findViewById(R.id.nearfocalsoln2);
                txt4 = findViewById(R.id.nearfocalsoln3);

                cOfConf = findViewById(R.id.CircleOfConfusion);
                String circle = cOfConf.getText().toString();
                circleOfconfusion = Double.parseDouble(circle);

                DtoSubj = findViewById(R.id.DistanceInput);
                String Nrdist= DtoSubj.getText().toString();
                distanceToSubj = Double.parseDouble(Nrdist);
                distanceToSubj *= 1000;

                ChAp = findViewById(R.id.distanceInput);
                String ChAper = ChAp.getText().toString();
                chosenAp = Double.parseDouble(ChAper);

                if(chosenAp >= 1.4 && circleOfconfusion > 0 && distanceToSubj > 0) {
                    calcDofField();
                    nearFocalPoint /= 1000;
                    hyperfocaldist /=1000;
                    depthOffield /= 1000;
                    farFocalPoint/= 1000;


                    String outputFocalDist = Double.toString(nearFocalPoint);
                    txt1.setText(outputFocalDist);
                    String outputFarFocalpt = Double.toString(farFocalPoint);
                    txt2.setText(outputFarFocalpt);
                    String outputDOF = Double.toString(depthOffield);
                    txt3.setText(outputDOF);
                    String outputHyperfocal = Double.toString(hyperfocaldist);
                    txt4.setText(outputHyperfocal);
                }
                else{
                    String str = "(aperature > 1.4, dist > 0, COC > 0)";
                    txt1.setText(str);
                    txt2.setText(str);
                    txt3.setText(str);
                    txt4.setText(str);
                }
            }
        });
    }

    private void catchIntents() {
        model = getIntent().getStringExtra("ca/sfu/assignment2correct/MainActivity.java:62");
        fp = getIntent().getIntExtra("ca/sfu/assignment2correct/MainActivity.java:63",0);
        ap = getIntent().getDoubleExtra("ca/sfu/assignment2correct/MainActivity.java:64",0);

    }

    private void setUpEndActivityBtn() {
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DepthOfFieldActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
