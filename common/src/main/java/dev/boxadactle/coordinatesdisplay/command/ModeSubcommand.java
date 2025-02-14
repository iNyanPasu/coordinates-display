package dev.boxadactle.coordinatesdisplay.command;

import dev.boxadactle.boxlib.command.api.BSubcommand;
import dev.boxadactle.boxlib.command.api.subcommand.BasicSubcommand;
import dev.boxadactle.coordinatesdisplay.CoordinatesDisplay;
import dev.boxadactle.coordinatesdisplay.registry.DisplayMode;
import net.minecraft.client.resources.language.I18n;

public class ModeSubcommand {

    public static BSubcommand create() {
        DisplayMode[] modes = DisplayMode.values();

        BSubcommand subcommand = new BasicSubcommand("mode", CoordinatesCommand::noArgs);

        for (DisplayMode mode : modes) {
            subcommand.registerSubcommand(new BasicSubcommand(mode.name().toLowerCase(), (context) -> {
                CoordinatesDisplay.getConfig().renderMode = mode;
                CoordinatesDisplay.CONFIG.save();

                CoordinatesDisplay.LOGGER.player.info(I18n.get("button.coordinatesdisplay.displayMode", mode.getName()));

                return 0;
            }));
        }

        return subcommand;
    }

}
