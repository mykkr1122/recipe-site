package com.example.kaorurecipe.Domain;

import java.sql.Timestamp;

public class Like {
    // いいねID
    private Integer id;
    // ユーザーID
    private Integer userId;
    // レシピID
    private Integer resipeId;
    // いいねの論理削除
    private boolean displayFlag;
    // 作成日
    private Timestamp createdAt;
    // 更新日
    private Timestamp updatedAt;

    public Like(Integer id, Integer userId, Integer resipeId, boolean displayFlag, Timestamp createdAt,
            Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.resipeId = resipeId;
        this.displayFlag = displayFlag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Like() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResipeId() {
        return resipeId;
    }

    public void setResipeId(Integer resipeId) {
        this.resipeId = resipeId;
    }

    public boolean isDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Like [id=" + id + ", userId=" + userId + ", resipeId=" + resipeId + ", displayFlag=" + displayFlag
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
