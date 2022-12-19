package com.example.insyte;

import android.view.View;
import android.widget.TextView;

public class ProgramViewHolder {

    TextView meditName;

    ProgramViewHolder (View v){
        meditName = v.findViewById(R.id.meditName);
    }
}
