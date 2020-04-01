package it.xonne.cisitasample2020.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import it.xonne.cisitasample2020.R;

public class ListUsersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        executeDonwloadData();
    }

    /**
     * Eseguo operazione di download dei dati
     * https://private-241152-cisitatest.apiary-mock.com/users
     */
    public void executeDonwloadData() {

        //istazione client http per dialogare con il mio back-end
        AsyncHttpClient client = new AsyncHttpClient();
        // eseguo chimata HTTP tramite metodo GET e delego la risposta alle 2 callback "onSuccess" e "onFailure"
        client.get("https://private-241152-cisitatest.apiary-mock.com/users", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {

                    String responseString = new String(responseBody, "UTF-8");
                    Log.d("CISITA", "onSuccess chiamata avvenuta con successo: " + responseString);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("CISITA", "onFaiulre errore nella chiamata: " + error.getLocalizedMessage());
            }
        });

    }


}
