package org.slimefunguguproject.bump.implementation.tasks;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import net.guizhanss.guizhanlib.common.Scheduler;
import org.bukkit.entity.WitherSkull;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;
import org.slimefunguguproject.bump.implementation.items.weapons.WitherSkullBow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The {@link WitherSkullBowTask} is responsible for track
 * {@link WitherSkull WitherSkulls} fired from a {@link WitherSkullBow}.
 *
 * @author ybw0014
 */
public final class WitherSkullBowTask implements Runnable {

    private static WitherSkullBowTask instance;

    // This map remembers each wither skull's spawn time
    private final Map<WitherSkull, Integer> skullSpawnMap = new HashMap<>();
    private int existTime;

    public WitherSkullBowTask(int existingTime) {
        instance = this;
        this.existTime = existingTime;
    }

    @Override
    public void run() {
        int currentTick = Bump.getSlimefunTickCount();

        Iterator<Map.Entry<WitherSkull, Integer>> it = skullSpawnMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<WitherSkull, Integer> entry = it.next();
            if (entry.getValue() + existTime < currentTick) {
                WitherSkull skull = entry.getKey();
                if (skull.isValid()) {
                    skull.remove();
                }
                it.remove();
            }
        }
    }

    /**
     * This method starts this task
     */
    public static void start() {
        SlimefunItem sfItem = SlimefunItem.getByItem(BumpItems.WITHERSKULL_BOW);
        if (sfItem == null) {
            return;
        }
        WitherSkullBow bow = (WitherSkullBow) sfItem;
        if (bow.getSkullExistingTime() > 0) {
            Scheduler.repeat(Slimefun.getTickerTask().getTickRate(), new WitherSkullBowTask(bow.getSkullExistingTime()));
        }
    }

    private static WitherSkullBowTask getTask() {
        return instance;
    }

    public static void track(WitherSkull skull) {
        getTask().trackSkull(skull);
    }

    public void trackSkull(WitherSkull skull) {
        skullSpawnMap.put(skull, Bump.getSlimefunTickCount());
    }
}
