package it.xonne.cisitasample2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.xonne.cisitasample2020.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("CISITA", "onCreate App Cisita!!");

        // definire quale lauyput deve mostrare la nosra schermata
        setContentView(R.layout.activity_main);

        // creo Home Fragment ad avvio
        createHomeFragment();

        // inflate delle view dal layout
        Button buttonClickMe = (Button)findViewById(R.id.buttonClickMe);
        final TextView myTextView = (TextView)findViewById(R.id.textViewHelloWorld);


        // intercetto il click sul pulsante
        buttonClickMe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("CISITA", "onClick event called!");

                // incremento il mio count
                count++;

                myTextView.setText("Hai premuto " + String.valueOf(count) + " volte");

                //cambio activity
                GoToSecondActivity();
            }
        });


    }

    // Istanzio un Intent per passare alla Second Activity
    private void GoToSecondActivity() {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }

    // creo e instanzio Home Fragment ad avvio applicazione
    private void createHomeFragment() {

        // creo una instanza del Home Fragment
        HomeFragment homeFragment = new HomeFragment();

        // inizio una transizione per aggiungere il fragment tramite il Fragment Manager ottenuto dall'activity
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();

        // aggiungo al FrameLyoaut contenuto in "activity_main.layout" l'instanza di Fragment e associo un TAG
        transaction.add(R.id.FrameLayoutContainer, homeFragment, "HOME_FRAGMENT");

        // finalizzo operazione di transizione
        transaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("CISITA", "onResume App Cisita!!");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("CISITA", "onPause App Cisita!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("CISITA", "onDestroy App Cisita!");
    }
}
