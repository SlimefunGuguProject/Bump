package bxx2004.bump.util;

import bxx2004.bump.Bump;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

@ParametersAreNonnullByDefault
public class Utils {

    private Utils() {}

    /**
     * This method calls {@link Bump}'s logger coverts color codes
     *
     * @param level the log {@link Level}
     * @param message the {@link String} of message
     * @param args array of {@link Object} to be injected to message
     */
    public static void log(Level level, String message, Object... args) {
        Bump.getInstance().getLogger().log(level, ChatUtil.color(MessageFormat.format(message, args)));
    }

    /**
     * This method test a chance roll starting from 1 to upper
     *
     * @param chance The number the roll must be lower than
     * @param upper  The highest possible number that could roll (inclusive)
     *
     * @return true if roll passes
     */
    public static boolean testChance(int chance, int upper) {
        return ThreadLocalRandom.current().nextInt(1, upper + 1) <= chance;
    }

    /**
     * This method calls {@link FoodLevelChangeEvent} and set {@link Player}'s
     * food level if {@link Player}'s {@link GameMode} is not creative and
     * the event is not cancelled
     *
     * @param p the {@link Player} that food level will be changed
     * @param level the target food level
     */
    public static void changeFoodLevel(Player p, int level) {
        if (p.getGameMode() != GameMode.CREATIVE) {
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(p, level);
            Bukkit.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                p.setFoodLevel(event.getFoodLevel());
            }
        }
    }
}
