package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Record_Adpater extends RecyclerView.Adapter<Record_Adpater.myviwholder> {

    private ArrayList<record_model> arrayList;

    public Record_Adpater(ArrayList<record_model> arrayList) {
        this.arrayList = arrayList;
    }


    public class myviwholder extends RecyclerView.ViewHolder{


        TextView date,bill,name,number,method;
        public myviwholder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.Rdate);
            bill=itemView.findViewById(R.id.Rbill);
            name=itemView.findViewById(R.id.Rname);
            number=itemView.findViewById(R.id.Rnumber);
            method=itemView.findViewById(R.id.Rpayment);
        }
    }

    @NonNull
    @Override
    public Record_Adpater.myviwholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        return new myviwholder((view));}

    @Override
    public void onBindViewHolder(@NonNull Record_Adpater.myviwholder holder, int position) {
        holder.date.setText("Date : "+arrayList.get(position).getDate());
        holder.bill.setText("Bill : "+arrayList.get(position).getBill()+" RS");
        holder.name.setText("Name : "+arrayList.get(position).getName());
        holder.number.setText("Number : "+arrayList.get(position).getNumber());
        holder.method.setText("Payment Method : "+arrayList.get(position).getPayment_method());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
