/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Posc;
import mindustry.gen.Velc;

public interface Boundedc
extends Healthc,
Entityc,
Posc,
Flyingc,
Velc,
Hitboxc {
    @Override
    public void update();
}

