/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.Color;
import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Posc;
import mindustry.gen.Velc;
import mindustry.type.StatusEffect;

public interface Statusc
extends Healthc,
Entityc,
Posc,
Flyingc,
Velc,
Hitboxc {
    public void apply(StatusEffect var1);

    public void apply(StatusEffect var1, float var2);

    public void clearStatuses();

    public void unapply(StatusEffect var1);

    public boolean isBoss();

    public boolean isImmune(StatusEffect var1);

    public Color statusColor();

    @Override
    public void update();

    public void draw();

    public boolean hasEffect(StatusEffect var1);

    public float speedMultiplier();

    public float damageMultiplier();

    public float healthMultiplier();

    public float reloadMultiplier();
}

