package com.grinderwolf.smw.nms.v1_9_R1;

import com.grinderwolf.smw.api.world.SlimeWorld;
import com.grinderwolf.smw.nms.CraftSlimeWorld;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_9_R1.BlockPosition;
import net.minecraft.server.v1_9_R1.EnumDifficulty;
import net.minecraft.server.v1_9_R1.WorldData;

@Getter
@RequiredArgsConstructor
public class CustomWorldData extends WorldData {

    private final CraftSlimeWorld world;

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public void setSpawn(BlockPosition position) {
        super.setSpawn(position);

        // Keep properties updated
        SlimeWorld.SlimeProperties oldProps = world.getProperties();

        if (oldProps.getSpawnX() != position.getX() || oldProps.getSpawnY() != position.getY() || oldProps.getSpawnZ() != position.getZ()) {
            SlimeWorld.SlimeProperties newProps = oldProps.toBuilder().spawnX(position.getX()).spawnY(position.getY()).spawnZ(position.getZ()).build();
            world.setProperties(newProps);
        }
    }

    @Override
    public void setDifficulty(EnumDifficulty difficulty) {
        super.setDifficulty(difficulty);

        // Keep properties updated
        SlimeWorld.SlimeProperties oldProps = world.getProperties();

        if (oldProps.getDifficulty() != difficulty.a()) {
            SlimeWorld.SlimeProperties newProps = oldProps.toBuilder().difficulty(difficulty.a()).build();
            world.setProperties(newProps);
        }
    }
}