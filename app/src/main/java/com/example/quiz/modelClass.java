package com.example.quiz;

public class modelClass {

    private String ImageName;
    private String CategoryName;

    public modelClass(String imageName, String categoryName) {
        this.ImageName = imageName;
        this.CategoryName = categoryName;
    }

    public String getImageName() {
        return ImageName;
    }

    public String getCategoryName() {
        return CategoryName;
    }
}
