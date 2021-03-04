/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Vec2;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Syncc;
import mindustry.type.Weather;

public interface WeatherStatec
extends Entityc,
Drawc,
Posc,
Syncc {
    public void init(Weather var1);

    @Override
    public void update();

    @Override
    public void draw();

    public Weather weather();

    public void weather(Weather var1);

    public float intensity();

    public void intensity(float var1);

    public float opacity();

    public void opacity(float var1);

    public float life();

    public void life(float var1);

    public float effectTimer();

    public void effectTimer(float var1);

    public Vec2 windVector();

    public void windVector(Vec2 var1);
}

