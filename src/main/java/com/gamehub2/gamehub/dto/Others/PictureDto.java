package com.gamehub2.gamehub.dto.Others;

import com.gamehub2.gamehub.entities.Others.Picture;

import java.util.Base64;

public class PictureDto {
    private Long id;
    private byte[] imageData;
    private String imageFormat;
    private String imageName;
    private Picture.PictureType type;
    private String base64ImageData;

    public PictureDto() {
    }

    public PictureDto(Long id, byte[] imageData, String imageFormat, String imageName) {
        this.id = id;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
        this.imageName = imageName;
        this.base64ImageData = Base64.getEncoder().encodeToString(imageData);
    }

    public PictureDto(Long id, byte[] imageData, String imageFormat, Picture.PictureType type) {
        this.id = id;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
        this.type = type;
        this.base64ImageData = Base64.getEncoder().encodeToString(imageData);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
        this.base64ImageData = Base64.getEncoder().encodeToString(imageData); // Update base64 data when image data changes
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Picture.PictureType getType() {
        return type;
    }

    public void setType(Picture.PictureType type) {
        this.type = type;
    }

    public String getBase64ImageData() {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
