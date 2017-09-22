package com.tecnologiasmoviles.iua.myapplication;

import java.util.List;

/**
 * Created by fcabanilla on 22/09/2017.
 */

public class Item {
    private int id;
    private String title;
    private String subtitle;
    private String main_image;
    private List<String> listItem;

    public Item(int id, String title, String subtitle, String main_image, List<String> listItem) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.main_image = main_image;
        this.listItem = listItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public List<String> getListItem() {
        return listItem;
    }

    public void setListItem(List<String> listItem) {
        this.listItem = listItem;
    }
}
