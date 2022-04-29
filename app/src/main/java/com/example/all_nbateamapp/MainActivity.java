package com.example.all_nbateamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
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
    ArrayList<String> firstNames = new ArrayList<String>();
    ArrayList<String> pos_List = new ArrayList<String>();
    ArrayList<Integer> PER_List = new ArrayList<>();
    ArrayList<Integer> winShare_List = new ArrayList<>();
    ArrayList<Integer> TS_List = new ArrayList<>();
    ArrayList<Integer> TOV_List = new ArrayList<>();
    ArrayList<Integer> win_perc_List = new ArrayList<>();
    ArrayList<Integer> APM_List = new ArrayList<>();
    ArrayList<Integer> PPG_List = new ArrayList<>();
    ArrayList<Integer> RPG_List = new ArrayList<>();
    ArrayList<Integer> NBA_Ranking = new ArrayList<>();

    int ID_num;
    String NBA_name = "Player list:" + "\n";
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
        // controls what happens when the addPlayer button is pressed
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try{
                    ID_num = Integer.parseInt(ID_input.getText().toString());
                } catch(Exception e ) {

                }

                if (0 < ID_num && ID_num < 16){
                    if (NBA_Ranking.size() == 0) {
                        NBA_Ranking.add(ID_num-1);
                        NBA_name += firstNames.get(ID_num-1) + "\n";
                        playerBox.setText(NBA_name);
                        viewStatsBox.setVisibility(View.GONE);
                        playerBox.setVisibility(View.VISIBLE);
                        backButton.setVisibility(View.GONE);
                        viewStats.setVisibility(View.VISIBLE);
                    }
                    else if (NBA_Ranking.size() < 10){
                        boolean duplicate = false;
                        for (int i = 0; i < NBA_Ranking.size(); i++) {
                            if (NBA_Ranking.get(i) == ID_num-1) {
                                duplicate = true;
                                Toast.makeText(getApplicationContext(), "Choose a different ID number", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (!duplicate) {
                            NBA_Ranking.add(ID_num-1);
                            NBA_name += firstNames.get(ID_num-1) + "\n";
                            playerBox.setText(NBA_name);
                            viewStatsBox.setVisibility(View.GONE);
                            playerBox.setVisibility(View.VISIBLE);
                            backButton.setVisibility(View.GONE);
                            viewStats.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "There's your top ten list!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a number between 1 and 15", Toast.LENGTH_LONG).show();
                }
                ID_input.getText().clear();
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
            public void textEdit(int ID){
                String statsBox = firstNames.get(ID-1) + "\n" + pos_List.get(ID-1) + "\n" + PER_List.get(ID-1) + "\n" + winShare_List.get(ID-1) + "\n" + TS_List.get(ID-1) + "\n" + TOV_List.get(ID-1) + "\n" + win_perc_List.get(ID-1) + "\n" + APM_List.get(ID-1) + "\n" + PPG_List.get(ID-1) + "\n" + RPG_List.get(ID-1);
                viewStatsBox.setText(statsBox);
            }
            @Override
            public void onClick(View v) {
                try{
                    ID_num = Integer.parseInt(ID_input.getText().toString());
                } catch(Exception e ) {
                    ID_num = 0;
                }
                if (0 < ID_num && ID_num < 16){
                    viewStatsBox.setVisibility(View.VISIBLE);
                    playerBox.setVisibility(View.GONE);
                    backButton.setVisibility(View.VISIBLE);
                    viewStats.setVisibility(View.GONE);
                    textEdit(ID_num);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_LONG).show();
                }
                ID_input.getText().clear();

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

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                playerList.add(obj.getString("name"));
                String[] tempSplitName = playerList.get(i).split(" ");
                firstNames.add(tempSplitName[0]);
                pos_List.add(obj.getString("position"));
                PER_List.add(obj.getInt("PER"));
                winShare_List.add(obj.getInt("Win shares"));
                TS_List.add(obj.getInt("TS%"));
                TOV_List.add(obj.getInt("TOV"));
                win_perc_List.add(obj.getInt("Team win %"));
                APM_List.add(obj.getInt("APM"));
                PPG_List.add(obj.getInt("PPG"));
                RPG_List.add(obj.getInt("RPG"));
            }


            //Toast.makeText(getApplicationContext(),nameList.toString(),Toast.LENGTH_LONG).show();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}