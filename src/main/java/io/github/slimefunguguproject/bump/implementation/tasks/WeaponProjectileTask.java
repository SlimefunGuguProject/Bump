package io.github.slimefunguguproject.bump.implementation.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.entity.Projectile;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

import net.guizhanss.guizhanlib.common.Scheduler;

/**
 * The {@link WeaponProjectileTask} is responsible for tracking
 * {@link Projectile} fired from Bump weapons.
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
public final class WeaponProjectileTask implements Runnable {

    private static WeaponProjectileTask instance;

    // This map records each projectile's spawn time
    private final Map<Projectile, Integer> projectileMap = new HashMap<>();
    private final int duration;

    public WeaponProjectileTask(int duration) {
        instance = this;
        this.duration = duration;
    }

    /**
     * This method starts this task
     */
    public static void start() {
        int duration = Bump.getRegistry().getConfig().getInt("weapons.projectile-duration", 0, 60);
        if (duration > 0) {
            Scheduler.repeat(Slimefun.getTickerTask().getTickRate(), new WeaponProjectileTask(duration));
        }
    }

    /**
     * This method will add {@link Projectile} to tracking list.
     *
     * @param projectile the {@link Projectile} to be added.
     */
    public static void track(@Nonnull Projectile projectile) {
        Preconditions.checkArgument(projectile != null, "Projectile cannot not be null. How?");
        instance.trackProjectile(projectile);
    }

    @Override
    public void run() {
        int currentTick = Bump.getSlimefunTickCount();

        Iterator<Map.Entry<Projectile, Integer>> it = projectileMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Projectile, Integer> entry = it.next();
            if (entry.getValue() + duration < currentTick) {
                Projectile projectile = entry.getKey();
                if (projectile.isValid()) {
                    projectile.remove();
                }
                it.remove();
            }
        }
    }

    private void trackProjectile(Projectile projectile) {
        projectileMap.put(projectile, Bump.getSlimefunTickCount());
    }
}
