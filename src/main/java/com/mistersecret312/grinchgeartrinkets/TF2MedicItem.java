package com.mistersecret312.grinchgeartrinkets;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.google.common.collect.Lists;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rafradek.TF2weapons.TF2weapons;
import rafradek.TF2weapons.client.audio.TF2Sounds;

import java.util.List;

public class TF2MedicItem extends Item implements IBauble
{
    public TF2MedicItem(String name)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxStackSize(1);
        this.setMaxDamage(1);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.HEAD;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        List<Potion> ubers = Lists.newArrayList(TF2weapons.uber, TF2weapons.quickFix, TF2weapons.critBoost, TF2weapons.shieldBullet, TF2weapons.shieldExplosive, TF2weapons.shieldFire);

        if(player.world.getWorldTime() % 200 == 0)
            player.heal(1f);

        setUberTimer(itemstack, getUberTimer(itemstack)+1);
        if(getUberTimer(itemstack) == 12000)
        {
            setUberTimer(itemstack, 0);

            int index = player.world.rand.nextInt(ubers.size());
            Potion potion = ubers.get(index);
            int power = potion == TF2weapons.shieldBullet
                    || potion == TF2weapons.shieldExplosive
                    || potion == TF2weapons.shieldFire
                    ? 1 : 0;
            PotionEffect effect = new PotionEffect(potion, 1200, power, false, false);
            player.getActivePotionMap().put(potion, effect);
            player.playSound(TF2Sounds.MOB_MEDIC_SAY, 1F, 1F);
        }
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {

    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {

    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }

    public int getUberTimer(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
        {
            stack.setTagCompound(new NBTTagCompound());
            return 0;
        }
        return tag.getInteger("timer");
    }

    public void setUberTimer(ItemStack stack, int time)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
            tag = new NBTTagCompound();
        tag.setInteger("timer", time);
    }
}
