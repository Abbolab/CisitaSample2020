package it.xonne.cisitasample2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("CISITA", "onCreate App Cisita!!");

        //prova GIT

        // definire quale lauyput deve mostrare la nosra schermata
        setContentView(R.layout.activity_main);

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
            }
        });


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
