package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.keyframe;

import com.google.gson.annotations.SerializedName;

public class DataPoint {
    @SerializedName("x")
    private String x;
    @SerializedName("y")
    private String y;
    @SerializedName("z")
    private String z;

    public DataPoint(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String x() {
        return x;
    }

    public String y() {
        return y;
    }

    public String z() {
        return z;
    }

    public float xAsFloat() {
        if (x == null)
            return 0;
        return Float.parseFloat(x);
    }

    public float yAsFloat() {
        if (y == null)
            return 0;
        return Float.parseFloat(y);
    }

    public float zAsFloat() {
        if (z == null)
            return 0;
        return Float.parseFloat(z);
    }
}
