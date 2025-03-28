package com.example.vinsandroidproject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list = findViewById(R.id.recycler_view_projects);
        Project[] projects = {
                new Project("Getting Started","Our very first project, the default 'Hello World' app!", R.drawable.getting_started),
                new Project("Vin Quote","Making a simple change to the layout, with my own personal motivational quote for software developers",R.drawable.quote),
                new Project("BMI Calculator","A real life working BMI calculator teaching me Variables, Methods and Conditional Logic",R.drawable.calculator),
                new Project("Inches Converter","A basic converter to convert inches to meters (my own personal calculator app made on my own",R.drawable.tape),
                new Project("The Hungry Developer","A menu app for a fictional restaurant, learning about Activities, Classes & Object, Arrays Intents and ListViews",R.drawable.hungry_developer)
        };
        ProjectsAdapter adapter = new ProjectsAdapter(projects);
        list.setAdapter(adapter);
    }
}