package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.google.gson.annotations.SerializedName;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.Itemtransform.ItemTransform;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.animation.BlockBenchAnimation;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.BlockBenchElement;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.Resolution;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.metadata.Metadata;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.outliner.Outliner;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.texture.BlockBenchTexture;

public class BlockBenchModel {
    @SerializedName("name")
    private String name;
    @SerializedName("identifier")
    private String identifier;
    @Nullable
    @SerializedName("parent")
    private String parent;
    @Nullable
    @SerializedName("credit")
    private String credit;
    @SerializedName("model_identifier")
    private String modelIdentifier;
    @SerializedName("meta")
    private Metadata metadata;
    @SerializedName("ambientocclusion")
    private boolean ambientOcclusion;
    @SerializedName("front_gui_light")
    private boolean frontGuiLight;
    @SerializedName("visible_box")
    private float[] visibleBox;
    @SerializedName("variable_placeholders")
    private String variablePlaceholders;
    @SerializedName("variable_placeholder_buttons")
    private List<String> variablePlaceholderButtons;
    @SerializedName("resolution")
    private Resolution resolution;
    @SerializedName("elements")
    private List<BlockBenchElement> elements;
    @SerializedName("textures")
    private List<BlockBenchTexture> textures;
    @SerializedName("display")
    private Map<String, ItemTransform> display;
    @SerializedName("outliner")
    private List<Outliner> outliner;
    @SerializedName("animations")
    private List<BlockBenchAnimation> animations;

    public BlockBenchModel(String name, String identifier, @Nullable String parent,
                           @Nullable String credit, String modelIdentifier, Metadata metadata,
                           boolean ambientOcclusion, boolean frontGuiLight, float[] visibleBox,
                           String variablePlaceholders, List<String> variablePlaceholderButtons,
                           Resolution resolution, List<BlockBenchElement> elements,
                           List<BlockBenchTexture> textures, Map<String, ItemTransform> display,
                           List<Outliner> outliner, List<BlockBenchAnimation> animations) {
        this.name = name;
        this.identifier = identifier;
        this.parent = parent;
        this.credit = credit;
        this.modelIdentifier = modelIdentifier;
        this.metadata = metadata;
        this.ambientOcclusion = ambientOcclusion;
        this.frontGuiLight = frontGuiLight;
        this.visibleBox = visibleBox;
        this.variablePlaceholders = variablePlaceholders;
        this.variablePlaceholderButtons = variablePlaceholderButtons;
        this.resolution = resolution;
        this.elements = elements;
        this.textures = textures;
        this.display = display;
        this.outliner = outliner;
        this.animations = animations;
    }

    public String name() {
        return name;
    }

    public String identifier() {
        return identifier;
    }

    @Nullable
    public String parent() {
        return parent;
    }

    @Nullable
    public String credit() {
        return credit;
    }

    public String modelIdentifier() {
        return modelIdentifier;
    }

    public Metadata metadata() {
        return metadata;
    }

    public boolean ambientOcclusion() {
        return ambientOcclusion;
    }

    public boolean frontGuiLight() {
        return frontGuiLight;
    }

    public float[] visibleBox() {
        return visibleBox;
    }

    public String variablePlaceholders() {
        return variablePlaceholders;
    }

    public List<String> variablePlaceholderButtons() {
        return variablePlaceholderButtons;
    }

    public Resolution resolution() {
        return resolution;
    }

    public List<BlockBenchElement> elements() {
        return elements;
    }

    public List<BlockBenchTexture> textures() {
        return textures;
    }

    public Map<String, ItemTransform> display() {
        return display;
    }

    public List<Outliner> outliner() {
        return outliner;
    }

    public List<BlockBenchAnimation> animations() {
        return animations;
    }
}
