package com.example.but05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlusInfo extends AppCompatActivity {
    private TextView nomRessource2;
    private TextView cours;
    private TextView td;
    private TextView tp;
    private Intent plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_info);
        nomRessource2=findViewById(R.id.nomRessource2);
        cours=findViewById(R.id.cours);
        td=findViewById(R.id.td);
        tp=findViewById(R.id.tp);
        plus=getIntent();
        nomRessource2.setText(plus.getStringExtra("nom"));
        cours.setText(plus.getStringExtra("cours")+"h de Cours");
        td.setText(plus.getStringExtra("td")+"h de Travaux Diriges");
        tp.setText(plus.getStringExtra("tp")+"h de Travaux Pratiques");

    }
    public void returnHome(View view) {
        Intent myIntent = new Intent(PlusInfo.this, MainActivity.class);
        PlusInfo.this.startActivity(myIntent);
    }
}