package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.animators;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.keyframe.BlockBenchKeyFrame;

public class BlockBenchAnimator {
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("keyframes")
    private List<BlockBenchKeyFrame> keyFrames;

    public BlockBenchAnimator(String name, String type, List<BlockBenchKeyFrame> keyFrames) {
        this.name = name;
        this.type = type;
        this.keyFrames = keyFrames;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public List<BlockBenchKeyFrame> keyFrames() {
        return keyFrames;
    }
}
