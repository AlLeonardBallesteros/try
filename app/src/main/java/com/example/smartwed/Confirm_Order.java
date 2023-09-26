package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.smartwed.Adapters.confirmOrderAdapter;
import com.example.smartwed.Models.confirmOrderModel;
import com.example.smartwed.databinding.ActivityConfirmOrderBinding;

import java.util.ArrayList;

public class Confirm_Order extends AppCompatActivity {
    ActivityConfirmOrderBinding binding;
    DataBaseHelper helper;
   confirmOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfirmOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

  helper=new DataBaseHelper();

        final ArrayList<confirmOrderModel> list=helper.getconfirmOrder();
      adapter=new confirmOrderAdapter(list,this);
        binding.confirmorderrecyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.confirmorderrecyclerview.setLayoutManager(layoutManager);









    }
}