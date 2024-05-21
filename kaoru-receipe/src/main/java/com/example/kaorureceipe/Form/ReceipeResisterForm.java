package com.example.kaorureceipe.Form;

/**
 * レシピ登録のためのフォーム
 */
public class ReceipeResisterForm {
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

    @Override
    public String toString() {
        return "ReceipeResisterForm [title=" + title + ", introduction=" + introduction + ", ingredient=" + ingredient
                + ", detail=" + detail + ", point=" + point + ", imagePath=" + imagePath + "]";
    }

}
