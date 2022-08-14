package com.arvind.addorremovelayoutfromrv.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.arvind.addorremovelayoutfromrv.R;
import com.arvind.addorremovelayoutfromrv.databinding.ItemsAddMoreBinding;
import com.arvind.addorremovelayoutfromrv.model.AddItemsModel;

import java.util.ArrayList;

public class CustomAddMoreAdapter extends RecyclerView.Adapter<CustomAddMoreAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<AddItemsModel> list;
    Onclick onclick;

    public interface Onclick {
        void onEvent(AddItemsModel model, int pos);
    }

    public CustomAddMoreAdapter(Context context, ArrayList<AddItemsModel> list, Onclick onclick) {
        this.context = context;
        this.list = list;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public CustomAddMoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemsAddMoreBinding itemsAddMoreBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.items_add_more, parent, false);
        return new MyViewHolder(itemsAddMoreBinding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CustomAddMoreAdapter.MyViewHolder holder, int position) {
        final AddItemsModel model = list.get(position);
        if (model.getTransactionType() != null) {
            holder.binding.optionsEtSelectTransactionTypeItems.setText(model.getTransactionType());
            holder.binding.etAmountAddItems.setText(model.getTitle());
            holder.binding.etTotalAmountAddItems.setText(model.getTotalAmount());
        }

        holder.binding.iconDeleteAddItems.setOnClickListener(view -> {
            list.remove(position);
            notifyDataSetChanged();
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.items_add_more, R.id.tv_id_auto_select_items);
        holder.binding.optionsEtSelectTransactionTypeItems.setAdapter(adapter);

        if (position == 0) {
            holder.binding.iconDeleteAddItems.setVisibility(View.GONE);
        } else {
            holder.binding.layoutAddItems.setOnClickListener(view -> onclick.onEvent(model, position));
        }


        holder.binding.optionsEtSelectTransactionTypeItems.setOnItemClickListener((adapterView, view, i, l) ->
                model.setTransactionType(holder.binding.optionsEtSelectTransactionTypeItems.getText().toString()));

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
        private ItemsAddMoreBinding binding;

        public MyViewHolder(@NonNull ItemsAddMoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
