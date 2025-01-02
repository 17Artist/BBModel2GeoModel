package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.adapters;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import com.google.gson.*;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.outliner.Outliner;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.utils.JsonUtils;


public class OutlinerGsonAdapter implements JsonDeserializer<Outliner> {

    @Override
    public Outliner deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonPrimitive())
            return Outliner.redirect(UUID.fromString(jsonElement.getAsString()));
        UUID uuid = UUID.fromString(jsonElement.getAsJsonObject().get("uuid").getAsString());
        String name = jsonElement.getAsJsonObject().get("name").getAsString();
        int color = jsonElement.getAsJsonObject().get("color").getAsInt();
        boolean export = this.getIfPresent(jsonElement, "export");
        boolean mirrorUV = this.getIfPresent(jsonElement, "mirrorUV");
        String nbt = jsonElement.getAsJsonObject().has("nbt") ? jsonElement.getAsJsonObject().get("nbt").getAsString() : null;
        Float[] origin = jsonElement.getAsJsonObject().has("origin")
                ? JsonUtils.streamOf(jsonElement.getAsJsonObject().get("origin").getAsJsonArray())
                .map(JsonElement::getAsFloat)
                .toArray(Float[]::new)
                : new Float[]{0.0f, 0.0f, 0.0f};
        Float[] rotation = jsonElement.getAsJsonObject().has("rotation")
                ? JsonUtils.streamOf(jsonElement.getAsJsonObject().get("rotation").getAsJsonArray())
                .map(JsonElement::getAsFloat)
                .toArray(Float[]::new)
                : new Float[]{0.0f, 0.0f, 0.0f};

        boolean visible = this.getIfPresent(jsonElement, "visible");

        List<Outliner> children = JsonUtils.deserializeList(
                JsonUtils.getArray(jsonElement.getAsJsonObject(), "children"),
                element -> deserialize(element, type, jsonDeserializationContext)
        );

        return new Outliner(uuid, name, color, export, mirrorUV, nbt, origin,rotation, visible, children);
    }

    private boolean getIfPresent(JsonElement jsonElement, String key) {
        return jsonElement.getAsJsonObject().has(key) && jsonElement.getAsJsonObject().get(key).getAsBoolean();
    }

}
