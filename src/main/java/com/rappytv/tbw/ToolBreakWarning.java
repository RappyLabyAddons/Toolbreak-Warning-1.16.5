package com.rappytv.tbw;

import com.rappytv.tbw.events.ChatEvent;
import com.rappytv.tbw.events.OnTickEvent;
import com.rappytv.tbw.modules.DurabilityModule;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ToolBreakWarning extends LabyModAddon {

    public static String prefix = "\u00A79\u00A7lTBW \u00A78\u00bb \u00A7r";
    public static boolean enabled = true;
    public static boolean format = true;
    public static boolean debug = false;
    public static String warnMsg = "\u00A7cDo you really want to continue using this tool? Its durability is {durability}%!";
    public static String lastHitMsg = "\u00A7cYou should stop using this tool now, this is your last hit!";
    public static boolean lastHitWarn = true;
    public static int warnPercentageSword = 5;
    public static int warnPercentagePickaxe = 5;
    public static int warnPercentageAxe = 5;
    public static int warnPercentageShovel = 5;
    public static ToolBreakWarning instance;

    @Override
    public void onEnable() {
        // Sets the instance
        instance = this;

        getApi().getEventService().registerListener(new ChatEvent());
        getApi().getEventService().registerListener(new OnTickEvent());
        getApi().registerModule(new DurabilityModule());
    }

    @Override
    public void loadConfig() {
        enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
        format = getConfig().has("format") ? getConfig().get("format").getAsBoolean() : format;
        debug = getConfig().has("debug") ? getConfig().get("debug").getAsBoolean() : debug;
        warnMsg = getConfig().has("warnmsg") ? getConfig().get("warnmsg").getAsString() : warnMsg;

        lastHitWarn = getConfig().has("lastHitWarn") ? getConfig().get("lastHitWarn").getAsBoolean() : lastHitWarn;
        warnPercentageSword = getConfig().has("sword") ? getConfig().get("sword").getAsInt() : warnPercentageSword;
        warnPercentagePickaxe = getConfig().has("pick") ? getConfig().get("pick").getAsInt() : warnPercentagePickaxe;
        warnPercentageAxe = getConfig().has("axe") ? getConfig().get("axe").getAsInt() : warnPercentageAxe;
        warnPercentageShovel = getConfig().has("shovel") ? getConfig().get("shovel").getAsInt() : warnPercentageShovel;
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        BooleanElement formatBool = new BooleanElement("Format Numbers", new ControlElement.IconData(new ResourceLocation("tbw/textures/format.png")), accepted -> {
            format = accepted;

            getConfig().addProperty("format", format);
            saveConfig();
        }, format);

        BooleanElement warnOnLastHit = new BooleanElement("Warn on last hit", new ControlElement.IconData(new ResourceLocation("tbw/textures/0p.png")), accepted -> {
            lastHitWarn = accepted;

            getConfig().addProperty("lastHitWarn", lastHitWarn);
            saveConfig();
        }, lastHitWarn);

        BooleanElement debugBool = new BooleanElement("Debug Mode (Development Feature)", new ControlElement.IconData(new ResourceLocation("tbw/textures/matrix.png")), accepted -> {
            debug = accepted;

            getConfig().addProperty("debug", debug);
            saveConfig();
        }, debug);

        StringElement warnmsg = new StringElement("Warn Message", new ControlElement.IconData(Material.REDSTONE_TORCH), warnMsg, accepted -> {
            warnMsg = accepted;

            getConfig().addProperty("msg", warnMsg);
            saveConfig();
        });

        SliderElement swordSlider = new SliderElement("Sword Warn Percentage",
                new ControlElement.IconData(Material.IRON_SWORD), warnPercentageSword);
        SliderElement pickSlider = new SliderElement("Pickaxe Warn Percentage",
                new ControlElement.IconData(Material.IRON_PICKAXE), warnPercentagePickaxe);
        SliderElement axeSlider = new SliderElement("Axe Warn Percentage",
                new ControlElement.IconData(Material.IRON_AXE), warnPercentageAxe);
        SliderElement spadeSlider = new SliderElement("Shovel Warn Percentage",
                new ControlElement.IconData(Material.IRON_SHOVEL), warnPercentageShovel);


        BooleanElement enabledBool = new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), accepted -> {
            enabled = accepted;

            formatBool.setBlocked(!enabled);
            formatBool.setHoverable(enabled);
            warnOnLastHit.setBlocked(!enabled);
            warnOnLastHit.setHoverable(enabled);
            debugBool.setBlocked(!enabled);
            debugBool.setHoverable(enabled);
            warnmsg.setBlocked(!enabled);
            warnmsg.setHoverable(enabled);
            swordSlider.setBlocked(!enabled);
            swordSlider.setHoverable(enabled);
            pickSlider.setBlocked(!enabled);
            pickSlider.setHoverable(enabled);
            axeSlider.setBlocked(!enabled);
            axeSlider.setHoverable(enabled);
            spadeSlider.setBlocked(!enabled);
            spadeSlider.setHoverable(enabled);

            getConfig().addProperty("enabled", enabled);
            saveConfig();
        }, enabled);

        formatBool.setDescriptionText("Unformatted Number: 1561\nFormatted Number: 1.561");

        debugBool.setDescriptionText("Sends a message in the chat every tick you're holding a tool.");

        warnOnLastHit.setDescriptionText("Warns you when your tool is at 0 durability");

        {
            // Setting the slider's min & max values
            swordSlider.setRange(1, 25);

            // Setting slider steps
            swordSlider.setSteps(1);

            // Adding change listener
            swordSlider.addCallback(accepted -> {
                warnPercentageSword = accepted;

                getConfig().addProperty("sword", warnPercentageSword);
                saveConfig();
            });
        }

        {
            // Setting the slider's min & max values
            pickSlider.setRange(1, 25);

            // Setting slider steps
            pickSlider.setSteps(1);

            // Adding change listener
            pickSlider.addCallback(accepted -> {
                warnPercentagePickaxe = accepted;

                getConfig().addProperty("pick", warnPercentagePickaxe);
                saveConfig();
            });
        }

        {
            // Setting the slider's min & max values
            axeSlider.setRange(1, 25);

            // Setting slider steps
            axeSlider.setSteps(1);

            // Adding change listener
            axeSlider.addCallback(accepted -> {
                warnPercentageAxe = accepted;

                getConfig().addProperty("axe", warnPercentageAxe);
                saveConfig();
            });
        }

        {
            // Setting the slider's min & max values
            spadeSlider.setRange(1, 25);

            // Setting slider steps
            spadeSlider.setSteps(1);

            // Adding change listener
            spadeSlider.addCallback(accepted -> {
                warnPercentageShovel = accepted;

                getConfig().addProperty("shovel", warnPercentageShovel);
                saveConfig();
            });
        }

        // Adding setting
        list.add(new HeaderElement("General"));
        list.add(enabledBool);
        list.add(formatBool);
        list.add(warnOnLastHit);
        list.add(debugBool);
        list.add(new HeaderElement("\u00A7cWarn Settings"));
        list.add(warnmsg);
        list.add(swordSlider);
        list.add(pickSlider);
        list.add(axeSlider);
        list.add(spadeSlider);
    }

    public static ToolBreakWarning getMain() {
        return instance;
    }
}
