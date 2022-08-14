package com.arvind.addorremovelayoutfromrv.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.arvind.addorremovelayoutfromrv.R;
import com.arvind.addorremovelayoutfromrv.adapter.CustomAddMoreAdapter;
import com.arvind.addorremovelayoutfromrv.databinding.ActivityMainBinding;
import com.arvind.addorremovelayoutfromrv.model.AddItemsModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    //    List<AddItemsModel> addItemsModelArrayList = new ArrayList<>();
    ArrayList<AddItemsModel> models = new ArrayList<>();
    CustomAddMoreAdapter adapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        initialize();
    }

    private void initialize() {
        initAdapter();
        mBinding.tvLabelItemAddMore.setOnClickListener(view -> {
            insertMethod("", "", "");
        });
    }

    private void initAdapter() {
        adapter = new CustomAddMoreAdapter(this, models,
                new CustomAddMoreAdapter.Onclick() {
                    @Override
                    public void onEvent(AddItemsModel model, int pos) {
                        position = pos;

                    }
                });
        mBinding.rvAddMore.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void insertMethod(String transactionType, String amount, String totalAmount) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transactionType", transactionType);
            jsonObject.put("amount", amount);
            jsonObject.put("totalAmount", totalAmount);
            AddItemsModel model = gson.fromJson(String.valueOf(jsonObject), AddItemsModel.class);
            models.add(model);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}