/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Position;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Statusc;
import mindustry.gen.Teamc;
import mindustry.gen.Velc;
import mindustry.type.UnitType;

public interface Weaponsc
extends Healthc,
Entityc,
Posc,
Flyingc,
Teamc,
Velc,
Rotc,
Hitboxc,
Statusc {
    public float ammof();

    public void setWeaponRotation(float var1);

    public void setupWeapons(UnitType var1);

    public void controlWeapons(boolean var1);

    public void controlWeapons(boolean var1, boolean var2);

    public void aim(Position var1);

    public void aim(float var1, float var2);

    public boolean canShoot();

    @Override
    public void remove();

    @Override
    public void update();

    public WeaponMount[] mounts();

    public void mounts(WeaponMount[] var1);

    public boolean isRotate();

    public float aimX();

    public void aimX(float var1);

    public float aimY();

    public void aimY(float var1);

    public boolean isShooting();

    public void isShooting(boolean var1);

    public float ammo();

    public void ammo(float var1);
}

