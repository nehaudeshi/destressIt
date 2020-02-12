package com.example.destressit;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TherapistsViewHolder extends RecyclerView.ViewHolder {

    public TextView nameText;
    public TextView emailText;
    public TextView phoneText;
    public Button requestApp;

    public TherapistsViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText=itemView.findViewById(R.id.tname);
        emailText=itemView.findViewById(R.id.temail);
        phoneText=itemView.findViewById(R.id.tphone);
        requestApp=itemView.findViewById(R.id.requestApp);

    }
}
