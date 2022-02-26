package com.example.retrofitcrud;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    secondViewModel secondviewmodel;
    Button createbtn, deletebtn, updatebtn;
    EditText nameedit, emailedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        secondviewmodel = new ViewModelProvider(this).get(secondViewModel.class);
        createbtn = findViewById(R.id.createbtn);
        nameedit = findViewById(R.id.nameedit);
        emailedit = findViewById(R.id.emailedit);
        deletebtn = findViewById(R.id.deletebtn);
        updatebtn = findViewById(R.id.updatebtn);

        createuserObserable();

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User("5", nameedit.getText().toString(),emailedit.getText().toString(), "Male","Active");
                secondviewmodel.createUser(user);
                finish();
            }
        });

      String recievedid = getIntent().getStringExtra("id");

      if (recievedid != null){

          deletebtn.setVisibility(View.VISIBLE);
          createbtn.setVisibility(View.GONE);
          updatebtn.setVisibility(View.VISIBLE);

          secondviewmodel.getUser(recievedid);
          secondviewmodel.getRecievedUser().observe(this, new Observer<User>() {
             @Override
             public void onChanged(User user) {
                 if (user != null){
                     nameedit.setText(user.getName());
                     emailedit.setText(user.getEmail());

                     updatebtn.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                            User user1 = new User(recievedid, nameedit.getText().toString(),
                                    emailedit.getText().toString(),"male","Active");
                            secondviewmodel.updateUser(user1,recievedid);
                            finish();
                         }
                     });

                     deletebtn.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                            secondviewmodel.deleteUser(recievedid);
                            finish();
                         }
                     });
                 }
             }
         });
      }



    }


    private void createuserObserable(){
        secondviewmodel.getsecondobserverlist().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if (userResponse != null){
                    Toast.makeText(MainActivity2.this, "Created successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity2.this, "sorry Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}