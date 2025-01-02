package priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.face;

import com.google.gson.annotations.SerializedName;
import priv.seventeen.artist.arcartx.bbmodel2geomodel.loader.element.uv.BlockBenchUv;

public class BlockBenchElementFace {
    @SerializedName("north")
    private BlockBenchUv north;
    @SerializedName("east")
    private BlockBenchUv east;
    @SerializedName("south")
    private BlockBenchUv south;
    @SerializedName("west")
    private BlockBenchUv west;
    @SerializedName("up")
    private BlockBenchUv up;
    @SerializedName("down")
    private BlockBenchUv down;

    public BlockBenchElementFace(BlockBenchUv north, BlockBenchUv east, BlockBenchUv south,
                                 BlockBenchUv west, BlockBenchUv up, BlockBenchUv down) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.up = up;
        this.down = down;
    }

    public BlockBenchUv north() {
        return north;
    }

    public BlockBenchUv east() {
        return east;
    }

    public BlockBenchUv south() {
        return south;
    }

    public BlockBenchUv west() {
        return west;
    }

    public BlockBenchUv up() {
        return up;
    }

    public BlockBenchUv down() {
        return down;
    }
}