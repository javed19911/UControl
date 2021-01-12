package com.smarthome.uenics.ucontrol;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> itemname;

    Viewholder holder;
    int position1;

    LayoutInflater inflater;

    View layout;
    public customAdapter(Context context, ArrayList<String> itemname) {

        super(context, R.layout.coustom_list, itemname);
        this.context=context;
        this.itemname=new ArrayList<>(itemname);


    }
    public View getView(final int position,View view,ViewGroup parent) {

        if(view==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.coustom_list, parent,false);
        }
            holder=new Viewholder();
            holder.Room_switch=(Button)view.findViewById(R.id.switch11);
            holder.regulator=(SeekBar)view.findViewById(R.id.seekBar1);
            holder.Room_name = (TextView) view.findViewById(R.id.editText1);
            holder.Room_switch.setTag(position);
            holder.regulator.setTag(position);

        holder.Room_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                position1=(Integer)v.getTag();


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("Are you sure want to delete?");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }




        });
       /* holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position1=(Integer)v.getTag();
                sharepreference=new Shareclass();
                String  index = sharepreference.getValue(context, "index", "0");
                int a=Integer.parseInt(index);
                position1=a-1-position1;
                String nextkey = "complain_no" + String.valueOf(position1);
                String getkey = sharepreference.getValue(context, nextkey, "Deleted");
                Intent in=new Intent(context,complain_details.class);
                in.putExtra("complain_id",getkey);
                v.getContext().startActivity(in);

            }
        });*/
        holder.Room_name.setText(itemname.get(position));
        return view;

    };
    public class Viewholder{
        Button Room_switch;
        SeekBar regulator;
        TextView Room_name;
    }
}

