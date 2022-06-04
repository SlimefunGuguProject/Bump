package org.slimefunguguproject.bump.implementation.tasks;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import net.guizhanss.guizhanlib.common.Scheduler;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.WitherSkull;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;
import org.slimefunguguproject.bump.implementation.items.weapons.WitherSkullBow;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The {@link WitherSkullBowTask} is responsible for tracking
 * {@link WitherSkull WitherSkulls} fired from {@link WitherSkullBow}.
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

    /**
     * This method will add {@link WitherSkull} to tracking list.
     *
     * @param skull the {@link WitherSkull} to be added.
     */
    public static void track(@Nonnull WitherSkull skull) {
        Validate.notNull(skull, "Wither skull should not be null. How?");
        instance.trackSkull(skull);
    }

    private void trackSkull(WitherSkull skull) {
        skullSpawnMap.put(skull, Bump.getSlimefunTickCount());
    }
}
