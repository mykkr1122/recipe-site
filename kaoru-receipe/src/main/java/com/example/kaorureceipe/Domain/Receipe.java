package com.example.kaorureceipe.Domain;

public class Receipe {
    private Integer id;
    // 料理名
    private String title;
    // 料理の紹介
    private String introduction;
    // 材料
    private String ingredient;
    // 作り方
    private String detail;
    // コツ・ポイント
    private String point;

    private String imagePath;
    private boolean displayFlag;

    public Receipe(Integer id, String title, String introduction, String ingredient, String detail,
            String point, String imagePath,
            boolean displayFlag) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.ingredient = ingredient;
        this.detail = detail;
        this.point = point;
        this.imagePath = imagePath;
        this.displayFlag = displayFlag;
    }

    public Receipe() {
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
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
