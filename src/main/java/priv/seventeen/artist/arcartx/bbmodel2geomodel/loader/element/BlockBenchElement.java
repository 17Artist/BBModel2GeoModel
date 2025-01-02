package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element;

import java.util.Map;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.face.BlockBenchElementFace;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.uv.BlockBenchUv;

public class BlockBenchElement {
    @SerializedName("uuid")
    private UUID uniqueId;
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("boxUv")
    private boolean boxUv;
    @SerializedName("rescale")
    private boolean rescale;
    @SerializedName("locked")
    private boolean locked;
    @SerializedName("render_order")
    private String renderOrder;
    @SerializedName("allow_mirror_modeling")
    private boolean allowMirrorModeling;
    @SerializedName("autouv")
    private int autoUv;
    @SerializedName("color")
    private int color;
    @SerializedName("from")
    private float[] from;
    @SerializedName("to")
    private float[] to;
    @SerializedName("origin")
    private float[] origin;
    @SerializedName("rotation")
    private float[] rotation;
    @SerializedName("uv_offset")
    private float[] uv_offset;
    @SerializedName("faces")
    private BlockBenchElementFace faces;

    public BlockBenchElement(UUID uniqueId, String type, String name, boolean boxUv,
                             boolean rescale, boolean locked, String renderOrder, boolean allowMirrorModeling,
                             int autoUv, int color, float[] from, float[] to, float[] origin, float[] rotation,
                             float[] uv_offset, BlockBenchElementFace faces) {
        this.uniqueId = uniqueId;
        this.type = type;
        this.name = name;
        this.boxUv = boxUv;
        this.rescale = rescale;
        this.locked = locked;
        this.renderOrder = renderOrder;
        this.allowMirrorModeling = allowMirrorModeling;
        this.autoUv = autoUv;
        this.color = color;
        this.from = from;
        this.to = to;
        this.origin = origin;
        this.rotation = rotation;
        this.uv_offset = uv_offset;
        this.faces = faces;
    }

    public UUID uniqueId() {
        return uniqueId;
    }

    public String type() {
        return type;
    }

    public String name() {
        return name;
    }

    public boolean boxUv() {
        return boxUv;
    }

    public boolean rescale() {
        return rescale;
    }

    public boolean locked() {
        return locked;
    }

    public String renderOrder() {
        return renderOrder;
    }

    public boolean allowMirrorModeling() {
        return allowMirrorModeling;
    }

    public int autoUv() {
        return autoUv;
    }

    public int color() {
        return color;
    }

    public float[] from() {
        return from;
    }

    public float[] to() {
        return to;
    }

    public float[] origin() {
        return origin;
    }

    public float[] rotation() {
        return rotation;
    }

    public float[] uv_offset() {
        return uv_offset;
    }

    public BlockBenchElementFace faces() {
        return faces;
    }

    public Map<String, BlockBenchUv> facesMap() {
        return Map.of(
                "north", faces.north(),
                "south", faces.south(),
                "east", faces.east(),
                "west", faces.west(),
                "up", faces.up(),
                "down", faces.down()
        );
    }
}
