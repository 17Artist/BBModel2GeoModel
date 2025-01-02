package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.outliner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.gson.annotations.SerializedName;

public class Outliner {
    @SerializedName("uuid")
    private UUID uniqueId;
    @SerializedName("name")
    private String name;
    @SerializedName("color")
    private int color;
    @SerializedName("export")
    private boolean export;
    @SerializedName("mirror_uv")
    private boolean mirrorUV;
    @Nullable
    @SerializedName("nbt")
    private String nbt;
    @SerializedName("origin")
    private Float[] origin;
    @SerializedName("rotation")
    private Float[] rotation;
    @SerializedName("visible")
    private boolean visible;
    @SerializedName("children")
    private List<Outliner> children;

    public Outliner(UUID uniqueId, String name, int color, boolean export,
                    boolean mirrorUV, @Nullable String nbt, Float[] origin, Float[] rotation,
                    boolean visible, List<Outliner> children) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.color = color;
        this.export = export;
        this.mirrorUV = mirrorUV;
        this.nbt = nbt;
        this.origin = origin;
        this.rotation = rotation;
        this.visible = visible;
        this.children = children;
    }

    public UUID uniqueId() {
        return uniqueId;
    }

    public String name() {
        return name;
    }

    public int color() {
        return color;
    }

    public boolean export() {
        return export;
    }

    public boolean mirrorUV() {
        return mirrorUV;
    }

    @Nullable
    public String nbt() {
        return nbt;
    }

    public Float[] origin() {
        return origin;
    }

    public Float[] rotation() {
        return rotation;
    }

    public boolean visible() {
        return visible;
    }

    public List<Outliner> children() {
        return children;
    }

    public boolean redirection() {
        return this.name == null;
    }

    public static Outliner redirect(UUID uniqueId) {
        return new Outliner(uniqueId, null, 0, false, false, null,
                new Float[]{0f, 0f, 0f}, new Float[]{0f, 0f, 0f},
                true, new ArrayList<>());
    }

    public static Outliner create(List<Outliner> children) {
        return new Outliner(UUID.randomUUID(), "bb_main", 0, false, false, null,
                new Float[]{0f, 0f, 0f}, new Float[]{0f, 0f, 0f},
                true, children);
    }
}
