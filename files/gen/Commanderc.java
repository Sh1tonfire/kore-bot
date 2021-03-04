/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Boolf;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.ai.formations.Formation;
import mindustry.ai.formations.FormationPattern;
import mindustry.entities.units.UnitController;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Unit;

public interface Commanderc
extends Entityc,
Posc {
    @Override
    public void update();

    @Override
    public void remove();

    public void killed();

    public void controller(UnitController var1);

    public void commandNearby(FormationPattern var1);

    public void commandNearby(FormationPattern var1, Boolf<Unit> var2);

    public void command(Formation var1, Seq<Unit> var2);

    public boolean isCommanding();

    public void clearCommand();

    @Nullable
    public Formation formation();

    public void formation(@Nullable Formation var1);

    public Seq<Unit> controlling();

    public void controlling(Seq<Unit> var1);

    public float minFormationSpeed();

    public void minFormationSpeed(float var1);
}

