package me.badbones69.crazycrates.multisupport;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.DecentHologramsAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.badbones69.crazycrates.api.interfaces.HologramController;
import me.badbones69.crazycrates.api.objects.Crate;
import me.badbones69.crazycrates.api.objects.CrateHologram;
import org.bukkit.block.Block;

public class DecentHologramsSupport implements HologramController {
    @Override
    public void createHologram(Block block, Crate crate) {
        CrateHologram crateHologram = crate.getHologram();
        if (crateHologram.isEnabled()) {
            double height = crateHologram.getHeight();
            final Hologram hologram = DHAPI.createHologram("crazycrates:" + crate.getName(), block.getLocation().add(.5, height, .5));
            for (String line : crateHologram.getMessages()) {
                DHAPI.addHologramLine(hologram, line);
            }
        }
    }

    @Override
    public void removeHologram(Block block, Crate crate) {
        DHAPI.removeHologram("crazycrates:" + crate.getName());
    }

    @Override
    public void removeAllHolograms() {
        for (Hologram hologram : DecentHologramsAPI.get().getHologramManager().getHolograms()) {
            if (hologram.getName().startsWith("crazycrates:")) {
                hologram.delete();
            }
        }
    }
}
