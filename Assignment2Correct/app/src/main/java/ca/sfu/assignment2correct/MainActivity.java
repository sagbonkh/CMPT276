package ca.sfu.assignment2correct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import model.Lens;
import model.LensManager;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1014;
    LensManager manager = LensManager.getInstance();
    private String make = "C";
    private int focalLength = 0;
    private double aperature = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent i = AddLens.makeLaunchIntent(this, "Bob", 101, 1.1);
        //startActivity(i);
            startActivityForResult(i, REQUEST_CODE);
//            populatemanager(make, focalLength, aperature);
//            populateListView();
        }
        );
        Intent intent = getIntent();
        make = intent.getStringExtra("ca/sfu/assignment2correct/AddLens.java:78");
        focalLength = intent.getIntExtra("ca/sfu/assignment2correct/AddLens.java:82",0);
        aperature = intent.getDoubleExtra("ca/sfu/assignment2correct/AddLens.java:86", 0);
        populatemanager(make, focalLength, aperature);
        populateListView();
        registerClickCallBack();
    }



    private void registerClickCallBack() {
        ListView list = findViewById(R.id.myList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;

                String name = manager.retrieveLens(position).getMake();
                int focus = manager.retrieveLens(position).getFocalLength();
                double ape = manager.retrieveLens(position).getAperature();
                Intent intent = DepthOfFieldActivity.makeIntent(MainActivity.this);
                intent.putExtra("ca/sfu/assignment2correct/MainActivity.java:62",name);
                intent.putExtra("ca/sfu/assignment2correct/MainActivity.java:63",focus);
                intent.putExtra("ca/sfu/assignment2correct/MainActivity.java:64",ape);
                startActivity(intent);
            }
        });
    }

    private void populatemanager(String model, int length, double ap) {
        if(manager.getSize() == 0)
        {
            manager.addLens(new Lens("Canon", 1.8, 50));
            manager.addLens(new Lens("Tamron", 2.8, 90));
            manager.addLens(new Lens("Sigma", 2.8, 200));
            manager.addLens(new Lens("Nikon", 4, 200));
        }
       else{
            manager.addLens(new Lens(model,ap,length));
        }
    }


    private void populateListView() {
        int size = manager.getSize();

        String[] myItems = new String[size];
        for(int i = 0; i < size;i++){
            myItems[i] = manager.retrieveLens(i).toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myItems);

        ListView list = findViewById(R.id.myList);
        list.setAdapter(adapter);
    }


}
