package com.rappytv.tbw.events;

import com.rappytv.tbw.ToolBreakWarning;
import com.rappytv.tbw.util.Util;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;

public class OnTickEvent {

    @Subscribe
    public void onTick(final TickEvent event) {
        if (!ToolBreakWarning.enabled) return;
        if (Minecraft.getInstance().player == null) return;
        ClientPlayerEntity p = Minecraft.getInstance().player;
        ItemStack i = p.getHeldItemMainhand();

        if (i == null) return;
        if (!i.getItem().isDamageable()) return;
        if (p.isCreative()) return;

        if (Util.isSword(i)) {
            Util.swordUsed(i);
        }
        if (Util.isPickaxe(i)) {
            Util.pickaxeUsed(i);
        } else if (Util.isAxe(i)) {
            Util.axeUsed(i);
        } else if (Util.isShovel(i)) {
            Util.shovelUsed(i);
        }
    }
}