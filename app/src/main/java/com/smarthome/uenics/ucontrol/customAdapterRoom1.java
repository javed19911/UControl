package com.smarthome.uenics.ucontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapterRoom1 extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> itemname;
    CommunicationChannel mCommChListner = null;
    Viewholder holder;
    int position1;
    ArrayList<Integer> edit_v;

    LayoutInflater inflater;

    View layout;
    public customAdapterRoom1(Context context, ArrayList<String> itemname,ArrayList<Integer> edit_v) {

        super(context, R.layout.coustom_list_room, itemname);
        this.context=context;
        this.itemname= itemname;
        this.edit_v=edit_v;
        mCommChListner = (CommunicationChannel)context;
    }
    interface CommunicationChannel
    {

        public void sendMessage5(int position);
    }
    public View getView(final int position,View view,ViewGroup parent) {

        if(view==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //inflater =(LayoutInflater)context.getLayoutInflater();
            view = inflater.inflate(R.layout.coustom_list_room, parent,false);
        }

        holder=new Viewholder();
        holder.enter=(ImageView)view.findViewById(R.id.enter);
        holder.edit_room=(ImageView)view.findViewById(R.id.edit_switch);
        holder.Room_name = (TextView) view.findViewById(R.id.editText1);
        holder.enter.setTag(position);
        if(edit_v.get(position)==1)
        {
            holder.edit_room.setVisibility(View.GONE);
        }else {
            holder.edit_room.setVisibility(View.VISIBLE);
        }

        holder.edit_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommChListner.sendMessage5(position);
            }
        });

        holder.enter.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Integer a1= (Integer) v.getTag();
                                                      Intent a = new Intent(getContext(), wellcomeSwitch.class);
                                                      a.putExtra("room_name",itemname.get(a1));
                                                      a.putExtra("id",""+position);
                                                      getContext().startActivity(a);
                                                  }
                                              });

        holder.Room_name.setText(itemname.get(position));
        return view;

    };
    public class Viewholder{

        TextView Room_name;
        ImageView enter,edit_room;
    }
}

