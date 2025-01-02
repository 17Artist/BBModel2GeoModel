package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.metadata;

import com.google.gson.annotations.SerializedName;

public class Metadata {
    @SerializedName("format_version")
    private String formatVersion;
    @SerializedName("model_format")
    private String modelFormat;
    @SerializedName("boxUv")
    private boolean boxUv;

    public Metadata(String formatVersion, String modelFormat, boolean boxUv) {
        this.formatVersion = formatVersion;
        this.modelFormat = modelFormat;
        this.boxUv = boxUv;
    }

    public String formatVersion() {
        return formatVersion;
    }

    public String modelFormat() {
        return modelFormat;
    }

    public boolean boxUv() {
        return boxUv;
    }
}
