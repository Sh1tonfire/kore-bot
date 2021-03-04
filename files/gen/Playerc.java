/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.util.Nullable;
import mindustry.entities.units.UnitController;
import mindustry.game.Team;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Player;
import mindustry.gen.Posc;
import mindustry.gen.Syncc;
import mindustry.gen.Timerc;
import mindustry.gen.Unit;
import mindustry.net.Administration;
import mindustry.net.NetConnection;
import mindustry.net.Packets;
import mindustry.world.blocks.storage.CoreBlock;

public interface Playerc
extends UnitController,
Entityc,
Drawc,
Posc,
Timerc,
Syncc {
    public boolean isBuilder();

    public CoreBlock.CoreBuild closestCore();

    public CoreBlock.CoreBuild core();

    public CoreBlock.CoreBuild bestCore();

    public TextureRegion icon();

    public boolean displayAmmo();

    public void reset();

    @Override
    public boolean isValidController();

    @Override
    public float clipSize();

    @Override
    public void afterSync();

    @Override
    public void update();

    @Override
    public void remove();

    public void team(Team var1);

    public void clearUnit();

    @Override
    public Unit unit();

    @Override
    public void unit(Unit var1);

    public boolean dead();

    public String ip();

    public String uuid();

    public String usid();

    public void kick(Packets.KickReason var1);

    public void kick(String var1);

    public void kick(String var1, int var2);

    @Override
    public void draw();

    public void sendMessage(String var1);

    public void sendMessage(String var1, Player var2);

    public void sendMessage(String var1, Player var2, String var3);

    public Administration.PlayerInfo getInfo();

    @Nullable
    public NetConnection con();

    public void con(@Nullable NetConnection var1);

    public Team team();

    public boolean typing();

    public void typing(boolean var1);

    public boolean shooting();

    public void shooting(boolean var1);

    public boolean boosting();

    public void boosting(boolean var1);

    public boolean admin();

    public void admin(boolean var1);

    public float mouseX();

    public void mouseX(float var1);

    public float mouseY();

    public void mouseY(float var1);

    public String name();

    public void name(String var1);

    public Color color();

    public void color(Color var1);

    public String locale();

    public void locale(String var1);

    public float deathTimer();

    public void deathTimer(float var1);

    public String lastText();

    public void lastText(String var1);

    public float textFadeTime();

    public void textFadeTime(float var1);
}

