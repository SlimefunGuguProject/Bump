package bxx2004.bump.util;

import bxx2004.bump.Bump;
import net.guizhanss.guizhanlib.utils.ChatUtil;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

@ParametersAreNonnullByDefault
public class Utils {

    private Utils() {}

    public static void log(Level level, String message, Object... args) {
        Bump.getInstance().getLogger().log(level, ChatUtil.color(MessageFormat.format(message, args)));
    }

    public static boolean testChance(int chance, int upper) {
        return ThreadLocalRandom.current().nextInt(1, upper + 1) <= chance;
    }
}
