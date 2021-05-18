package com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SizeF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.ListenableFuture;
import com.smarthome.uenics.ucontrol.BR;
import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.FragmentCameraImageBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseFragment;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.VMImageDataCollection;
import com.smarthome.uenics.ucontrol.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;


import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.CAMERA_SERVICE;

;


public class CameraFragment extends BaseFragment<FragmentCameraImageBinding, VMImageDataCollection> {


    private Executor executor = Executors.newSingleThreadExecutor();

    public static final int REQUEST_CODE = 100;

    private String[] neededPermissions = new String[]{CAMERA, WRITE_EXTERNAL_STORAGE};
    private SizeF sensor_size;
    private double scale_factor = 1;
    private double focal_length = 3.2;
    private float[] focal_lengths;
    private double object_distance = 609.6;
    private double object_height = 27;



    @Inject
    ViewModelProviderFactory factory;

    FragmentCameraImageBinding fragmentCameraBinding;

    VMImageDataCollection vmMainDataCollection;


    @Override
    public int getBindingVariable() {
        return BR.vmMain;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_camera_image;
    }

    @Override
    public VMImageDataCollection getViewModel() {
        vmMainDataCollection = new ViewModelProvider(getBaseActivity(), factory).get(VMImageDataCollection.class);
        return vmMainDataCollection;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentCameraBinding = getViewDataBinding();
    }


    @Override
    public void onResume() {
        super.onResume();
        boolean result = checkPermission();
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        Log.d("mobile PPI : ",""+metrics.densityDpi);
        if (result) {
            startCameraX();
        }
    }

