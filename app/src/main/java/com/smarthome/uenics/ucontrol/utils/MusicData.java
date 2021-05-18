package com.smarthome.uenics.ucontrol.utils;


/**
 * Created by nishant on 8/03/2020.
 */

public class MusicData {
    private String soundFilePath;
    private boolean ripe;
    private double groundtruthData;

    public MusicData(String filePath, boolean ripeorunripe, double truthValue) {
        soundFilePath = filePath;
        ripe = ripeorunripe;
        groundtruthData = truthValue;
    }

    public void setSoundFilePath(String soundFilePath) {
        this.soundFilePath = soundFilePath;
    }

    public String getSoundFilePath() {
        return  this.soundFilePath;
    }

    public boolean isRipe() {
        return ripe;
    }
    public void setRipe(boolean ripe) {
        this.ripe = ripe;
    }

    public double getGroundtruthData() {
        return this.groundtruthData;
    }

    public void setGroundtruthData(double groundtruth) {
        this.groundtruthData = groundtruth;
    }

 }

