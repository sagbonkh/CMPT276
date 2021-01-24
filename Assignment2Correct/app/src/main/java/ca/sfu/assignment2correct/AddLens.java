package ca.sfu.assignment2correct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Lens;
import model.LensManager;

public class AddLens extends AppCompatActivity {
    private static final String EXTRA_MAKE = "ca/sfu/assignment2correct/AddLens.java:22";
    private static final String EXTRA_LENGTH = "ca/sfu/assignment2correct/AddLens.java:22";
    private static final String EXTRA_APERATURE = "ca/sfu/assignment2correct/AddLens.java:23";
    Toolbar toolbar;
    private String make;
    private String make1;
    private int focalLength;
    private double aperature;
    private Lens Lenny;
    private EditText userTextEntry;
    private EditText userfocusEntry;
    private EditText userAperatureEntry;
    private Button btn;
    private double aperature1;
    private int focalpt;

    //private static String EXTRA_MESSAGE = "Extra - message";

    public static Intent makeLaunchIntent(Context c, String make, int length, double aperature) {
        Intent intent = new Intent(c, AddLens.class);
        intent.putExtra(EXTRA_MAKE, make);
        intent.putExtra(EXTRA_LENGTH, length);
        intent.putExtra(EXTRA_APERATURE, aperature);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        extractDataFromIntent();
        userTextEntry = findViewById(R.id.makeInput);
        userfocusEntry = findViewById(R.id.focalptInput);
        userAperatureEntry = findViewById(R.id.AperatureIn);
        setupSavebtn();
//        setUpEndActivityBtn();

    }

    private void setUpIntentReturn() {
        Intent intent = new Intent(AddLens.this, MainActivity.class);
        intent.putExtra("ca/sfu/assignment2correct/AddLens.java:78", make1);
        intent.putExtra("ca/sfu/assignment2correct/AddLens.java:82", focalpt);
        intent.putExtra("ca/sfu/assignment2correct/AddLens.java:86", aperature1);
        startActivity(intent);
    }

    private void addLensToList() {
        LensManager manager = LensManager.getInstance();
        manager.addLens(Lenny);
    }

    private void createLensObj() {
        Lenny = new Lens(make, aperature, focalLength);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        make = intent.getStringExtra(EXTRA_MAKE);
        focalLength = intent.getIntExtra(EXTRA_LENGTH, 0);
        aperature = intent.getDoubleExtra(EXTRA_APERATURE, 0.0);
    }

    private void setupSavebtn() {
        btn = findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {

            private String aperature;
            private String focallgt;

            @Override
            public void onClick(View v) {
                    userTextEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(userTextEntry.getText().length() <= 0 ){
                                userTextEntry.setError("Error");
                            }
                        }
                    });
                    make1 = userTextEntry.getText().toString().trim();
                    userfocusEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(userfocusEntry.getText().length() <= 0){
                                userfocusEntry.setError("Error");
                            }
                        }
                    });
                    focallgt = userfocusEntry.getText().toString().trim();

                    userAperatureEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(userfocusEntry.getText().length() <= 0){
                                userfocusEntry.setError("Error");
                            }
                        }
                    });
                    aperature = userAperatureEntry.getText().toString().trim();
//                    if (make.length() != 0 && focallgt.length() != 0 && aperature.length() != 0)
//                        i++;
//                    else {
//                        Toast.makeText(AddLens.this,
//                                "Entries must be filled in", Toast.LENGTH_SHORT).show();
//                    }

                focalpt = Integer.parseInt(focallgt);
                aperature1 = Double.parseDouble(aperature);
                setUpIntentReturn();
            }
        });
    }

//    private TextWatcher loginTextwatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String name = userTextEntry.getText().toString().trim();
//            String focus = userfocusEntry.getText().toString().trim();
//            String ap = userAperatureEntry.getText().toString().trim();
//
//            btn.setEnabled(userTextEntry.length() != 0
//                && userfocusEntry.length() != 0
//                    &&userAperatureEntry.length() != 0);
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddLens.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
    //
