package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation;

import java.util.Map;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.animators.BlockBenchAnimator;

public class BlockBenchAnimation {
    @SerializedName("uuid")
    private UUID uniqueId;
    @SerializedName("name")
    private String name;
    @SerializedName("loop")
    private String loop;
    @SerializedName("override")
    private boolean override;
    @SerializedName("length")
    private float length;
    @SerializedName("snapping")
    private int snapping;
    @SerializedName("anim_time_update")
    private String animationTimeUpdate;
    @SerializedName("blend_weight")
    private String blendWeight;
    @SerializedName("start_delay")
    private String startDelay;
    @SerializedName("loop_delay")
    private String loopDelay;
    @SerializedName("animators")
    private Map<UUID, BlockBenchAnimator> animators;

    public BlockBenchAnimation(UUID uniqueId, String name, String loop, boolean override,
                               float length, int snapping, String animationTimeUpdate, String blendWeight,
                               String startDelay, String loopDelay, Map<UUID, BlockBenchAnimator> animators) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.loop = loop;
        this.override = override;
        this.length = length;
        this.snapping = snapping;
        this.animationTimeUpdate = animationTimeUpdate;
        this.blendWeight = blendWeight;
        this.startDelay = startDelay;
        this.loopDelay = loopDelay;
        this.animators = animators;
    }

    public UUID uniqueId() {
        return uniqueId;
    }

    public String name() {
        return name;
    }

    public String loop() {
        return loop;
    }

    public boolean override() {
        return override;
    }

    public float length() {
        return length;
    }

    public int snapping() {
        return snapping;
    }

    public String animationTimeUpdate() {
        return animationTimeUpdate;
    }

    public String blendWeight() {
        return blendWeight;
    }

    public String startDelay() {
        return startDelay;
    }

    public String loopDelay() {
        return loopDelay;
    }

    public Map<UUID, BlockBenchAnimator> animators() {
        return animators;
    }
}
