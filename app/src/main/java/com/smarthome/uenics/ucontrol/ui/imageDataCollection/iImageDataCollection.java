package com.smarthome.uenics.ucontrol.ui.imageDataCollection;

public interface iImageDataCollection {

    void OnImageUploading();

    void OnImagedUploaded();

    void handleError(Throwable throwable);

    void showMessage(String message);
}
