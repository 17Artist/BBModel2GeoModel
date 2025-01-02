package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jetbrains.annotations.NotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.adapters.OutlinerGsonAdapter;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.model.BlockBenchModel;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.outliner.Outliner;

public class BlockBenchModelReader {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(Outliner.class, new OutlinerGsonAdapter())
            .create();


    public @NotNull BlockBenchModel read(@NotNull InputStream input) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input))) {
            return GSON.fromJson(bufferedReader, BlockBenchModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read BlockBench model from input stream", e);
        }
    }


    public @NotNull BlockBenchModel read(@NotNull Path path) {
        try (InputStream input = Files.newInputStream(path)) {
            return read(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read BlockBench model from path: " + path, e);
        }
    }
}
