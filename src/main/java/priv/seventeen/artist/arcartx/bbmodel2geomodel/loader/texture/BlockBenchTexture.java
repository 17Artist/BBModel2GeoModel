package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.texture;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.UUID;


import com.google.gson.annotations.SerializedName;

public class BlockBenchTexture {
    @SerializedName("uuid")
    private UUID uniqueId;
    @SerializedName("path")
    private String path;
    @SerializedName("name")
    private String name;
    @SerializedName("folder")
    private String folder;
    @SerializedName("namespace")
    private String namespace;
    @SerializedName("id")
    private int id;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("uv_width")
    private int uvWidth;
    @SerializedName("uv_height")
    private int uvHeight;
    @SerializedName("particle")
    private boolean particle;
    @SerializedName("layers_enabled")
    private boolean layersEnabled;
    @SerializedName("render_mode")
    private String renderMode;
    @SerializedName("render_sides")
    private boolean renderSides;
    @SerializedName("frame_time")
    private int frameTime;
    @SerializedName("frame_order_type")
    private String frameOrderType;
    @SerializedName("frame_interpolate")
    private boolean frameInterpolate;
    @SerializedName("visible")
    private boolean visible;
    @SerializedName("internal")
    private boolean internal;
    @SerializedName("saved")
    private boolean saved;
    @SerializedName("relative_path")
    private String relativePath;
    @SerializedName("source")
    private String source;

    public BlockBenchTexture(UUID uniqueId, String path, String name, String folder,
                             String namespace, int id, int width, int height, int uvWidth, int uvHeight,
                             boolean particle, boolean layersEnabled, String renderMode,
                             boolean renderSides, int frameTime, String frameOrderType,
                             boolean frameInterpolate, boolean visible, boolean internal,
                             boolean saved, String relativePath, String source) {
        this.uniqueId = uniqueId;
        this.path = path;
        this.name = name;
        this.folder = folder;
        this.namespace = namespace;
        this.id = id;
        this.width = width;
        this.height = height;
        this.uvWidth = uvWidth;
        this.uvHeight = uvHeight;
        this.particle = particle;
        this.layersEnabled = layersEnabled;
        this.renderMode = renderMode;
        this.renderSides = renderSides;
        this.frameTime = frameTime;
        this.frameOrderType = frameOrderType;
        this.frameInterpolate = frameInterpolate;
        this.visible = visible;
        this.internal = internal;
        this.saved = saved;
        this.relativePath = relativePath;
        this.source = source;
    }

    public UUID uniqueId() {
        return uniqueId;
    }

    public String path() {
        return path;
    }

    public String name() {
        return name;
    }

    public String folder() {
        return folder;
    }

    public String namespace() {
        return namespace;
    }

    public int id() {
        return id;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int uvWidth() {
        return uvWidth;
    }

    public int uvHeight() {
        return uvHeight;
    }

    public boolean particle() {
        return particle;
    }

    public boolean layersEnabled() {
        return layersEnabled;
    }

    public String renderMode() {
        return renderMode;
    }

    public boolean renderSides() {
        return renderSides;
    }

    public int frameTime() {
        return frameTime;
    }

    public String frameOrderType() {
        return frameOrderType;
    }

    public boolean frameInterpolate() {
        return frameInterpolate;
    }

    public boolean visible() {
        return visible;
    }

    public boolean internal() {
        return internal;
    }

    public boolean saved() {
        return saved;
    }

    public String relativePath() {
        return relativePath;
    }

    public String source() {
        return source;
    }



    public BufferedImage image() {
        if (source == null)
            return null;
        String data = source.substring(source.indexOf(",") + 1);
        byte[] imageContent = Base64.getDecoder().decode(data);
        try (ByteArrayInputStream stream = new ByteArrayInputStream(imageContent)) {
            return javax.imageio.ImageIO.read(stream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read image from source", e);
        }
    }
}
