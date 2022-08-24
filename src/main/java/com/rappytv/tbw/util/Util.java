package com.rappytv.tbw.util;

import com.rappytv.tbw.ToolBreakWarning;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Util {

    public static boolean swordWarned = false;
    public static boolean pickWarned = false;
    public static boolean axeWarned = false;
    public static boolean shovelWarned = false;

    private static final String[] sword = {"item.minecraft.wooden_sword", "item.minecraft.stone_sword", "item.minecraft.iron_sword", "item.minecraft.golden_sword", "item.minecraft.diamond_sword", "item.minecraft.netherite_sword"};
    private static final String[] pick = {"item.minecraft.wooden_pickaxe", "item.minecraft.stone_pickaxe", "item.minecraft.iron_pickaxe", "item.minecraft.golden_pickaxe", "item.minecraft.diamond_pickaxe", "item.minecraft.netherite_pickaxe"};
    private static final String[] axe = {"item.minecraft.wooden_axe", "item.minecraft.stone_axe", "item.minecraft.iron_axe", "item.minecraft.golden_axe", "item.minecraft.diamond_axe", "item.minecraft.netherite_axe"};
    private static final String[] spade = {"item.minecraft.wooden_shovel", "item.minecraft.stone_shovel", "item.minecraft.iron_shovel", "item.minecraft.golden_shovel", "item.minecraft.diamond_shovel", "item.minecraft.netherite_shovel"};

    public static void msg(String text, boolean prefix) {
        ToolBreakWarning.getMain().getApi().displayMessageInChat(prefix ? ToolBreakWarning.prefix + text : text);
    }

    public static String formatNumber(int number) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(number);
    }

    public static boolean isLastHit(ItemStack i) {
        if (!ToolBreakWarning.lastHitWarn) return false;
        return (i.getMaxDamage() - i.getDamage()) == 1;
    }

    public static boolean isSword(ItemStack i) {
        if (i.getTranslationKey().equalsIgnoreCase(sword[0])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(sword[1])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(sword[2])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(sword[3])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(sword[4])) return true;
        else return i.getTranslationKey().equalsIgnoreCase(sword[5]);
    }

    public static boolean isPickaxe(ItemStack i) {
        if (i.getTranslationKey().equalsIgnoreCase(pick[0])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(pick[1])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(pick[2])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(pick[3])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(pick[3])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(pick[4])) return true;
        else return i.getTranslationKey().equalsIgnoreCase(pick[5]);
    }

    public static boolean isAxe(ItemStack i) {
        if (i.getTranslationKey().equalsIgnoreCase(axe[0])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(axe[1])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(axe[2])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(axe[3])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(axe[4])) return true;
        else return i.getTranslationKey().equalsIgnoreCase(axe[5]);
    }

    public static boolean isShovel(ItemStack i) {
        if (i.getTranslationKey().equalsIgnoreCase(spade[0])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(spade[1])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(spade[2])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(spade[3])) return true;
        else if (i.getTranslationKey().equalsIgnoreCase(spade[4])) return true;
        else return i.getTranslationKey().equalsIgnoreCase(spade[5]);
    }

    public static void swordUsed(ItemStack i) {
        int itemWarnInt = (ToolBreakWarning.warnPercentageSword * i.getMaxDamage()) / 100;
        int itemUsedInt = i.getMaxDamage() - i.getDamage();
        if (ToolBreakWarning.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Sword in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if (itemUsedInt == itemWarnInt) {
            if (!swordWarned) {
                msg(ToolBreakWarning.warnMsg.replace("{durability}", ToolBreakWarning.warnPercentageSword + ""), true);
                msg(ToolBreakWarning.warnMsg.replace("{durability}", ToolBreakWarning.warnPercentageSword + ""), true);
                msg(ToolBreakWarning.warnMsg.replace("{durability}", ToolBreakWarning.warnPercentageSword + ""), true);
                Minecraft.getInstance().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 100f, 0f);
                swordWarned = true;
            }
        } else if (isLastHit(i)) {
            if (!swordWarned) {
                msg(ToolBreakWarning.lastHitMsg, true);
                msg(ToolBreakWarning.lastHitMsg, true);
                msg(ToolBreakWarning.lastHitMsg, true);
                Minecraft.getInstance().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 100f, 0f);
                swordWarned = true;
            }
        } else {
            swordWarned = false;
        }
    }

    public static void pickaxeUsed(ItemStack i) {
        int itemWarnInt = (ToolBreakWarning.warnPercentagePickaxe * i.getMaxDamage()) / 100;
        int itemUsedInt = i.getMaxDamage() - i.getDamage();
        if (ToolBreakWarning.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Pickaxe in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if (itemUsedInt == itemWarnInt) {
            if (!pickWarned) {
                Minecraft.getInstance().displayGuiScreen(new ChatScreen(""));
                msg(ToolBreakWarning.warnMsg.replace("{durability}", ToolBreakWarning.warnPercentagePickaxe + ""), true);
                pickWarned = true;
            }
        } else if (isLastHit(i)) {
            if (!pickWarned) {
                Minecraft.getInstance().displayGuiScreen(new ChatScreen(""));
                msg(ToolBreakWarning.lastHitMsg, true);
                pickWarned = true;
            }
        } else {
            pickWarned = false;
        }
    }

    public static void axeUsed(ItemStack i) {
        int itemWarnInt = (ToolBreakWarning.warnPercentageAxe * i.getMaxDamage()) / 100;
        int itemUsedInt = i.getMaxDamage() - i.getDamage();
        if (ToolBreakWarning.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Axe in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if (itemUsedInt == itemWarnInt) {
            if (!axeWarned) {
                Minecraft.getInstance().displayGuiScreen(new ChatScreen(""));
                msg(ToolBreakWarning.warnMsg.replace("{durability}", ToolBreakWarning.warnPercentageAxe + ""), true);
                axeWarned = true;
            }
        } else if (isLastHit(i)) {
            if (!axeWarned) {
                Minecraft.getInstance().displayGuiScreen(new ChatScreen(""));
                msg(ToolBreakWarning.lastHitMsg, true);
                axeWarned = true;
            }
        } else {
            axeWarned = false;
        }
    }

    public static void shovelUsed(ItemStack i) {
        int itemWarnInt = (ToolBreakWarning.warnPercentageShovel * i.getMaxDamage()) / 100;
        int itemUsedInt = i.getMaxDamage() - i.getDamage();
        if (ToolBreakWarning.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Shovel in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if (itemUsedInt == itemWarnInt) {
            if (!shovelWarned) {
                Minecraft.getInstance().displayGuiScreen(new ChatScreen(""));
                msg(ToolBreakWarning.warnMsg.replace("{durability}", ToolBreakWarning.warnPercentageShovel + ""), true);
                shovelWarned = true;
            }
        } else if (isLastHit(i)) {
            if (!shovelWarned) {
                Minecraft.getInstance().displayGuiScreen(new ChatScreen(""));
                msg(ToolBreakWarning.lastHitMsg, true);
                shovelWarned = true;
            }
        } else {
            shovelWarned = false;
        }
    }
}
