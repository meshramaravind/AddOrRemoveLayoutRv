package com.arvind.addorremovelayoutfromrv.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.arvind.addorremovelayoutfromrv.R;
import com.arvind.addorremovelayoutfromrv.databinding.ItemsTransactionAddMoreBinding;
import com.arvind.addorremovelayoutfromrv.model.AddItemsModel;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapterTransactionAddOrRemove extends RecyclerView.Adapter<CustomAdapterTransactionAddOrRemove.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<AddItemsModel> list;
    Onclick onclick;

    public interface Onclick {
        void onEvent(AddItemsModel model, int pos);
    }

    public CustomAdapterTransactionAddOrRemove(Context context, ArrayList<AddItemsModel> list, Onclick onclick) {
        this.context = context;
        this.list = list;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public CustomAdapterTransactionAddOrRemove.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemsTransactionAddMoreBinding itemsTransactionAddMoreBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.items_transaction_add_more, parent, false);
        return new MyViewHolder(itemsTransactionAddMoreBinding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CustomAdapterTransactionAddOrRemove.MyViewHolder holder, int position) {
        final AddItemsModel model = list.get(position);
        if (model.getTransactionType() != null) {
            holder.binding.optionsEtSelectTaxLedgerAdditionalChargesItems.setText(model.getTransactionType());
            holder.binding.etChargesInPercentItems.setText(model.getTitle());
            holder.binding.etChargesInAmountItems.setText(model.getTotalAmount());
        }

        holder.binding.iconDeleteAdditionalChargesColumn.setOnClickListener(view -> {
            list.remove(position);
            notifyDataSetChanged();
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.items_auto_complete_layout, R.id.tv_id_auto_select_items);
        holder.binding.optionsEtSelectTaxLedgerAdditionalChargesItems.setAdapter(adapter);

        if (position == 0) {
            holder.binding.iconDeleteAdditionalChargesColumn.setVisibility(View.GONE);
        } else {
            holder.binding.iconDeleteAdditionalChargesColumn.setVisibility(View.VISIBLE);
            holder.binding.tvTextTitleSelectTaxLedgerItems.setVisibility(View.GONE);
            holder.binding.tvTextContentSelectTaxLedgerItems.setVisibility(View.GONE);
            holder.binding.layoutAddItems.setOnClickListener(view -> onclick.onEvent(model, position));
        }

        holder.binding.optionsEtSelectTaxLedgerAdditionalChargesItems.setOnItemClickListener((adapterView, view, i, l) ->
                model.setTransactionType(holder.binding.optionsEtSelectTaxLedgerAdditionalChargesItems.getText().toString()));


        model.setTitle(holder.binding.etChargesInPercentItems.getText().toString());
        model.setTotalAmount(holder.binding.etChargesInPercentItems.getText().toString());
    }

    public void updateList(ArrayList<AddItemsModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemsTransactionAddMoreBinding binding;

        public MyViewHolder(@NonNull ItemsTransactionAddMoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

