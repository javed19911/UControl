package com.smarthome.uenics.ucontrol.ui.imageDataCollection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.data.DataManager;
import com.smarthome.uenics.ucontrol.data.model.others.DefaultResponse;
import com.smarthome.uenics.ucontrol.data.model.others.mParameter;
import com.smarthome.uenics.ucontrol.ui.base.BaseViewModel;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.CameraFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.UploadFragment;
import com.smarthome.uenics.ucontrol.utils.rx.SchedulerProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VMImageDataCollection extends BaseViewModel<iImageDataCollection> {

    private FragmentManager fragmentManager;
    private boolean isActivityforResult = false;
    private double distance_from_camera =0;
    private String sensor_size ="";
    private double focal_length =0;

    public MutableLiveData<Uri> image_file = new MutableLiveData<>();
//    public MutableLiveData<mCommodity> commodity = new MutableLiveData<>();
    public MutableLiveData<String> commodity = new MutableLiveData<>();

    public LiveData<List<mParameter>> parameters;// = new LiveData<>(new ArrayList<mParameter>());

    public MutableLiveData<ArrayList<String>> commodities = new MutableLiveData<>(new ArrayList<String>());
    public MutableLiveData<String> selectedCommodity = new MutableLiveData<>();


    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        if (fragmentManager.getFragments().size() == 0) {
            //UploadImage();
            captureImage();
        }
    }

    public VMImageDataCollection(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        commodities.postValue(dataManager.getCommodities());
        parameters = dataManager.getAllParameters();
    }


    public void onCommoditySelected(android.widget.AdapterView parent,int position) {
        selectedCommodity.setValue((String) parent.getSelectedItem());
    }

    public boolean isActivityforResult() {
        return isActivityforResult;
    }

    public void setActivityforResult(boolean activityforResult) {
        isActivityforResult = activityforResult;
    }

    public double getDistance_from_camera() {
        return distance_from_camera;
    }

    public void setDistance_from_camera(double distance_from_camera) {
        this.distance_from_camera = distance_from_camera;
    }

    public String getSensor_size() {
        return sensor_size;
    }

    public void setSensor_size(String sensor_size) {
        this.sensor_size = sensor_size;
    }

    public double getFocal_length() {
        return focal_length;
    }

    public void setFocal_length(double focal_length) {
        this.focal_length = focal_length;
    }

    public void UploadImage(){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, new UploadFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //ft.addToBackStack("camera");
        ft.commit();
    }

    public void captureImage(){
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(R.id.container, new Camera2BasicFragment());
        ft.replace(R.id.container, new CameraFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //ft.addToBackStack("camera");
        ft.commit();
    }
    public void onBackBtnClicked(Context context) {
        // onResultAvailable();
        ((Activity)context).finish();
    }

    public void onCaptureImageBtnClicked(Context context) {
        // onResultAvailable();
        if(!isActivityforResult) {
            uploadImage(context);
        }else{
            Intent resultIntent = new Intent();
            resultIntent.putExtra("camera_height", getDistance_from_camera());
            resultIntent.putExtra("sensor_size", getSensor_size());
            resultIntent.putExtra("focal_length", getFocal_length());
            resultIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_file.getValue());
            ((Activity)context).setResult(Activity.RESULT_OK, resultIntent);
            ((Activity)context).finish();
        }
    }

    private String getStringImage(Context context,Uri imagefile) {
       /* FileInputStream fis = null;
        try{
            fis = new FileInputStream(Objects.requireNonNull(FileUtil.getRealPathFromURI(context, imagefile)));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        Bitmap bmp = BitmapFactory.decodeStream(fis);*/
        Bitmap bmp = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                bmp = ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.getContentResolver(), imagefile));
            }else {
                bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imagefile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // bmp = getResizedBitmap(bmp,250);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }

    private void uploadImage(Context context){
        setIsLoading(true);
        if (getNavigator() != null) {
            getNavigator().OnImageUploading();
        }
        // MultipartBody.Part is used to send also the actual filename
        MultipartBody.Part upload_image = MultipartBody.Part.createFormData("upload_image", getStringImage(context,image_file.getValue()));

        getDataManager()
                //.UploadImage(commodity.getValue().getCommodity_id(),upload_image)
                .UploadImage("0",upload_image)
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccessful()) {
                            DefaultResponse defaultResponse = response.body();
                            if (defaultResponse.getStatus().equalsIgnoreCase("success")) {
                                // commodities.postValue(directResponse.getResult().getCommodities());
                                if (getNavigator() != null) {
                                    getNavigator().showMessage("Successfully Uploaded");
                                    getNavigator().OnImagedUploaded();
                                }
                            }else{
                                if (getNavigator() != null) {
                                    getNavigator().showMessage(defaultResponse.getStatus());
                                }
                            }
                        }else{
                            if (getNavigator() != null) {
                                getNavigator().showMessage("Data Error....");
                            }
                        }
                        setIsLoading(false);
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        setIsLoading(false);
                        if (getNavigator() != null) {
                            getNavigator().handleError(t);
                        }
                    }
                });
    }











}
