package me.badbones69.crazycrates.multisupport;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.badbones69.crazycrates.api.interfaces.HologramController;
import me.badbones69.crazycrates.api.objects.Crate;
import me.badbones69.crazycrates.api.objects.CrateHologram;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class DecentHologramsSupport implements HologramController {

    private static final Set<String> USED_HOLOGRAMS = new HashSet<>();

    @Override
    public void createHologram(Block block, Crate crate) {
        CrateHologram crateHologram = crate.getHologram();
        if (crateHologram.isEnabled()) {
            double height = crateHologram.getHeight();
            final String name = "crazycrates-" + crate.getName();
            final Hologram actualHologram = DHAPI.getHologram(name);
            if (actualHologram != null) {
                DHAPI.removeHologram(name);
            }
            USED_HOLOGRAMS.add(name);
            final Hologram hologram = DHAPI.createHologram(name, block.getLocation().add(.5, height, .5));
            for (String line : crateHologram.getMessages()) {
                DHAPI.addHologramLine(hologram, line);
            }
        }
    }

    @Override
    public void removeHologram(Block block, Crate crate) {
        DHAPI.removeHologram("crazycrates-" + crate.getName());
    }

    @Override
    public void removeAllHolograms() {
        for (String name : USED_HOLOGRAMS) {
            DHAPI.removeHologram(name);
        }
    }
}
