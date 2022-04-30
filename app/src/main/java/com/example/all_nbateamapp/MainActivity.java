package com.example.all_nbateamapp;
// imports all the classes used in the project
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

//this is the main activity, the first class ran in this program, this is the first and only activity ran in the app
public class MainActivity extends AppCompatActivity {

    // all the arraylists that are initialized, the begin empty and values are added inside a method later on in the program
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

    // any variable types are initialized here, integers, strings, buttons, textview and edittext
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

        // variables are given values here, textviews are finding the id number value of the textview shown on screen
        ID_input =  (EditText) findViewById(R.id.playerID);
        addPlayer = findViewById(R.id.addPlayer);
        viewStats = findViewById(R.id.viewStats);
        backButton = findViewById(R.id.backButton);
        viewStatsBox = (TextView)findViewById(R.id.playerStats);
        playerBox = (TextView)findViewById(R.id.playerList);

        // calls another method that is later on declared that pulls information from the .json file that is imported
        get_json();
        // controls what happens when the addPlayer button is pressed, using the setOnClickListener method from an imported class
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override

            // a procedure called onClick with parameter View v that changes the visibility of the buttons and of the textviews
            public void onClick(View v) {
                try{
                    ID_num = Integer.parseInt(ID_input.getText().toString());
                } catch(Exception e ) {

                }
                // selection, determines whether ID_num is between 0 and 16, selects either the if statement or else statement that
                if (0 < ID_num && ID_num < 16){
                    /* sequencing, if these if statements were swapped, the for loop would not run as intended and would never meet the requirements of
                    the if statement */
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
                        // iteration, iterates through all the items in the array to see if the ID has already been used
                        for (int i = 0; i < NBA_Ranking.size(); i++) {
                            if (NBA_Ranking.get(i) == ID_num-1) {
                                duplicate = true;
                                // a popup display that gives a message to the user
                                Toast.makeText(getApplicationContext(), "Choose a different ID number", Toast.LENGTH_SHORT).show();
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
                    // a popup display that gives a message to the user
                    else {
                        Toast.makeText(getApplicationContext(), "There's your top ten list!", Toast.LENGTH_SHORT).show();
                    }
                }
                // a popup display that gives a message to the user
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a number between 1 and 15", Toast.LENGTH_SHORT).show();
                }
                // clears the input box when the addPlayer button is pressed
                ID_input.getText().clear();
            }
        });

        // controls what happens when the back button is pressed, using the setOnClickListener method from an imported class
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            // a procedure called onClick with parameter View v that changes the visibility of the buttons and of the textviews
            public void onClick(View v) {
                viewStatsBox.setVisibility(View.GONE);
                playerBox.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.GONE);
                viewStats.setVisibility(View.VISIBLE);

            }
        });

        // controls what happens when the viewstats button is pressed, using the setOnClickListener method from an imported class
        viewStats.setOnClickListener(new View.OnClickListener() {
            /* procedures name is textEdit and the parameter is int ID and there is no return type. The int ID comes from the edittext box where
            the user inputs a number between 1 and 15, inclusive */
            public void textEdit(int ID){
                String statsBox = firstNames.get(ID-1) + "\n" + pos_List.get(ID-1) + "\n" + PER_List.get(ID-1) + "\n" + winShare_List.get(ID-1) + "\n" + TS_List.get(ID-1) + "\n" + TOV_List.get(ID-1) + "\n" + win_perc_List.get(ID-1) + "\n" + APM_List.get(ID-1) + "\n" + PPG_List.get(ID-1) + "\n" + RPG_List.get(ID-1);
                viewStatsBox.setText(statsBox);
            }
            @Override

            // a procedure called onClick with parameter View v that changes the visibility of the buttons and of the textviews
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
                    // call the procedure textEdit
                    textEdit(ID_num);
                }
                // a popup display that gives a message to the user
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                // clears the input box when the addPlayer button is pressed
                ID_input.getText().clear();

            }
        });


    }

    // https://youtu.be/h71Ia9iFWfI used code used from this youtube video
    // method that retrieves the data from a .json file and separates every value in the key to a different arraylist
    public void get_json() {
        String json;
        // tries to pull the information from the .json file, if it fails it will print the line where it fails
        try {
            // opens the .json file to record the information inside and closes it afterwards
            InputStream is = getAssets().open("nbaplayer.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            // runs a for loop that iterates through the .json adding the different value pairs to different arraylists
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}