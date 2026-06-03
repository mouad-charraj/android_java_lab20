package com.example.lab20;

import com.google.gson.annotations.SerializedName;

public class Contact_mouad {

    @SerializedName("id")
    private int id_mouad;

    @SerializedName("name")
    private String name_mouad;

    @SerializedName("phone")
    private String phone_mouad;

    @SerializedName("source")
    private String source_mouad;

    @SerializedName("created_at")
    private String created_at_mouad;

    public Contact_mouad() {
    }

    public Contact_mouad(String name_mouad, String phone_mouad) {
        this.name_mouad = name_mouad;
        this.phone_mouad = phone_mouad;
    }

    public int getId_mouad() {
        return id_mouad;
    }

    public String getName_mouad() {
        return name_mouad;
    }

    public String getPhone_mouad() {
        return phone_mouad;
    }

    public String getSource_mouad() {
        return source_mouad;
    }

    public String getCreated_at_mouad() {
        return created_at_mouad;
    }

    public void setId_mouad(int id_mouad) {
        this.id_mouad = id_mouad;
    }

    public void setName_mouad(String name_mouad) {
        this.name_mouad = name_mouad;
    }

    public void setPhone_mouad(String phone_mouad) {
        this.phone_mouad = phone_mouad;
    }

    public void setSource_mouad(String source_mouad) {
        this.source_mouad = source_mouad;
    }

    public void setCreated_at_mouad(String created_at_mouad) {
        this.created_at_mouad = created_at_mouad;
    }
}
