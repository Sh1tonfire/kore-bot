/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;

public interface Teamc
extends Entityc,
Posc {
    public boolean cheating();

    public Building core();

    public Building closestCore();

    public Building closestEnemyCore();

    public Team team();

    public void team(Team var1);
}

