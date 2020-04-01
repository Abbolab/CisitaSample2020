package it.xonne.cisitasample2020.fragments;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import it.xonne.cisitasample2020.R;
import it.xonne.cisitasample2020.models.User;

public class ListUsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ottengo riferimento recycler view
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


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

                // dataset
                ArrayList<User> listUsers = new ArrayList();

                try {

                    //convertiamo l'array di byte in stringa tramite charset UTF-8
                    String responseString = new String(responseBody, "UTF-8");
                    //Log.d("CISITA", "onSuccess chiamata avvenuta con successo: " + responseString);

                    // instanziamo il nostro JSON object a partire dalla stringa ottenuta dal server
                    JSONObject jsonObject = new JSONObject(responseString);
                    // ottengo il json array dal json obhect tramite la chiave "users"
                    JSONArray jsonArray = jsonObject.getJSONArray("users");

                    // ciclo json array per serializzare il contenuto
                    for(int index = 0; index < jsonArray.length(); index++) {
                        //ottengo json object che rappresent User tramite posizione del json array
                        JSONObject userJsonObject = jsonArray.getJSONObject(index);

                        //istanzio un oggetto Java User
                        User currentUser = new User();
                        //inizio serializzazione mappatura da JSON a oggetto JAVA
                        currentUser.name = userJsonObject.getString("name");
                        currentUser.surname = userJsonObject.getString("surname");
                        currentUser.visibile = userJsonObject.getBoolean("visibile");
                        currentUser.age = userJsonObject.getInt("age");

                        // formatto data da stringa a oggeto
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm HH:mm");
                        Date dateRegistration = dateFormat.parse(userJsonObject.getString("dateRegistration"));
                        // assegno date registration letto da json come stringa e convertito in date
                        currentUser.date = dateRegistration;

                        //Log.d("CISITA", "User index: " + index + " toString: " + currentUser.getFullName());

                        //aggiungo oggetto user a dataset
                        listUsers.add(currentUser);
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                Log.d("CISITA", "List Users count: " + listUsers.size());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("CISITA", "onFaiulre errore nella chiamata: " + error.getLocalizedMessage());
            }
        });

    }


}
