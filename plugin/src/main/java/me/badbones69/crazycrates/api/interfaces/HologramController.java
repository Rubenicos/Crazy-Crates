package me.badbones69.crazycrates.api.interfaces;

import me.badbones69.crazycrates.api.objects.Crate;
import org.bukkit.block.Block;

public interface HologramController {
    
    void createHologram(Block block, Crate crate);
    
    default void removeHologram(Block block) {
        // empty default method
    }

    default void removeHologram(Block block, Crate crate) {
        removeHologram(block);
    }
    
    void removeAllHolograms();
    
}