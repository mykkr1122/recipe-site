package com.example.kaorureceipe.Domain;

public class Receipe {
    private Integer id;
    private String title;
    private String detail;
    private String ingredient;
    private String imagePath;
    private boolean displayFlag;

    public Receipe(Integer id, String title, String detail, String ingredient, String imagePath, boolean displayFlag) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.ingredient = ingredient;
        this.imagePath = imagePath;
        this.displayFlag = displayFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    @Override
    public String toString() {
        return "Receipe [id=" + id + ", title=" + title + ", detail=" + detail + ", ingredient=" + ingredient
                + ", imagePath=" + imagePath + ", displayFlag=" + displayFlag + "]";
    }

}
