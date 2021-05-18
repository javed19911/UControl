package com.smarthome.uenics.ucontrol.ui.imageDataCollection;

import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.smarthome.uenics.ucontrol.BR;
import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.ActivityImageDataCollectionBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseActivity;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.CameraFragment;

import java.util.List;

import javax.inject.Inject;


public class ImageDataCollectionActivity extends BaseActivity<ActivityImageDataCollectionBinding, VMImageDataCollection> implements iImageDataCollection  {



    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FragmentManager fragmentManager;

    VMImageDataCollection viewModel;


    @Override
    public int getBindingVariable() {
        return BR.vmMain;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_data_collection;
    }

    @Override
    public VMImageDataCollection getViewModel() {
        viewModel = new ViewModelProvider(this,factory).get(VMImageDataCollection.class);
        viewModel.commodity.setValue((String) getIntent().getSerializableExtra("commodity"));
        viewModel.setActivityforResult(getCallingActivity() != null);
        viewModel.setFragmentManager(fragmentManager);
        return viewModel;
    }

    @Override
    public void onSetup() {
        super.onSetup();
        viewModel.setNavigator(this);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }



   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_RECORD_AUDIO && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            viewModel.startAudioRecording(this);
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
            //Do something
            List<Fragment> fragments = fragmentManager.getFragments();
            for(Fragment f : fragments){
                if( f instanceof ResultFragment) {
                    viewModel.onTestAgain();
                }else if(f instanceof FRecord) {
                    viewModel.startAudioRecording(this);
                }else if(f instanceof CameraFragment) {
                    viewModel.onCaptureImageBtnClicked(this);
                }else{
                    viewModel.stopAudioRecording(this);
                }
            }
            //Toast.makeText(this,"Pause clicked",Toast.LENGTH_LONG).show();
            return true;
        }*/
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void OnImageUploading() {
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment f : fragments){
            if(f instanceof CameraFragment) {
               ((CameraFragment) f).OnImageUploading();
            }
        }
    }

    @Override
    public void OnImagedUploaded() {
        onBackPressed();
    }

    @Override
    public void handleError(Throwable throwable) {
        showMessage(throwable.getLocalizedMessage());
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getViewDataBinding().getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    /*@Override
    public void onBackPressed() {
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment f : fragments){
            if(f instanceof ResultFragment || f instanceof CameraFragment) {
                viewModel.onTestAgain();
            }else{
                viewModel.stopAudioRecording(this);
                fragmentManager.popBackStack();
                super.onBackPressed();
            }
        }
    }*/

}
