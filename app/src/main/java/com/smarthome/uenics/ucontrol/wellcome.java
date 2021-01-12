package com.smarthome.uenics.ucontrol;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.smarthome.uenics.ucontrol.customAdapterRoom1.*;

public class   wellcome extends AppCompatActivity implements CommunicationChannel {
    TextView enter_home_name;
    RelativeLayout set_home_name;
    ListView listView;
    PopupWindow popwindow;
    public Integer r,index=0;
    ArrayList<Integer> edit_v;
    ArrayList<String> room_name;
    customAdapterRoom1 adapter;
    //String room_name[],date[];
    private ShareClass sharedPreference;
    BluetoothAdapter mBluetoothAdapter =null;
    private static final int SETTINGS_RESULT = 1;
    // Debugging
    private static final String TAG = "Bluecon";
    private static final boolean D = true;
    private static final int REQUEST_ENABLE_BT = 0;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellcome);
        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else {

            setupChat();
        }
    }

    private	void setupChat() {
    set_home_name= (RelativeLayout) findViewById(R.id.add);
        final View layout1=(ViewGroup)findViewById(R.id.setting);
        enter_home_name= (TextView) findViewById(R.id.home_name);
        listView= (ListView) findViewById(R.id.listView);
        sharedPreference = new ShareClass(this);
        String key="No_Room";
        r = Integer.parseInt(sharedPreference.getValue(key, "0"));
        if(r!=0){
            listView.setVisibility(View.VISIBLE);
            set_home_name.setVisibility(View.GONE);
            key="Home_name";
            enter_home_name.setText(sharedPreference.getValue(key, "UEnics"));
            adap(0);
        }

        set_home_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {
                        float a=0.2f;
                        //layout1.setAlpha(a);
                        View layout;
                        LayoutInflater inflater = (LayoutInflater) wellcome.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        layout = inflater.inflate(R.layout.pop_up, (ViewGroup) findViewById(R.id.a223));
                        popwindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT, true);
                        popwindow.setAnimationStyle(R.style.Animation);
                        popwindow.showAtLocation(layout, Gravity.CENTER, 0, 90);
                        Button roomok= (Button) layout.findViewById(R.id.set_home_name);
                        final EditText smartroom= (EditText) layout.findViewById(R.id.enter_home_name);
                        smartroom.setText(enter_home_name.getText().toString());
                        roomok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popwindow.dismiss();
                                //layout1.setAlpha(1f);
                                if(smartroom.getText().toString().length()>9){
                                    enter_home_name.setText(smartroom.getText().toString().toUpperCase());
                                    String key="Home_name";
                                    sharedPreference.save(key, smartroom.getText().toString().toUpperCase());
                                }

                                //r = Integer.parseInt(smartroom.getText().toString());
                                getpopup();
                                //roomname();
                            }

                        });
                        //Toast.makeText(getApplicationContext(),"javed",Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        });
        final ImageView edit1= (ImageView) findViewById(R.id.edit1);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<edit_v.size();i++){
                    if(edit_v.get(i)==1) {
                        edit_v.set(i,0);
                        Toast.makeText(getApplicationContext(),"edit_v = 0",Toast.LENGTH_SHORT).show();
                    }else {
                        edit_v.set(i,1);
                        Toast.makeText(getApplicationContext(),"edit_v = 1",Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    public void adap(int flag){

        room_name= new ArrayList<String>();
        edit_v= new ArrayList<Integer>();
        for(int i=0;i<r;i++)
        {
            String key="Room_name_"+String.valueOf(i);
            String xz=sharedPreference.getValue(key,key);
            room_name.add(xz);
            edit_v.add(1);
        }
        adapter=new customAdapterRoom1(this, room_name,edit_v);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        if(flag==1) {
            roomname(1,0);
        }
    }


    public void getpopup(){
        try {
            final View layout;
            LayoutInflater inflater = (LayoutInflater) wellcome.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.smartroom_no, (ViewGroup) findViewById(R.id.a22));
            popwindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
            popwindow.setAnimationStyle(R.style.Animation);
            popwindow.showAtLocation(layout, Gravity.CENTER, 0, 90);
            Button roomok1 = (Button) layout.findViewById(R.id.room_ok);
            Button skip = (Button) layout.findViewById(R.id.skip);
            final EditText smartroom= (EditText) layout.findViewById(R.id.smartroom);
            final String key="No_Room";
            //r = Integer.parseInt(sharedPreference.getValue(key, "0"));
            smartroom.setText(sharedPreference.getValue(key, "No. of SMART ROOMS"));
            roomok1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popwindow.dismiss();
                    if(smartroom.getText().toString().length()>0){
                        listView.setVisibility(View.VISIBLE);
                        set_home_name.setVisibility(View.GONE);
                        sharedPreference.save(key, smartroom.getText().toString());
                        r = Integer.parseInt(smartroom.getText().toString());
                        adap(1);
                    }


                }
            });

            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popwindow.dismiss();
                }

            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void roomname(final int f,int index1){
        try {
            View layout;
            index=index1;
            LayoutInflater inflater = (LayoutInflater) wellcome.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.roomname, (ViewGroup) findViewById(R.id.room));
            popwindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT, true);
            popwindow.setAnimationStyle(R.style.Animation);
            popwindow.showAtLocation(layout, Gravity.CENTER, 0, 90);
            Button roomok= (Button) layout.findViewById(R.id.set);
            Button skip= (Button) layout.findViewById(R.id.skip);
            final TextView smartroom2= (TextView) layout.findViewById(R.id.rn);
            final EditText smartroom1= (EditText) layout.findViewById(R.id.srn);
            final EditText id= (EditText) layout.findViewById(R.id.id);
            final String key = "Room_name_" + String.valueOf(index);
            final String key1 = "Room_name_id" + String.valueOf(index);
            id.setHint(sharedPreference.getValue(key1, "98:D3:31:60:28:BD"));
            smartroom1.setHint(sharedPreference.getValue(key, "Room_name"+index));
            smartroom2.setText("Room_name"+index);
            roomok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(smartroom1.getText().toString().length()>0){
                        room_name.set(index, smartroom1.getText().toString());
                        Toast.makeText(getApplicationContext(), room_name.get(index), Toast.LENGTH_SHORT).show();
                        sharedPreference.save(key, smartroom1.getText().toString().toUpperCase());
                    }
                    if(id.getText().toString().length()>0){
                        String id1=id.getText().toString().substring(0,2);
                        id1=id1.concat(":");
                        id1=id1.concat(id.getText().toString().substring(2, 4));
                        id1=id1.concat(":");
                        id1=id1.concat(id.getText().toString().substring(4, 6));
                        id1=id1.concat(":");
                        id1=id1.concat(id.getText().toString().substring(6, 8));
                        id1=id1.concat(":");
                        id1=id1.concat(id.getText().toString().substring(8, 10));
                        id1=id1.concat(":");
                        id1=id1.concat(id.getText().toString().substring(10,12));
                        sharedPreference.save(key1,id1.toUpperCase() );
                    }
                    //adapter.setNotifyOnChange(true);
                    popwindow.dismiss();
                    //enter_home_name.setText(smartroom.getText().toString());
                    if (r > 1 && f==1) {
                        r--;

                        index++;
                        roomname(1, index);

                    }
                    adapter.notifyDataSetChanged();
                }

            });


            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    popwindow.dismiss();

                    if (r>1 && f==1){
                        r--;

                        index++;
                        roomname(1,index);

                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case SETTINGS_RESULT:

                onCreate(null);
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    setupChat();
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, "BT not enabled",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }

    }

    @Override
    public void sendMessage5(int position) {
        roomname(0,position);
    }
}
