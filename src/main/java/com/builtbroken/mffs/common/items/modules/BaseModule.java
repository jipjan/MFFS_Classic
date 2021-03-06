package com.builtbroken.mffs.common.items.modules;

import com.builtbroken.mc.core.registry.implement.IRecipeContainer;
import com.builtbroken.mc.prefab.inventory.InventoryUtility;
import com.builtbroken.mffs.api.IFieldInteraction;
import com.builtbroken.mffs.api.IProjector;
import com.builtbroken.mffs.api.modules.IFieldModule;
import com.builtbroken.mffs.api.utils.UnitDisplay;
import com.builtbroken.mffs.api.utils.Util;
import com.builtbroken.mffs.api.vector.Vector3D;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

/**
 * @author Calclavia
 */
public abstract class BaseModule extends Item implements IFieldModule, IRecipeContainer
{

    /* Current fortronCost */
    private float fortronCost = 0.5F;

    /**
     * allows items to add custom lines of information to the mouseover description
     *
     * @param stack
     * @param usr
     * @param list
     * @param dummy
     */
    @Override
    public void addInformation(ItemStack stack, EntityPlayer usr, List list, boolean dummy)
    {
        list.add(EnumChatFormatting.BLUE + LanguageRegistry.instance().getStringLocalization("info.item.fortron") + " " + UnitDisplay.getDisplay(getFortronCost(1.0F) * 20.0F, UnitDisplay.Unit.LITER) + "/s");

        String tooltip = LanguageRegistry.instance().getStringLocalization(getUnlocalizedName() + ".tooltip");
        if (tooltip != null && tooltip.length() > 0)
        {
            list.addAll(Util.sepString(tooltip, 30));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
    {
        //Cache module name so item can be merged into one item at some point
        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        if (!stack.getTagCompound().hasKey("module"))
        {
            String key = InventoryUtility.getRegistryName(stack.getItem());
            if (key != null)
            {
                key = key.replace("mffs:", "");
                stack.getTagCompound().setString("module", key);
            }
        }
    }

    /**
     * @param projector
     * @param position
     * @return
     */
    @Override
    public Set<Vector3D> onPreCalculate(IFieldInteraction projector, Set<Vector3D> position)
    {

        return position;
    }


    /**
     * Calculates the projection field.
     *
     * @param projector The projector interface.
     * @param position  A set of positions.
     */
    @Override
    public void onCalculate(IFieldInteraction projector, Set<Vector3D> position)
    {
    }


    /**
     * Calls this on projection.
     *
     * @param projector The projector interface.
     * @param fields    A set of fields that are projected.
     * @return
     */
    @Override
    public boolean prePlaceFieldBlock(IProjector projector, Set<Vector3D> fields)
    {
        return false;
    }


    @Override
    public int prePlaceFieldBlock(IProjector projector, Vector3D position)
    {
        return 0;
    }

    @Override
    public boolean onCollideWithForcefield(World world, int x, int y, int z, Entity entity, ItemStack moduleStack)
    {
        return false;
    }

    /**
     * @param cost
     * @return
     */
    public BaseModule setCost(float cost)
    {
        this.fortronCost = cost;
        return this;
    }

    @Override
    public int getFortronCost(float amplifier)
    {
        return Math.round(this.fortronCost);
    }

    @Override
    public boolean onDestroy(IProjector projector, Set<Vector3D> field)
    {
        return false;
    }

    @Override
    public boolean doesRequireUpdate(ItemStack moduleStack)
    {
        return false;
    }
}
