/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.struct.Queue;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Teamc;

public interface Builderc
extends Entityc,
Posc,
Teamc,
Rotc {
    public boolean canBuild();

    @Override
    public void update();

    public void drawBuildPlans();

    public void drawPlan(BuildPlan var1, float var2);

    public void drawPlanTop(BuildPlan var1, float var2);

    public boolean shouldSkip(BuildPlan var1, Building var2);

    public void removeBuild(int var1, int var2, boolean var3);

    public boolean isBuilding();

    public void clearBuilding();

    public void addBuild(BuildPlan var1);

    public void addBuild(BuildPlan var1, boolean var2);

    public boolean activelyBuilding();

    public BuildPlan buildPlan();

    public void draw();

    public Queue<BuildPlan> plans();

    public void plans(Queue<BuildPlan> var1);

    public boolean updateBuilding();

    public void updateBuilding(boolean var1);
}

