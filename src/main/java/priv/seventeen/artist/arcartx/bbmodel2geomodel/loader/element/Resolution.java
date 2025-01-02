package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element;

import com.google.gson.annotations.SerializedName;

public class Resolution {

    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
