package com.mistersecret312.grinchgeartrinkets;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.hbm.potion.HbmPotion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rafradek.TF2weapons.TF2weapons;
import rafradek.TF2weapons.client.audio.TF2Sounds;

import java.util.List;
import java.util.UUID;

public class RadProtItem extends Item implements IBauble
{
    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6920A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-69469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-69846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-64FD380BB150")};

    public RadProtItem(String name)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxStackSize(1);
        this.setMaxDamage(1);

        ItemInit.ITEMS.add(this);
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(
            EntityEquipmentSlot modifiers)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(modifiers);
        multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[modifiers.getIndex()], "Armor modifier", (double) 10, 0));
        multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[modifiers.getIndex()], "Armor toughness", (double) 8, 0));

        return multimap;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.TRINKET;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        player.removePotionEffect(HbmPotion.radiation);
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.getAttributeMap().applyAttributeModifiers(getItemAttributeModifiers(EntityEquipmentSlot.CHEST));
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.getAttributeMap().applyAttributeModifiers(getItemAttributeModifiers(EntityEquipmentSlot.CHEST));
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
