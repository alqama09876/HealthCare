package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthcare.PojoClasses.InformationHealth;
import com.example.healthcare.PojoClasses.Tips_Model;

import java.util.ArrayList;

public class HealthInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tips);
        ArrayList<Tips_Model> items = new ArrayList<>();
        items.add(new Tips_Model("Hand Hygiene", "Wash your hands frequently to prevent the spread of germs."));
        items.add(new Tips_Model("Stay Hydrated", "Drink enough water daily to maintain good health."));
        items.add(new Tips_Model("Healthy Diet", "Consume a balanced diet rich in fruits and vegetables."));
        items.add(new Tips_Model("Regular Exercise", "Engage in physical activity for at least 30 minutes a day."));
        items.add(new Tips_Model("Adequate Sleep", "Get 7-9 hours of sleep for optimal well-being."));
        items.add(new Tips_Model("Sun Protection", "Use sunscreen to protect your skin from harmful UV rays."));
        items.add(new Tips_Model("Stress Management", "Practice stress-reduction techniques like meditation."));
        items.add(new Tips_Model("Avoid Smoking", "Quit smoking to reduce the risk of respiratory and heart diseases."));
        items.add(new Tips_Model("Limit Alcohol", "Consume alcohol in moderation to protect your liver and overall health."));

        // first aid advice
        ArrayList<InformationHealth> firstAidItems = new ArrayList<>();
        firstAidItems.add(new InformationHealth("Cuts and Scrapes", "Clean the wound with soap and water, apply an antiseptic, and cover with a sterile bandage."));
        firstAidItems.add(new InformationHealth("Burns", "Cool the burn with cold running water for at least 10 minutes, cover with a sterile dressing, and seek medical help for severe burns."));
        firstAidItems.add(new InformationHealth("Sprains and Strains", "Rest the injured area, apply ice wrapped in a cloth, compress with a bandage, and keep the area elevated."));
        firstAidItems.add(new InformationHealth("Choking", "Perform Heimlich maneuver by standing behind the person, placing your arms around their waist, and applying upward pressure under the ribcage."));
        firstAidItems.add(new InformationHealth("Fractures", "Immobilize the injured area, apply ice if there is swelling, and seek medical assistance."));
        firstAidItems.add(new InformationHealth("Nosebleeds", "Pinch the nostrils together, lean forward, and breathe through your mouth. Apply ice or a cold cloth to the nose."));
        firstAidItems.add(new InformationHealth("Heat Exhaustion", "Move to a cool place, drink water, apply cool compresses, and rest. Seek medical help if symptoms worsen."));
        firstAidItems.add(new InformationHealth("Poisoning", "Call emergency services, provide information about the substance ingested, and do not induce vomiting unless advised by professionals."));
        firstAidItems.add(new InformationHealth("Heart Attack", "Call emergency services, keep the person calm, encourage them to sit and rest, and administer aspirin if available."));



        ListView listViewHealthTips = findViewById(R.id.listViewHealthTips);
        ListView listViewFirstAid = findViewById(R.id.listViewFirstAid);
        Button buttonHealthTips = findViewById(R.id.buttonHealthTips);
        Button buttonFirstAid = findViewById(R.id.buttonFirstAid);

        ArrayAdapter<Tips_Model> healthTipsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ArrayAdapter<InformationHealth> firstAidAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, firstAidItems);
        listViewHealthTips.setVisibility(View.VISIBLE);
        listViewFirstAid.setVisibility(View.GONE);
        ArrayAdapter<Tips_Model> adapter = new ArrayAdapter<Tips_Model>(HealthInformation.this, android.R.layout.simple_list_item_2, android.R.id.text1, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Tips_Model item = getItem(position);
                TextView titleView = view.findViewById(android.R.id.text1);
                TextView subtitleView = view.findViewById(android.R.id.text2);
                titleView.setTextColor(getResources().getColor(R.color.black));
                subtitleView.setTextColor(getResources().getColor(R.color.black));
                titleView.setText(item.getTitle());
                subtitleView.setText(item.getSubtitle());
                return view;
            }
        };
        ArrayAdapter<InformationHealth> informationHealthArrayAdapter = new ArrayAdapter<InformationHealth>(HealthInformation.this, android.R.layout.simple_list_item_2, android.R.id.text1, firstAidItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                InformationHealth item = getItem(position);
                TextView titleView = view.findViewById(android.R.id.text1);
                TextView subtitleView = view.findViewById(android.R.id.text2);
                titleView.setTextColor(getResources().getColor(R.color.black));
                subtitleView.setTextColor(getResources().getColor(R.color.black));
                titleView.setText(item.getTitle());
                subtitleView.setText(item.getDescription());
                return view;
            }
        };
        listViewHealthTips.setAdapter(adapter);
        listViewFirstAid.setAdapter(informationHealthArrayAdapter);
        buttonHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listViewHealthTips.setAdapter(adapter);
                listViewHealthTips.setVisibility(View.VISIBLE);
                listViewFirstAid.setVisibility(View.GONE);
            }
        });

        buttonFirstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listViewFirstAid.setAdapter(informationHealthArrayAdapter);
                listViewHealthTips.setVisibility(View.GONE);
                listViewFirstAid.setVisibility(View.VISIBLE);
            }
        });


    }
    public void backLogin(View view) {
        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
        finish();
    }
}