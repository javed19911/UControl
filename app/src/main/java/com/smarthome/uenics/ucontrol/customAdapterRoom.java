package com.smarthome.uenics.ucontrol;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapterRoom extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> itemname;

    Viewholder holder;
    int position1;

    LayoutInflater inflater;

    View layout;
    public customAdapterRoom(Context context, ArrayList<String> itemname) {

        super(context, R.layout.coustom_list_room, itemname);
        this.context=context;
        this.itemname=new ArrayList<>(itemname);


    }
    public View getView(final int position,View view,ViewGroup parent) {

        if(view==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //inflater =(LayoutInflater)context.getLayoutInflater();
            view = inflater.inflate(R.layout.coustom_list_room, parent,false);
        }
            holder=new Viewholder();
            holder.enter=(ImageButton)view.findViewById(R.id.enter);
            holder.Room_name = (TextView) view.findViewById(R.id.editText1);
            holder.enter.setTag(position);


        holder.enter.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Integer a1= (Integer) v.getTag();
                                                      Intent a = new Intent(getContext(), Switch_activity.class);
                                                      a.putExtra("room_name",itemname.get(a1));
                                                      getContext().startActivity(a);

                                                  }
                                              });

        holder.Room_name.setText(itemname.get(position));
        return view;

    };
    public class Viewholder{

        TextView Room_name;
        ImageButton enter;
    }
}

