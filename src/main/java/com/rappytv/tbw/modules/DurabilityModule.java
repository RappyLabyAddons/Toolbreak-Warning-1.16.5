package com.rappytv.tbw.modules;

import com.rappytv.tbw.ToolBreakWarning;
import com.rappytv.tbw.util.Util;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DurabilityModule extends SimpleModule {

    @Override
    public boolean isShown() {
        ClientPlayerEntity p = Minecraft.getInstance().player;
        if (p == null) return false;
        ItemStack i = p.getHeldItemMainhand();
        if (i == null) return false;
        if (!i.getItem().isDamageable()) return false;
        if (p.isCreative()) return false;

        return Util.isSword(i) || Util.isPickaxe(i) || Util.isAxe(i) || Util.isShovel(i);
    }

    @Override
    public String getDisplayName() {
        return "Durability";
    }

    @Override
    public String getDisplayValue() {
        ItemStack itemStack = Minecraft.getInstance().player.getHeldItemMainhand();

        String durability = "0/0";

        if (Util.isSword(itemStack) || Util.isPickaxe(itemStack) || Util.isAxe(itemStack) || Util.isShovel(itemStack)) {
            String maxDamage = ToolBreakWarning.format ? Util.formatNumber(itemStack.getMaxDamage()) : Integer.toString(itemStack.getMaxDamage());
            String damage = ToolBreakWarning.format ? Util.formatNumber(itemStack.getMaxDamage() - itemStack.getDamage()) : Integer.toString(itemStack.getMaxDamage() - itemStack.getDamage());

            durability = damage + "/" + maxDamage;
        }
        return durability;
    }

    @Override
    public String getDefaultValue() {
        return ToolBreakWarning.format ? Util.formatNumber(1561) : Integer.toString(1561);
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("tbw/textures/icon.png"));
    }

    @Override
    public void loadSettings() {
    }

    @Override
    public String getSettingName() {
        return "durability";
    }

    @Override
    public String getControlName() {
        return "Tool durability";
    }

    @Override
    public String getDescription() {
        return "Displays the durability of your currently held pickaxe, axe, or shovel";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return ModuleCategoryRegistry.CATEGORY_INFO;
    }
}