package com.mistersecret312.grinchgeartrinkets;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import lumien.randomthings.item.ModItems;
import net.daveyx0.primitivemobs.core.PrimitiveMobsItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ChickenItem extends Item implements IBauble
{
    public static final double MAX_VALUE = 0.44D;

    public ChickenItem()
    {
        this.setRegistryName("chicken_amulet");
        this.setUnlocalizedName("chicken_amulet");

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
        if (player.getEntityWorld().isRemote) return;

        if (this.getLastCreationTime(itemstack) + 6000 > player.getEntityWorld().getTotalWorldTime()) return;

        if (player instanceof EntityPlayer)
        {
            EntityPlayer entityPlayer = ((EntityPlayer) player);
            ItemStack itemStack = entityPlayer.getHeldItemOffhand();

            if (!itemStack.getItem().equals(Items.WHEAT_SEEDS)) return;

            int amount = itemStack.getCount();
            Random random = new Random();
            double rolled = random.nextDouble() * MAX_VALUE;

            ItemStack rolledStack = Items.AIR.getDefaultInstance();
            Egg[] values = Egg.values();
            for (Egg egg : values)
            {
                rolled -= egg.chance;
                if (rolled <= 0)
                {
                    rolledStack = egg.stack;
                    break;
                }
            }

            for (int i = 0; i < amount; i++)
            {
                itemStack.setCount(itemStack.getCount() - 1);
                this.setConsumedAmount(itemstack, this.getConsumedAmount(itemstack) + 1);

                if (this.getConsumedAmount(itemstack) >= 64)
                {
                    EntityItem item = new EntityItem(player.getEntityWorld(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), rolledStack);
                    player.getEntityWorld().spawnEntity(item);
                    this.setLastCreationTime(itemstack, player.world.getTotalWorldTime());
                    break;
                }
            }
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

    @Override
    public void addInformation(ItemStack stack, World p_77624_2_, List<String> strings,
                               ITooltipFlag p_77624_4_)
    {
        super.addInformation(stack, p_77624_2_, strings, p_77624_4_);
    }

    public void setLastCreationTime(ItemStack stack, long time)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
            tag = new NBTTagCompound();
        tag.setLong("time", time);
    }

    public long getLastCreationTime(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
        {
            stack.setTagCompound(new NBTTagCompound());
            return 0;
        }
        return tag.getLong("time");
    }

    public void setConsumedAmount(ItemStack stack, int amount)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
            tag = new NBTTagCompound();
        tag.setInteger("consumed", amount);
    }

    public int getConsumedAmount(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
        {
            stack.setTagCompound(new NBTTagCompound());
            return 0;
        }
        return tag.getInteger("consumed");
    }

    public enum Egg
    {
        ROTTEN_EGG(new ItemStack(IafItemRegistry.rotten_egg), 0.10D),
        SPIDER_EGG(new ItemStack(PrimitiveMobsItems.SPIDER_EGG), 0.10D),
        DODO_EGG(new ItemStack(PrimitiveMobsItems.DODO_EGG), 0.05D),
        INGREDIENT(new ItemStack(ModItems.ingredients, 1, 11), 0.04D),
        MYSTERY_EGG_1(new ItemStack(PrimitiveMobsItems.MYSTERYEGG1), 0.02D),
        MYSTERY_EGG_2(new ItemStack(PrimitiveMobsItems.MYSTERYEGG2), 0.01D),
        MYSTERY_EGG_3(new ItemStack(PrimitiveMobsItems.MYSTERYEGG3), 0.02D),
        DEATHWORM_EGG(new ItemStack(IafItemRegistry.deathworm_egg), 0.02D),
        HIPPOGRYPH_EGG(new ItemStack(IafItemRegistry.hippogryph_egg), 0.02D),
        MYRMEX_DESERT_EGG(new ItemStack(IafItemRegistry.myrmex_desert_egg), 0.03D),
        MYRMEX_JUNGLE_EGG(new ItemStack(IafItemRegistry.myrmex_jungle_egg), 0.03D),
        DRAGON_AMETHYST_EGG(new ItemStack(IafItemRegistry.dragonegg_amethyst), 0.01D),
        DRAGON_RED_EGG(new ItemStack(IafItemRegistry.dragonegg_red), 0.01D),
        DRAGON_SAPPHIRE_EGG(new ItemStack(IafItemRegistry.dragonegg_sapphire), 0.01D);

        ItemStack stack;
        double chance;
        Egg(ItemStack stack, double chance)
        {
            this.stack = stack;
            this.chance = chance;
        }
    }
}
