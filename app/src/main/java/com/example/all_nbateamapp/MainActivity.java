package com.example.all_nbateamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> playerList = new ArrayList<>();
    static ArrayList<String> firstNames = new ArrayList<String>();
    ArrayList<Integer> pos_List = new ArrayList<>();
    ArrayList<Integer> PER_List = new ArrayList<>();
    ArrayList<Integer> winShare_List = new ArrayList<>();
    ArrayList<Integer> TS_List = new ArrayList<>();
    ArrayList<Integer> TOV_List = new ArrayList<>();
    ArrayList<Integer> win_perc_List = new ArrayList<>();
    ArrayList<Integer> APM_List = new ArrayList<>();
    ArrayList<Integer> PPG_List = new ArrayList<>();
    ArrayList<Integer> RPG_List = new ArrayList<>();

    ArrayList<Integer> NBA_Ranking = new ArrayList<>();

    int ID_num = 0;
    Button addPlayer;
    Button viewStats;
    Button backButton;
    EditText ID_input;
    TextView viewStatsBox;
    TextView playerBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID_input =  (EditText) findViewById(R.id.playerID);
        addPlayer = findViewById(R.id.addPlayer);
        viewStats = findViewById(R.id.viewStats);
        backButton = findViewById(R.id.backButton);
        viewStatsBox = (TextView)findViewById(R.id.playerStats);
        playerBox = (TextView)findViewById(R.id.playerList);

        get_json();

        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //ID_num = Integer.parseInt(ID_input.getText().toString());
                ID_num = 4;
                String NBA_name = "";
                if (ID_num > 0){
                    if (NBA_Ranking.size() < 11){
                        //NBA_name = firstNames.get(ID_num);
                        playerBox.setText(NBA_name);
                        backButton.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_LONG).show();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                viewStatsBox.setVisibility(View.GONE);
                playerBox.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.GONE);
                viewStats.setVisibility(View.VISIBLE);

            }
        });
        viewStats.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //ID_num = Integer.parseInt(ID_input.getText().toString());
                if (ID_num > 0){
                    viewStatsBox.setVisibility(View.VISIBLE);
                    playerBox.setVisibility(View.GONE);
                    backButton.setVisibility(View.VISIBLE);
                    viewStats.setVisibility(View.GONE);
                    textEdit(ID_num);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_LONG).show();
                }

            }

            public void textEdit(int ID){
                if (0 < ID && ID <= 16){
                    firstName();
                    String statsBox = firstNames.get(ID-1) + "/n" + pos_List.get(ID-1) + "/n" + PER_List.get(ID-1) + "/n" + winShare_List.get(ID-1) + "/n" + TS_List.get(ID-1) + "/n" + TOV_List.get(ID-1) + "/n" + win_perc_List.get(ID-1) + "/n" + APM_List.get(ID-1) + "/n" + PPG_List.get(ID-1) + "/n" + RPG_List.get(ID-1);
                    viewStatsBox.setText(statsBox);

                }
            }

            public void firstName(){
                for (int i = 0; i< playerList.size();i++) {
                    int j = 0;
                    String firstName = "";
                    while (playerList.get(i).charAt(j) != ' ') {
                        firstName = playerList.get(i).substring(0,j);
                        j++;
                    }
                    firstNames.add(firstName);
                }
            }
        });


    }

    // method that retrieves the data from a .json file and separates every value in the key to a different arraylist
    public void get_json() {
        String json;
        try {
            InputStream is = getAssets().open("nbaplayer.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("2")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("3")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("4")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("5")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("6")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("7")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("8")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("9")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("10")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("11")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("12")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("13")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("14")) {
                    playerList.add(obj.getString("name"));
                }
                else if (obj.getString("id").equals("15")) {
                    playerList.add(obj.getString("name"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("2")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("3")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("4")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("5")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("6")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("7")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("8")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("9")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("10")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("11")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("12")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("13")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("14")) {
                    pos_List.add(obj.getInt("position"));
                }
                else if (obj.getString("id").equals("15")) {
                    pos_List.add(obj.getInt("position"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("2")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("3")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("4")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("5")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("6")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("7")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("8")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("9")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("10")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("11")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("12")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("13")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("14")) {
                    PER_List.add(obj.getInt("PER"));
                }
                else if (obj.getString("id").equals("15")) {
                    PER_List.add(obj.getInt("PER"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("2")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("3")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("4")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("5")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("6")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("7")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("8")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("9")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("10")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("11")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("12")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("13")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("14")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
                else if (obj.getString("id").equals("15")) {
                    winShare_List.add(obj.getInt("Win shares"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("2")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("3")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("4")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("5")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("6")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("7")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("8")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("9")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("10")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("11")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("12")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("13")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("14")) {
                    TS_List.add(obj.getInt("TS%"));
                }
                else if (obj.getString("id").equals("15")) {
                    TS_List.add(obj.getInt("TS%"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("2")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("3")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("4")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("5")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("6")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("7")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("8")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("9")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("10")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("11")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("12")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("13")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("14")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
                else if (obj.getString("id").equals("15")) {
                    TOV_List.add(obj.getInt("TOV"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("2")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("3")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("4")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("5")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("6")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("7")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("8")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("9")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("10")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("11")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("12")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("13")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("14")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
                else if (obj.getString("id").equals("15")) {
                    win_perc_List.add(obj.getInt("Team win %"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("2")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("3")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("4")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("5")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("6")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("7")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("8")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("9")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("10")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("11")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("12")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("13")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("14")) {
                    APM_List.add(obj.getInt("APM"));
                }
                else if (obj.getString("id").equals("15")) {
                    APM_List.add(obj.getInt("APM"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("2")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("3")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("4")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("5")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("6")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("7")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("8")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("9")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("10")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("11")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("12")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("13")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("14")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
                else if (obj.getString("id").equals("15")) {
                    PPG_List.add(obj.getInt("PPG"));
                }
            }

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("id").equals("1")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("2")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("3")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("4")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("5")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("6")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("7")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("8")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("9")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("10")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("11")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("12")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("13")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("14")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
                else if (obj.getString("id").equals("15")) {
                    RPG_List.add(obj.getInt("RPG"));
                }
            }
            //Toast.makeText(getApplicationContext(),nameList.toString(),Toast.LENGTH_LONG).show();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}