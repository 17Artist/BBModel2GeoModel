package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.keyframe;

import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class BlockBenchKeyFrame {
    @SerializedName("uuid")
    private UUID uniqueId;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("channel")
    private String channel;
    @SerializedName("time")
    private float time;
    @SerializedName("color")
    private int color;
    @SerializedName("interpolation")
    private String interpolation;
    @SerializedName("bezier_linked")
    private boolean bezierLinked;
    @SerializedName("bezier_left_time")
    private float[] bezierLeftTime;
    @SerializedName("bezier_left_value")
    private float[] bezierLeftValue;
    @SerializedName("bezier_right_time")
    private float[] bezierRightTime;
    @SerializedName("bezier_right_value")
    private float[] bezierRightValue;
    @SerializedName("data_points")
    private List<DataPoint> dataPoints;

    public BlockBenchKeyFrame(UUID uniqueId, String name, String type, String channel,
                              float time, int color, String interpolation, boolean bezierLinked,
                              float[] bezierLeftTime, float[] bezierLeftValue,
                              float[] bezierRightTime, float[] bezierRightValue,
                              List<DataPoint> dataPoints) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.type = type;
        this.channel = channel;
        this.time = time;
        this.color = color;
        this.interpolation = interpolation;
        this.bezierLinked = bezierLinked;
        this.bezierLeftTime = bezierLeftTime;
        this.bezierLeftValue = bezierLeftValue;
        this.bezierRightTime = bezierRightTime;
        this.bezierRightValue = bezierRightValue;
        this.dataPoints = dataPoints;
    }

    public UUID uniqueId() {
        return uniqueId;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String channel() {
        return channel;
    }

    public float time() {
        return time;
    }

    public int color() {
        return color;
    }

    public String interpolation() {
        return interpolation;
    }

    public boolean bezierLinked() {
        return bezierLinked;
    }

    public float[] bezierLeftTime() {
        return bezierLeftTime;
    }

    public float[] bezierLeftValue() {
        return bezierLeftValue;
    }

    public float[] bezierRightTime() {
        return bezierRightTime;
    }

    public float[] bezierRightValue() {
        return bezierRightValue;
    }

    public List<DataPoint> dataPoints() {
        return dataPoints;
    }
}
