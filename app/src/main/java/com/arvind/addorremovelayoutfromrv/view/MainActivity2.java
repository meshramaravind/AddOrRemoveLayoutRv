package com.arvind.addorremovelayoutfromrv.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.arvind.addorremovelayoutfromrv.R;
import com.arvind.addorremovelayoutfromrv.adapter.CustomAdapterTransactionAddOrRemove;

import com.arvind.addorremovelayoutfromrv.databinding.ActivityMain2Binding;
import com.arvind.addorremovelayoutfromrv.databinding.DialogLayoutTransactionAddMoreBinding;
import com.arvind.addorremovelayoutfromrv.model.AddItemsModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding mBinding;
    private BottomSheetDialog bottomSheetDialog;
    private DialogLayoutTransactionAddMoreBinding dialogLayoutTransactionAddMoreBinding;
    int position;
    ArrayList<AddItemsModel> addItemsModelArrayList = new ArrayList<>();
    CustomAdapterTransactionAddOrRemove adapterTransactionAddOrRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        mBinding.setLifecycleOwner(this);
        initialize();

    }

    private void initialize() {
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
            dialogLayoutTransactionAddMoreBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(this),
                    R.layout.dialog_layout_transaction_add_more,
                    findViewById(R.id.layout_add_transaction_container),
                    false);
            bottomSheetDialog.setContentView(dialogLayoutTransactionAddMoreBinding.getRoot());

            dialogLayoutTransactionAddMoreBinding.imageCancelDialogAddAdditionalCharges.setOnClickListener(view -> {
                bottomSheetDialog.dismiss();
                dialogLayoutTransactionAddMoreBinding.optionsEtSelectTransactionItems2.setText("");
                dialogLayoutTransactionAddMoreBinding.etTitleItems2.setText("");
                dialogLayoutTransactionAddMoreBinding.etAmountItems2.setText("");
            });

            dialogLayoutTransactionAddMoreBinding.buttonCancelAdditionalCharges.setOnClickListener(view -> {
                bottomSheetDialog.dismiss();
                dialogLayoutTransactionAddMoreBinding.optionsEtSelectTransactionItems2.setText("");
                dialogLayoutTransactionAddMoreBinding.etTitleItems2.setText("");
                dialogLayoutTransactionAddMoreBinding.etAmountItems2.setText("");
            });

            dialogLayoutTransactionAddMoreBinding.tvLabelItemAdd.setOnClickListener(view -> {
                getShowTransactionView();
            });

            initAdapterTransaction();

            dialogLayoutTransactionAddMoreBinding.tvLabelAddTransactionItemAddMore.setOnClickListener(view -> {

                insertMethod("", "", "");

            });
            insertMethod("", "", "");
        }
    }

    private void insertMethod(String transactionType, String title, String totalAmount) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transactionType", transactionType);
            jsonObject.put("title", title);
            jsonObject.put("totalAmount", totalAmount);
            AddItemsModel model = gson.fromJson(String.valueOf(jsonObject), AddItemsModel.class);
            addItemsModelArrayList.add(model);
            adapterTransactionAddOrRemove.updateList(addItemsModelArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initAdapterTransaction() {
        dialogLayoutTransactionAddMoreBinding.rvTaxForAddTransaction.setHasFixedSize(true);
        adapterTransactionAddOrRemove = new CustomAdapterTransactionAddOrRemove(
                this, addItemsModelArrayList, (model, pos) -> {
            position = pos;
        });

        dialogLayoutTransactionAddMoreBinding.rvTaxForAddTransaction.setAdapter(adapterTransactionAddOrRemove);
    }

    private void getShowTransactionView() {
        dialogLayoutTransactionAddMoreBinding.rvTaxForAddTransaction.setVisibility(View.VISIBLE);
        dialogLayoutTransactionAddMoreBinding.tvLabelAddTransactionItemAddMore.setVisibility(View.VISIBLE);
        dialogLayoutTransactionAddMoreBinding.viewDottedLineItems.setVisibility(View.VISIBLE);
        dialogLayoutTransactionAddMoreBinding.tvLabelItemAdd.setVisibility(View.GONE);
    }
}