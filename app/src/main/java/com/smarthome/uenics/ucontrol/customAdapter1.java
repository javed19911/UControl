package com.smarthome.uenics.ucontrol;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class customAdapter1 extends ArrayAdapter<String> {
    private final Context context;
    private final String[] itemname,regulator_required;
    private final String[] status;
    Viewholder holder;
    PopupWindow popwindow;
    String position1;
    int[] regulator_progess;
    int[] edit_v;
    LayoutInflater inflater;
    CommunicationChannel mCommChListner = null;
    View layout;
    private double mPrevAngle = 0;
    private double mCurrAngle = 0,mPrevAngle1=0;
    public customAdapter1(Context context, String[] itemname,String[] regulator_required,int[] edit_v,int[] regulator_progess,String[] status2) {

        super(context, R.layout.coustom_list, itemname);
        this.context=context;
        this.itemname=itemname;
        //this.status=new String[itemname.length];
        this.status=status2;
        this.regulator_required=regulator_required;
        this.regulator_progess=regulator_progess;
        this.edit_v=edit_v;
        mCommChListner = (CommunicationChannel)context;
    }
    interface CommunicationChannel
    {
        public void sendMessage3(String msg);
        public void sendMessage4(String switch_name,int position);
        public void sendMessage5(int position);
    }
    public View getView(final int position,View view, final ViewGroup parent) {

        if(view==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.coustom_list, parent,false);
        }
        holder=new Viewholder();
        holder.Room_switch=(Button)view.findViewById(R.id.switch11);
        //holder.regulator=(SeekBar)view.findViewById(R.id.seekBar1);
        holder.Room_name = (TextView) view.findViewById(R.id.editText1);
        holder.regulator_status = (TextView) view.findViewById(R.id.next);
        holder.edit_switch= (ImageView) view.findViewById(R.id.edit_switch);
        holder.Room_switch.setTag("s"+position);
        holder.Room_name.setTag(("t" + position).toString());

        if(edit_v[position]==1)
        {
            holder.edit_switch.setVisibility(View.GONE);
        }else {
            holder.edit_switch.setVisibility(View.VISIBLE);
        }

        holder.edit_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCommChListner.sendMessage5(position);
            }
        });

        if(status[position].equals("1"))
        {
            holder.Room_switch.setBackgroundResource(R.drawable.pressed);
        }else {
            holder.Room_switch.setBackgroundResource(R.drawable.not_pressed);
        }

       holder.Room_switch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               position1 = (String) view.getTag();
               String switch_name = itemname[position];
               if (regulator_required[position].equals("1")) {
                    show_regulator(switch_name,position);
               } else {
                    btn(switch_name);
               }

           }

           private void btn(String switch_name) {
               if (status[position].equals("0")) {
                   //status[position] = "1";
                   sendMessage1("s"+position+"1" + regulator_progess[position] + "i");
                   //sendMessage1(position+status[position]+regulator_progess[position]+"i");
                   //holder.Room_switch.setBackgroundResource(R.drawable.pressed);
                   Toast.makeText(context, switch_name + " Switched ON", Toast.LENGTH_SHORT).show();
               } else {
                  // status[position] = "0";
                   //sendMessage1(position+status[position]+regulator_progess[position]+"i");
                   //holder.Room_switch.setBackgroundResource(R.drawable.not_pressed);
                   sendMessage1("s"+position+"0"+regulator_progess[position]+"i");
                   Toast.makeText(context, switch_name + " Switched OFF", Toast.LENGTH_SHORT).show();
               }
           }


       });
        /*holder.regulator.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String switch_name = itemname[position];
                final int progess = seekBar.getProgress();
                if (status[position].equals("1")) {
                    regulator_progess[position] = progess;
                    sendMessage1(position + status[position] + regulator_progess[position] + "i");
                    Toast.makeText(context, switch_name + " Switched ON at " + progess, Toast.LENGTH_SHORT).show();
                } else {
                    seekBar.setProgress(regulator_progess[position]);
                    Toast.makeText(context, switch_name + " is Switched OFF ", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        //status[position]="0";
        //regulator_required[position]=1;
        holder.Room_name.setText(itemname[position]);
        holder.regulator_status.setText(""+regulator_progess[position]);
        return view;

    };
    public void sendMessage1(String msg)
    {
        mCommChListner.sendMessage3(msg);
    }
    public class Viewholder{
        Button Room_switch;
        //SeekBar regulator;
        TextView Room_name,regulator_status;
        ImageView edit_switch;
    }
    private void show_regulator(String switch_name,int position) {
        //Toast.makeText(context, switch_name + " Switched ON at ", Toast.LENGTH_SHORT).show();
        mCommChListner.sendMessage4(switch_name,position);
    }
}

