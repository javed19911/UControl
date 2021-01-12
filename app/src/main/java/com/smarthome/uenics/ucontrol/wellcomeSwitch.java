package com.smarthome.uenics.ucontrol;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class wellcomeSwitch extends AppCompatActivity implements customAdapter1.CommunicationChannel {
    Intent roomname;
    RelativeLayout set_home_name;
    TextView home_name;
    PopupWindow popwindow;
    ListView listView;
    Integer r,index=0;
    int start=0,start_flag;
    private ShareClass sharedPreference;
    String[] switch_name,regulator_required;
    View layout1;
    customAdapter1 adapter;
    private static final String TAG = "Bluecon";
    private static final boolean D = true;
    BluetoothAdapter mBluetoothAdapter =null;
    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    int status1=1;
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // String buffer for outgoing messages
    private StringBuffer mOutStringBuffer;
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    // Member object for the chat services
    public BluetoothChatService mChatService = null;
    //progess dialog
    private ProgressDialog mProgress;
    private double mPrevAngle = 0;
    private double mCurrAngle = 0,mPrevAngle1=0;
    String room_id,status2[],rec_msg="";
    int[] edit_v,regulator_progess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellcome);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else {

            setupChat();
        }

    }
    private	void setupChat()
    {
        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService = new BluetoothChatService(this, mHandler);
        // Initialize the buffer for outgoing messages
        mOutStringBuffer = new StringBuffer("");

        sharedPreference = new ShareClass(this);
        //String address = "00:2D:42:76:A8:03";
        String address = "98:D3:31:60:28:BE";
        //String address = "98:D3:31:60:2C:AF";
        roomname=getIntent();

        room_id=roomname.getStringExtra("id");

        String key="Room_name_id"+room_id;
        final String  Id = sharedPreference.getValue(key, address);
        connectDevice(Id);

        set_home_name= (RelativeLayout) findViewById(R.id.add);
        layout1=(ViewGroup)findViewById(R.id.setting);
        home_name= (TextView) findViewById(R.id.home_name);
        home_name.setText(roomname.getStringExtra("room_name"));
        listView= (ListView) findViewById(R.id.listView);

        key="No_Switch_"+room_id;
        r = Integer.parseInt(sharedPreference.getValue(key, "0"));
        if(r!=0){
            listView.setVisibility(View.VISIBLE);
            set_home_name.setVisibility(View.GONE);
            //key="Home_name";
            //enter_home_name.setText(sharedPreference.getValue(key, "UEnics"));
            adap(0);
        }

        set_home_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    float a = 0.2f;
                    //layout1.setAlpha(a);
                    final View layout;
                    LayoutInflater inflater = (LayoutInflater) wellcomeSwitch.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    layout = inflater.inflate(R.layout.smartroom_no, (ViewGroup) findViewById(R.id.a22));
                    popwindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                    popwindow.setAnimationStyle(R.style.Animation);
                    popwindow.showAtLocation(layout, Gravity.CENTER, 0, 90);
                    Button roomok1 = (Button) layout.findViewById(R.id.room_ok);
                    Button skip = (Button) layout.findViewById(R.id.skip);
                    TextView title = (TextView) layout.findViewById(R.id.ti);
                    final EditText smartroom = (EditText) layout.findViewById(R.id.smartroom);
                    title.setText("Number of SMART SWITCH");
                    final String key = "No_Switch_" + room_id;
                    smartroom.setHint(sharedPreference.getValue(key, "No. of SMART SWITCH"));
                    roomok1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popwindow.dismiss();
                            if(smartroom.getText().toString().length()>0) {
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
        });
        final ImageView edit1= (ImageView) findViewById(R.id.edit1);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<edit_v.length;i++){
                    if(edit_v[i]==1) {
                        edit_v[i]=0;
                        Toast.makeText(getApplicationContext(),"edit_v = 0",Toast.LENGTH_SHORT).show();
                    }else {
                        edit_v[i]=1;
                        Toast.makeText(getApplicationContext(),"edit_v = 1",Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
    public void adap(int flag){

        switch_name=new String[r];
        regulator_required=new String[r];
        status2=new String[r];
        edit_v=new int[r];
        regulator_progess=new int[r];

        for(int i=0;i<r;i++)
        {
            String key="Switch_name_"+room_id+"_"+String.valueOf(i);
            String xz=sharedPreference.getValue(key,"Switch_name_"+String.valueOf(i));
            switch_name[i]=xz;
            key="Switch_name_"+room_id+"_"+String.valueOf(i)+"regulator";
            String ab=sharedPreference.getValue(key,"0");
            regulator_required[i]=ab;
            edit_v[i]=1;
            regulator_progess[i]=8;
            status2[i]="0";
        }
        adapter=new customAdapter1(this, switch_name,regulator_required,edit_v,regulator_progess,status2);
        listView.setAdapter(adapter);
        if (flag==1) {
            roomname(1,0);
        }
    }

    public void roomname(final int f,int index1){
        try {
            View layout;
            index=index1;
            LayoutInflater inflater = (LayoutInflater) wellcomeSwitch.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.switchname, (ViewGroup) findViewById(R.id.room));
            popwindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT, true);
            popwindow.setAnimationStyle(R.style.Animation);
            popwindow.showAtLocation(layout, Gravity.CENTER, 0,0);
            Button roomok= (Button) layout.findViewById(R.id.set);
            Button skip= (Button) layout.findViewById(R.id.skip);
            final TextView smartroom= (TextView) layout.findViewById(R.id.rn);
            final EditText smartroom1= (EditText) layout.findViewById(R.id.srn);
            final String key1 = "Switch_name_" + room_id + "_" + String.valueOf(index);
            smartroom1.setHint(sharedPreference.getValue(key1, "Switch_name"+index));
            smartroom.setText("Switch_name " + index);
            RadioButton yes=(RadioButton)layout.findViewById(R.id.yes1);
            RadioButton no=(RadioButton)layout.findViewById(R.id.no1);
            if(regulator_required[index].equals("1")){
                yes.setChecked(true);
                no.setChecked(false);
            }
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key="Switch_name_"+room_id+"_"+String.valueOf(index)+"regulator";
                    sharedPreference.save(key,"1");
                    regulator_required[index]="1";
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key="Switch_name_"+room_id+"_"+String.valueOf(index)+"regulator";
                    sharedPreference.save(key,"0");
                    regulator_required[index]="0";
                }
            });
            roomok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(smartroom1.getText().toString().length()>0){
                        switch_name[index] = smartroom1.getText().toString();
                        sharedPreference.save(key1, smartroom1.getText().toString().toUpperCase());
                    }
                    //sharedPreference.save(key1, smartroom1.getText().toString());
                    //key="Switch_name_"+room_id+"_"+String.valueOf(index)+"regulator";
                   // sharedPreference.save(key,"1");
                    Toast.makeText(getApplicationContext(), switch_name[index], Toast.LENGTH_SHORT).show();
                    popwindow.dismiss();

                    if (r > 1 && f == 1) {
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


  // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (D)
                        Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            //setStatus(getString(R.string.title_connected_to,
                            //mConnectedDeviceName));
                            mProgress.dismiss();
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            //setStatus(R.string.title_connecting);
                            //progress bar
                            mProgress = ProgressDialog.show(wellcomeSwitch.this, "Entering the room", "Please wait...");
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                            break;
                        case BluetoothChatService.STATE_NONE:
                            //setStatus(R.string.title_not_connected);

                            break;
                    }
                    break;
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    //mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    int position1=0;
                    if(!readMessage.equals("a")){
                        rec_msg=rec_msg.concat(readMessage);
                        if(rec_msg.length()>=5){
                            //while(rec_msg.length()>=5) {
                                position1 = rec_msg.charAt(1) - '0';

                                if (rec_msg.charAt(2) == '0') {
                                    //sw1.setBackgroundResource(R.drawable.switchoff1);
                                    status2[position1] = "0";
                                } else {
                                    //sw1.setBackgroundResource(R.drawable.switchon1);
                                    status2[position1] = "1";
                                }
                                regulator_progess[position1] = rec_msg.charAt(3) - '0';
                               // rec_msg=rec_msg.substring(5);
                            //}
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),rec_msg+" "+position1, Toast.LENGTH_SHORT)
                                    .show();
                            rec_msg="";
                            if(start_flag==1){
                                chng();
                            }
                        }

                        //update1(position);
                    }
                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getApplicationContext(),
                            "Connected to " + mConnectedDeviceName,
                            Toast.LENGTH_SHORT).show();

                    chng();
                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(),
                            msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
                            .show();
                    mProgress.dismiss();
                    //Intent activityChangeIntent = new Intent(room.this, MainActivity.class);
                    //room.this.startActivity(activityChangeIntent);
                    onBackPressed();
                    break;
            }
        }


    };
    @Override
    public void sendMessage3(String msg) {
        sendMessage(msg);
        //Toast.makeText(this,  " Switched ON "+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendMessage4(String switch_name,final int position) {
        try {
            View layout;

            LayoutInflater inflater = (LayoutInflater) wellcomeSwitch.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.regulator, (ViewGroup) findViewById(R.id.regulat));
            popwindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT, true);
            popwindow.setAnimationStyle(R.style.Animation);
            popwindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
            Button reset= (Button) layout.findViewById(R.id.reset);
            final TextView status= (TextView) layout.findViewById(R.id.status);
            final ImageButton sw1= (ImageButton) layout.findViewById(R.id.switch3);
            final TextView sw= (TextView) layout.findViewById(R.id.sw);
            final ImageView reg= (ImageView) layout.findViewById(R.id.reg);
            sw.setText(switch_name);
            if (status2[position].equals("0")) {
                sw1.setBackgroundResource(R.drawable.switchoff1);
            }
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popwindow.dismiss();
                    adapter.notifyDataSetChanged();
                }

            });
            sw1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(status2[position].equals("1")) {
                        sw1.setBackgroundResource(R.drawable.switchoff1);
                        //status2[position]="0";
                        //sendMessage(position + status2[position] + regulator_progess[position] + "i");
                        //adapter.notifyDataSetChanged();
                        sendMessage("s"+position + "0" + regulator_progess[position] + "i");
                    }
                    else{
                        sw1.setBackgroundResource(R.drawable.switchon1);
                        //status2[position]="1";
                        //sendMessage(position + status2[position] + regulator_progess[position] + "i");
                        //adapter.notifyDataSetChanged();
                        sendMessage("s"+position + "1" + regulator_progess[position] + "i");
                    }

                }

            });
            reg.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {


                        final float xc = view.getWidth() / 2;
                        final float yc = view.getHeight() / 2;

                        final float x = motionEvent.getX();
                        final float y = motionEvent.getY();
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN: {

                                mPrevAngle1 = mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                                if (-80 < mCurrAngle && mCurrAngle < 120)
                                    view.clearAnimation();
                                break;
                            }
                            case MotionEvent.ACTION_MOVE: {
                                mPrevAngle = mCurrAngle;
                                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                                if (-80 < mCurrAngle && mCurrAngle < 120) {
                                    animate(mPrevAngle, mCurrAngle, 0, view);
                                } else
                                    mCurrAngle = mPrevAngle;
                                break;
                            }
                            case MotionEvent.ACTION_UP: {
                                if (-80 < mCurrAngle && mCurrAngle < 120) {

                                    if (mCurrAngle > 0) {
                                        mCurrAngle = (mCurrAngle - mCurrAngle % 42) / 42;
                                        mCurrAngle = mCurrAngle + 3;
                                    }
                                    else{
                                        mCurrAngle = (mCurrAngle - mCurrAngle % 42) / 42;
                                        mCurrAngle = mCurrAngle + 2;
                                    }
                                    //Toast.makeText(getApplicationContext(), "" + mCurrAngle, Toast.LENGTH_SHORT).show();
                                }
                                regulator_progess[position] = Integer.parseInt(("" + mCurrAngle).substring(0, 1));
                                if(status2[position].equals("1")) {
                                    sendMessage("s"+position + status2[position] + regulator_progess[position] + "i");
                                    //Toast.makeText(getApplicationContext(), "" + regulator_progess[position], Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Plese Switch it ON", Toast.LENGTH_SHORT).show();
                                }
                                mPrevAngle1 = mCurrAngle = 0;
                                adapter.notifyDataSetChanged();
                                break;
                            }
                        }

                    return true;
                }

                private void animate(double fromDegrees, double toDegrees, long durationMillis, View view) {
                    final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setDuration(durationMillis);
                    rotate.setFillEnabled(true);
                    rotate.setFillAfter(true);
                    if (toDegrees > 0) {
                        toDegrees = (toDegrees - toDegrees % 42) / 42;
                        toDegrees = toDegrees + 3;
                    }
                    else{
                        toDegrees = (toDegrees - toDegrees % 42) / 42;
                        toDegrees = toDegrees + 2;
                    }
                    status.setText(("" + toDegrees).substring(0, 1));
                    view.startAnimation(rotate);
                    System.out.println(mCurrAngle);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage5(int position) {
        roomname(0,position);
    }


    public void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this, "not_connected", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
            //mOutEditText.setText(mOutStringBuffer);
        }
    }




    private void connectDevice(String data) {
        // Get the device MAC address
        String address = data;
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device);
    }

    @Override
    public synchronized void onPause() {
        super.onPause();
        if (D)
            Log.e(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (D)
            Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the Bluetooth chat services

    }

@Override
public void onBackPressed(){
    super.onBackPressed();
    if (mChatService != null)
        mChatService.stop();
    if (D)
        Log.e(TAG, "--- ON DESTROY ---");
}

    public void chng(){
        //chng1();

        if(start<r) {
            start_flag=1;
            sendMessage("sa" + start + "i");
            start++;
        }
        else{
            start_flag=0;
        }
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }*/


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D)
            Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    //connectDevice(data);
                }
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

}
