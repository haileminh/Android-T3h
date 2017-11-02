package com.hailm.recyclerview.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hailm.recyclerview.R;
import com.hailm.recyclerview.adapter.UserAdapter;
import com.hailm.recyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvUsers;
    private UserAdapter userAdapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        createUsers();
    }

    private void initializeComponents() {
        users = new ArrayList<>();
        rcvUsers = (RecyclerView) findViewById(R.id.rcv_users);

        // setup recyclerView

        // Nàm ngang
        //LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //Nằm dọc
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcvUsers.setLayoutManager(llm);

        userAdapter = new UserAdapter(this, users);
        rcvUsers.setAdapter(userAdapter);



    }

    private void createUsers() {
        for (int i = 1; i < 50; i++) {
            User user = new User();
            user.setName("User " + i);
            user.setEmail("Email " + i + "@gmail.com");
            user.setAvatarUrl("http://abc.com");
            users.add(user);
        }
    }
}
