package com.mistersecret312.grinchgeartrinkets;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.tmtravlr.potioncore.potion.PotionDiamondSkin;
import lumien.randomthings.potion.ModPotions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class GemAmuletItem extends Item implements IBauble
{
    public Gem gem;
    public GemAmuletItem(Gem gem)
    {
        this.gem = gem;
        this.setUnlocalizedName(gem.potion.getName().replace("effect.", "")+"_amulet");
        this.setRegistryName(gem.potion.getName().replace("effect.", "")+"_amulet");

        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxStackSize(1);
        this.setMaxDamage(1);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        PotionEffect effect = new PotionEffect(gem.potion, 240, 0, false, false);
        player.getActivePotionMap().put(gem.potion, effect);
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

    @Override
    public void addInformation(ItemStack stack, World p_77624_2_, List<String> strings,
                               ITooltipFlag p_77624_4_)
    {
        super.addInformation(stack, p_77624_2_, strings, p_77624_4_);
    }

    public enum Gem
    {
        RUBY(MobEffects.REGENERATION),
        AMBER(MobEffects.SPEED),
        SAPPHIRE(MobEffects.WATER_BREATHING),
        PERIDOT(MobEffects.JUMP_BOOST),
        MALACHITE(MobEffects.STRENGTH),
        TANZANITE(MobEffects.RESISTANCE),
        TOPAZ(MobEffects.FIRE_RESISTANCE),
        EMERALD(ModPotions.imbueExperience),
        DIAMOND(PotionDiamondSkin.INSTANCE);

        Potion potion;
        Gem(Potion potion)
        {
            this.potion = potion;
        }
    }
}