    private boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            ArrayList<String> permissionsNotGranted = new ArrayList<>();
            for (String permission : neededPermissions) {
                if (ContextCompat.checkSelfPermission(getBaseActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsNotGranted.add(permission);
                }
            }
            if (permissionsNotGranted.size() > 0) {
                boolean shouldShowAlert = false;
                for (String permission : permissionsNotGranted) {
                    shouldShowAlert = ActivityCompat.shouldShowRequestPermissionRationale(getBaseActivity(), permission);
                }
                if (shouldShowAlert) {
                    showPermissionAlert(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]));
                } else {
                    requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]));
                }
                return false;
            }
        }
        return true;
    }

    private void showPermissionAlert(final String[] permissions) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getBaseActivity());
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(R.string.permission_required);
        alertBuilder.setMessage(R.string.permission_message);
        alertBuilder.setPositiveButton(android.R.string.yes, (dialog, which) -> requestPermissions(permissions));
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    private void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(getBaseActivity(), permissions, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                for (int result : grantResults) {
                    if (result == PackageManager.PERMISSION_DENIED) {
                        // Not all permissions granted. Show message to the user.
                        return;
                    }
                }
                startCameraX();

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    private void startCameraX() {
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(getActivity());

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {

                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);

                } catch (ExecutionException | InterruptedException e) {
                    // No errors need to be handled for this Future.
                    // This should never be reached.
                }
            }
        }, ContextCompat.getMainExecutor(getActivity()));
    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        fragmentCameraBinding.done.setVisibility(View.GONE);
        fragmentCameraBinding.reset.setVisibility(View.GONE);
        fragmentCameraBinding.capture.setVisibility(View.VISIBLE);

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                //.setBackpressureStrategy()
                .build();


        cameraProvider.unbindAll();


        ImageCapture.Builder builder = new ImageCapture.Builder();

        //Vendor-Extensions (The CameraX extensions dependency in build.gradle)
        HdrImageCaptureExtender hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder);

        // Query if extension is available (optional).
        if (hdrImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            // Enable the extension if available.
            hdrImageCaptureExtender.enableExtension(cameraSelector);

        }

        final ImageCapture imageCapture = builder
                .setTargetRotation(getBaseActivity().getWindowManager().getDefaultDisplay().getRotation())
                .build();


        preview.setSurfaceProvider(fragmentCameraBinding.camera.getSurfaceProvider());
        androidx.camera.core.Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) getBaseActivity(),
                cameraSelector, preview, imageAnalysis, imageCapture);


        imageAnalysis.setAnalyzer(executor, new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy image) {
                //MeteringPointFactory factory = fragmentCameraBinding.camera.createMeteringPointFactory(cameraSelector);
                MeteringPointFactory factory = fragmentCameraBinding.camera.getMeteringPointFactory();

                MeteringPoint autoFocusPoint = factory.createPoint(250, 250);
                Log.d("Points", "points : " + autoFocusPoint.getSize());

                MeteringPoint autoFocusPoint1 = factory.createPoint(image.getWidth(), image.getHeight());
                Log.d("Points", "points1 : " + autoFocusPoint1.getSize());
                Log.d("Points1", "size : " + image.getWidth() + " X " + image.getHeight());
                Log.d("Points1", "preview size : " + fragmentCameraBinding.camera.getWidth() + " X " + fragmentCameraBinding.camera.getHeight());
                Log.d("Points1", "sensor size : " + sensor_size.getWidth() + " X " + sensor_size.getHeight());
                scale_factor = fragmentCameraBinding.camera.getHeight() / sensor_size.getHeight();
                //image.getImage().
                /*Camera camera1 = new Camera.open();
                camera1.getParameters().getFocalLength();*/
                getBaseActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        int circle_radius = (int) (focal_length * (object_height / object_distance) * scale_factor);// + 130;
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) fragmentCameraBinding.previewMask.getLayoutParams();
                        layoutParams.height = circle_radius;
                        layoutParams.width = circle_radius;
                        Log.d("Points1", "circle size : " + circle_radius);
                        fragmentCameraBinding.previewMask.setLayoutParams(layoutParams);
                        if (focal_lengths.length > 0) {
                            fragmentCameraBinding.focalLengths.setText(Arrays.toString(focal_lengths));
                        } else {
                            fragmentCameraBinding.focalLengths.setText(focal_length + "");
                        }
                        imageAnalysis.clearAnalyzer();

                    }
                });
                image.close();
            }
        });

        CameraManager manager =
                (CameraManager) getBaseActivity().getSystemService(CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics chars
                        = manager.getCameraCharacteristics(cameraId);
                // Do something with the characteristics
                // Does the camera have a forwards facing lens?
                Integer facing = chars.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && facing ==
                        CameraCharacteristics.LENS_FACING_BACK) {
                    //float yourMinFocus = chars.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
                    //float yourMaxFocus = chars.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE);
                    sensor_size = chars.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
                    focal_lengths = chars.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
                    if (Objects.requireNonNull(focal_lengths).length > 0) {
                        focal_length = Objects.requireNonNull(chars.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS))[0];
                    }
                    //chars.get()
                    break;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        fragmentCameraBinding.capture.setOnClickListener(v -> {
            if (vmMainDataCollection.isActivityforResult()) {
                vmMainDataCollection.image_file.setValue(getBaseActivity().getIntent().getParcelableExtra(MediaStore.EXTRA_OUTPUT));
            } else {
                vmMainDataCollection.image_file.setValue(FileUtil.getOutputMediaFile(getBaseActivity(), FileUtil.Type.IMAGE));
            }
            fragmentCameraBinding.capture.setVisibility(View.GONE);
            File file = new File(Objects.requireNonNull(FileUtil.getRealPathFromURI(getBaseActivity(),
                    vmMainDataCollection.image_file.getValue())));

            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions
                    .Builder(file).build();

            imageCapture.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback() {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                    getBaseActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            cameraProvider.unbindAll();
                            fragmentCameraBinding.done.setVisibility(View.VISIBLE);
                            fragmentCameraBinding.reset.setVisibility(View.VISIBLE);

                        }
                    });

                }

                @Override
                public void onError(@NonNull ImageCaptureException error) {
                    error.printStackTrace();
                    getBaseActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            fragmentCameraBinding.capture.setVisibility(View.VISIBLE);
                        }
                    });
                }
            });


        });

        fragmentCameraBinding.reset.setOnClickListener(v -> {
            //resetCamera();
            startCameraX();
        });

    }

    public void OnImageUploading() {
        fragmentCameraBinding.done.setVisibility(View.GONE);
        fragmentCameraBinding.back.setVisibility(View.GONE);
        fragmentCameraBinding.reset.setVisibility(View.GONE);
        fragmentCameraBinding.capture.setVisibility(View.GONE);
    }


}
