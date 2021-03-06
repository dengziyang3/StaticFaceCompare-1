package com.hzgc.jni;

import java.io.Serializable;
import java.util.Arrays;
@Deprecated
public class FaceAttribute implements Serializable {
    private float[] feature;    // 特征值
    private int hairColor;      // 头发颜色
    private int hairStyle;      // 头发类型
    private int gender;         // 性别
    private int hat;            // 是否带帽子
    private int tie;            // 是否系领带
    private int huzi;           // 胡子类型
    private int eyeglasses;     // 是否戴眼镜
    private int sharpness;      // 清晰度评价,清晰为0，不清晰为1
    private int frontFace;      // 是否正面

    public int getSharpness() {
        return sharpness;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }

    public float[] getFeature() {
        return feature;
    }

    public void setFeature(float[] feature) {
        this.feature = feature;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getHat() {
        return hat;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public int getHuzi() {
        return huzi;
    }

    public void setHuzi(int huzi) {
        this.huzi = huzi;
    }

    public int getEyeglasses() {
        return eyeglasses;
    }

    public void setEyeglasses(int eyeglasses) {
        this.eyeglasses = eyeglasses;
    }

    public int getFrontFace() {
        return frontFace;
    }

    public void setFrontFace(int frontFace) {
        this.frontFace = frontFace;
    }

    @Override
    public String toString() {
        return "FaceAttribute{" +
                "feature=" + Arrays.toString(feature) +
                ", hairColor=" + hairColor +
                ", hairStyle=" + hairStyle +
                ", gender=" + gender +
                ", hat=" + hat +
                ", tie=" + tie +
                ", huzi=" + huzi +
                ", eyeglasses=" + eyeglasses +
                ", sharpness=" + sharpness +
                ", frontFace=" + frontFace +
                '}';
    }
}
