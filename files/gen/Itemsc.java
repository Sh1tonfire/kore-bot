/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.type.Item;
import mindustry.type.ItemStack;

public interface Itemsc
extends Entityc,
Posc {
    public int itemCapacity();

    @Override
    public void update();

    public Item item();

    public void clearItem();

    public boolean acceptsItem(Item var1);

    public boolean hasItem();

    public void addItem(Item var1);

    public void addItem(Item var1, int var2);

    public int maxAccepted(Item var1);

    public ItemStack stack();

    public void stack(ItemStack var1);

    public float itemTime();

    public void itemTime(float var1);
}

