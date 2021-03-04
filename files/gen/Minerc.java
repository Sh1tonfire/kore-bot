/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.util.Nullable;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Itemsc;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Teamc;
import mindustry.type.Item;
import mindustry.world.Tile;

public interface Minerc
extends Entityc,
Drawc,
Posc,
Teamc,
Rotc,
Itemsc {
    public boolean canMine(Item var1);

    public boolean offloadImmediately();

    public boolean mining();

    public boolean validMine(Tile var1, boolean var2);

    public boolean validMine(Tile var1);

    public boolean canMine();

    @Override
    public void update();

    @Override
    public void draw();

    public float mineTimer();

    public void mineTimer(float var1);

    @Nullable
    public Tile mineTile();

    public void mineTile(@Nullable Tile var1);
}

