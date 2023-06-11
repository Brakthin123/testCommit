package com.example.myprojectstudy.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myprojectstudy.R;
import com.example.myprojectstudy.databinding.ActivityMainBinding;
import com.example.myprojectstudy.ui.fragment.ChatFragment;
import com.example.myprojectstudy.ui.fragment.HomeFragment;
import com.example.myprojectstudy.ui.fragment.MoreFragment;
import com.example.myprojectstudy.ui.fragment.ProfileFragment;
import com.example.myprojectstudy.ui.fragment.ProvincesFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Show HomeFragment
        showFragment(new HomeFragment());

        // Setup Listeners
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itm_home) {
                showFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.itm_couses) {
                showFragment(new ProvincesFragment());
            } else if (item.getItemId() == R.id.itm_chat){
                showFragment(new ChatFragment());
            } else if (item.getItemId() == R.id.itm_account) {
                showFragment(new ProfileFragment());
            } else {
                showFragment(new MoreFragment());
            }
            return true;
        });



    }

    // Class Fragment
    private void showFragment(Fragment fragment){
        // FragmentManger
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Replace homeFragment
        fragmentTransaction.replace(R.id.lytFragment,fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }

}