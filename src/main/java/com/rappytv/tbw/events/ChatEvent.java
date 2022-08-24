package com.rappytv.tbw.events;

import com.rappytv.tbw.ToolBreakWarning;
import com.rappytv.tbw.util.Util;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;

public class ChatEvent {

    @Subscribe
    public void onSend(final MessageSendEvent event) {
        event.setCancelled(onSend(event.getMessage()));
    }

    public boolean onSend(String s) {
        String[] args = s.split(" ");
        if (args[0].equalsIgnoreCase("/tbw")) {
            if (args.length < 2) {
                Util.msg("\u00A7cPlease provide a valid argument! Args: enable, disable, format, debug", true);
                return true;
            }
            if (args[1].equalsIgnoreCase("enable")) {
                ToolBreakWarning.enabled = true;

                ToolBreakWarning.getMain().getConfig().addProperty("enabled", ToolBreakWarning.enabled);
                ToolBreakWarning.getMain().saveConfig();
                Util.msg("\u00A7eAddon successfully \u00A7aenabled\u00A7e!", true);
                return true;
            } else if (args[1].equalsIgnoreCase("disable")) {
                ToolBreakWarning.enabled = false;

                ToolBreakWarning.getMain().getConfig().addProperty("enabled", ToolBreakWarning.enabled);
                ToolBreakWarning.getMain().saveConfig();
                Util.msg("\u00A7eAddon successfully \u00A7cdisabled\u00A7e!", true);
                return true;
            } else if (args[1].equalsIgnoreCase("format")) {
                if (args.length >= 3) {
                    if (args[2].equalsIgnoreCase("on")) {
                        ToolBreakWarning.format = true;

                        ToolBreakWarning.getMain().getConfig().addProperty("format", ToolBreakWarning.format);
                        ToolBreakWarning.getMain().saveConfig();
                        Util.msg("\u00A72Number formatting \u00A7esuccessfully \u00A7aenabled\u00A7e!", true);
                        return true;
                    } else if (args[2].equalsIgnoreCase("off")) {
                        ToolBreakWarning.format = false;

                        ToolBreakWarning.getMain().getConfig().addProperty("format", ToolBreakWarning.format);
                        ToolBreakWarning.getMain().saveConfig();
                        Util.msg("\u00A7eNumber formatting successfully \u00A7cdisabled\u00A7e!", true);
                        return true;
                    } else {
                        Util.msg("\u00A7cPlease provide a valid argument! Args: on, off", true);
                    }
                } else {
                    Util.msg("\u00A7cPlease provide a valid argument! Args: on, off", true);
                }
                return true;
            } else if (args[1].equalsIgnoreCase("debug")) {
                if (args.length >= 3) {
                    if (args[2].equalsIgnoreCase("on")) {
                        ToolBreakWarning.enabled = true;
                        ToolBreakWarning.debug = true;

                        ToolBreakWarning.getMain().getConfig().addProperty("debug", ToolBreakWarning.debug);
                        ToolBreakWarning.getMain().saveConfig();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7aenabled\u00A7e!", true);
                    } else if (args[2].equalsIgnoreCase("once")) {
                        ClientPlayerEntity p = Minecraft.getInstance().player;
                        ItemStack itemStack = p.getHeldItemMainhand();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7aenabled \u00A7eonce!", true);

                        if (Util.isSword(itemStack)) {
                            int itemWarnInt = (ToolBreakWarning.warnPercentageSword * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Sword in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else if (Util.isPickaxe(itemStack)) {
                            int itemWarnInt = (ToolBreakWarning.warnPercentagePickaxe * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Pickaxe in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else if (Util.isAxe(itemStack)) {
                            int itemWarnInt = (ToolBreakWarning.warnPercentageAxe * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Axe in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else if (Util.isShovel(itemStack)) {
                            int itemWarnInt = (ToolBreakWarning.warnPercentageShovel * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Shovel in main hand\n\u00A7eTool used: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (ToolBreakWarning.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else {
                            Util.msg("\u00A7c\u00A7l------ No Event triggered ------\n\u00A7eEvent: \u00A74-\n\u00A7eTool used: \u00A74-\n\u00A7eTool warn: \u00A74-\n\u00A7c\u00A7l------------------------------", false);
                        }
                    } else if (args[2].equalsIgnoreCase("off")) {
                        ToolBreakWarning.debug = false;

                        ToolBreakWarning.getMain().getConfig().addProperty("debug", ToolBreakWarning.debug);
                        ToolBreakWarning.getMain().saveConfig();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7cdisabled\u00A7e!", true);
                    } else {
                        Util.msg("\u00A7cPlease provide a valid argument! Args: on, off", true);
                    }
                } else {
                    Util.msg("\u00A7cPlease provide a valid argument! Args: on, once, off", true);
                }
                return true;
            } else {
                Util.msg("\u00A7cPlease provide a valid argument! Args: enable, disable, format, debug", true);
                return true;
            }
        } else return false;
    }
}