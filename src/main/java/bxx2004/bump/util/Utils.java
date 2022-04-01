package bxx2004.bump.util;

import bxx2004.bump.Bump;
import net.guizhanss.guizhanlib.utils.ChatUtil;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.logging.Level;

public class Utils {

    private Utils() {}

    @ParametersAreNonnullByDefault
    public static void log(Level level, String message, Object... args) {
        Bump.getInstance().getLogger().log(level, ChatUtil.color(MessageFormat.format(message, args)));
    }
}
