package com.mistersecret312.grinchgeartrinkets;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.List;

public class NightVisionGoggles extends Item implements IBauble
{
    public NightVisionGoggles(String name)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxStackSize(1);
        this.setMaxDamage(1);

        ItemInit.ITEMS.add(this);
    }

    public IEnergyStorage getEnergyStorage(ItemStack stack) {
        if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
            return stack.getCapability(CapabilityEnergy.ENERGY, null);
        }

        return null;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        IEnergyStorage storage = getEnergyStorage(stack);
        return (double) storage.getEnergyStored()/storage.getMaxEnergyStored();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new EnergyProvider(new EnergyItem(stack, 100000));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World p_77624_2_, List<String> strings,
                               ITooltipFlag p_77624_4_)
    {
        super.addInformation(stack, p_77624_2_, strings, p_77624_4_);
        IEnergyStorage storage = getEnergyStorage(stack);
        strings.add("Energy Stored: " + storage.getEnergyStored() + "/" + storage.getMaxEnergyStored() + " RF");
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.HEAD;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        if(getEnergyStorage(itemstack).getEnergyStored() == 0)
            return;

        PotionEffect effect = new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, false, false);
        player.getActivePotionMap().put(MobEffects.NIGHT_VISION, effect);

        getEnergyStorage(itemstack).extractEnergy(8, false);
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
}
