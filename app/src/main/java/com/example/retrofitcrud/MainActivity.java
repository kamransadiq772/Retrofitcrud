package com.example.retrofitcrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.invoke.MutableCallSite;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.itemClickListerner {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    viewModel viewmodel;
    MutableLiveData<List<User>> userList ;
    Button searcbtn, createnewbtn;
    EditText searcheditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searcbtn =findViewById(R.id.searchbtn);
        createnewbtn=findViewById(R.id.createAcountbtn);
        searcheditText = findViewById(R.id.searchEditText);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this,this);
        recyclerView.setAdapter(adapter);

        viewmodel = new ViewModelProvider(this).get(viewModel.class);
        viewmodel.getobserverlist().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                if (userList != null){
                    adapter.setUserList(userList);
                }else {
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewmodel.getUserList();


        searcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searcheditText.getText().toString())){

                    viewmodel.searchUser(searcheditText.getText().toString());

                }else {
                    viewmodel.getUserList();
                }
            }
        });

        createnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("id", user.getId());
        startActivityForResult(intent,2000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2000){

            viewmodel.getUserList();

        }

        super.onActivityResult(requestCode, resultCode, data);



    }
}