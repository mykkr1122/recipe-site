package com.example.kaorurecipe.Form;

/**
 * レシピ編集のためのフォーム
 */
public class RecipeEditForm {

    // レシピID
    private Integer id;
    // 料理名
    private String title;
    // 料理の紹介
    private String introduction;
    // 〜人前
    private Integer serving;
    // 材料
    private String ingredients;
    // 作り方
    private String detail;
    // コツ・ポイント
    private String point;

    private String imagePath;

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

    public Integer getServing() {
        return serving;
    }

    public void setServing(Integer serving) {
        this.serving = serving;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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
        return "RecipeEditForm [title=" + title + ", introduction=" + introduction + ", serving=" + serving
                + ", ingredients=" + ingredients + ", detail=" + detail + ", point=" + point + ", imagePath="
                + imagePath + "]";
    }

}
