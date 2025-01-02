package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.uv;

import com.google.gson.annotations.SerializedName;

public class BlockBenchUv {
    @SerializedName("uv")
    private float[] uv;
    @SerializedName("texture")
    private String rawTexture;

    public BlockBenchUv(float[] uv, String rawTexture) {
        this.uv = uv;
        this.rawTexture = rawTexture;
    }

    public float[] uv() {
        return uv;
    }

    public String rawTexture() {
        return rawTexture;
    }

    public int texture() {
        if (rawTexture == null)
            return -1;
        return Integer.parseInt(rawTexture);
    }

    public boolean hasTexture() {
        return rawTexture != null;
    }
}
