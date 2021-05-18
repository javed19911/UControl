package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "parameter")
public class mParameter {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("reference_value")
    private ArrayList<String> referenceValue = null;

    @SerializedName("data_type")
    private String dataType;

    @SerializedName("input_required")
    private Boolean inputRequired;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(ArrayList<String> referenceValue) {
        this.referenceValue = referenceValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getInputRequired() {
        return inputRequired;
    }

    public void setInputRequired(Boolean inputRequired) {
        this.inputRequired = inputRequired;
    }

}