/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Position;
import arc.util.Interval;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.nio.FloatBuffer;
import mindustry.entities.units.UnitCommand;
import mindustry.game.Team;
import mindustry.gen.Entityc;
import mindustry.gen.Player;
import mindustry.gen.Playerc;
import mindustry.gen.Unit;
import mindustry.net.Administration;
import mindustry.net.NetConnection;
import mindustry.net.Packets;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.storage.CoreBlock;

final class NullPlayer
extends Player
implements Playerc {
    NullPlayer() {
    }

    @Override
    public final <T> T as() {
        return null;
    }

    @Override
    public final <T extends Entityc> T self() {
        return null;
    }

    @Override
    public final <T> T with(Cons<T> cons) {
        return null;
    }

    @Override
    public final void add() {
    }

    @Override
    public final boolean admin() {
        return false;
    }

    @Override
    public final void admin(boolean admin) {
    }

    @Override
    public final void afterRead() {
    }

    @Override
    public final void afterSync() {
    }

    @Override
    public final float angleTo(Position other) {
        return 0.0f;
    }

    @Override
    public final float angleTo(float x, float y) {
        return 0.0f;
    }

    @Override
    public final CoreBlock.CoreBuild bestCore() {
        return null;
    }

    @Override
    public final Block blockOn() {
        return null;
    }

    @Override
    public final boolean boosting() {
        return false;
    }

    @Override
    public final void boosting(boolean boosting) {
    }

    @Override
    public final int classId() {
        return 0;
    }

    @Override
    public final void clearUnit() {
    }

    @Override
    public final float clipSize() {
        return 0.0f;
    }

    @Override
    public final CoreBlock.CoreBuild closestCore() {
        return null;
    }

    @Override
    public final Color color() {
        return new Color();
    }

    @Override
    public final void color(Color color) {
    }

    @Override
    public final void command(UnitCommand command) {
    }

    @Override
    public final NetConnection con() {
        return null;
    }

    @Override
    public final void con(NetConnection con) {
    }

    @Override
    public final CoreBlock.CoreBuild core() {
        return null;
    }

    @Override
    public final boolean dead() {
        return false;
    }

    @Override
    public final float deathTimer() {
        return 0.0f;
    }

    @Override
    public final void deathTimer(float deathTimer) {
    }

    @Override
    public final boolean displayAmmo() {
        return false;
    }

    @Override
    public final void draw() {
    }

    @Override
    public final float dst(Position other) {
        return 0.0f;
    }

    @Override
    public final float dst(float x, float y) {
        return 0.0f;
    }

    @Override
    public final float dst2(Position other) {
        return 0.0f;
    }

    @Override
    public final float dst2(float x, float y) {
        return 0.0f;
    }

    @Override
    public final Floor floorOn() {
        return null;
    }

    @Override
    public final Administration.PlayerInfo getInfo() {
        return null;
    }

    @Override
    public final float getX() {
        return 0.0f;
    }

    @Override
    public final float getY() {
        return 0.0f;
    }

    @Override
    public final TextureRegion icon() {
        return null;
    }

    @Override
    public final int id() {
        return -1;
    }

    @Override
    public final void id(int id) {
    }

    @Override
    public final void interpolate() {
    }

    @Override
    public final String ip() {
        return null;
    }

    @Override
    public final boolean isAdded() {
        return false;
    }

    @Override
    public final boolean isBeingControlled(Unit player) {
        return false;
    }

    @Override
    public final boolean isBuilder() {
        return false;
    }

    @Override
    public final boolean isLocal() {
        return false;
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final boolean isRemote() {
        return false;
    }

    @Override
    public final boolean isValidController() {
        return false;
    }

    @Override
    public final void kick(String reason) {
    }

    @Override
    public final void kick(String reason, int duration) {
    }

    @Override
    public final void kick(Packets.KickReason reason) {
    }

    @Override
    public final String lastText() {
        return "";
    }

    @Override
    public final void lastText(String lastText) {
    }

    @Override
    public final long lastUpdated() {
        return 0L;
    }

    @Override
    public final void lastUpdated(long lastUpdated) {
    }

    @Override
    public final String locale() {
        return "en";
    }

    @Override
    public final void locale(String locale) {
    }

    @Override
    public final float mouseX() {
        return 0.0f;
    }

    @Override
    public final void mouseX(float mouseX) {
    }

    @Override
    public final float mouseY() {
        return 0.0f;
    }

    @Override
    public final void mouseY(float mouseY) {
    }

    @Override
    public final String name() {
        return "noname";
    }

    @Override
    public final void name(String name) {
    }

    @Override
    public final boolean onSolid() {
        return false;
    }

    @Override
    public final void read(Reads read) {
    }

    @Override
    public final void readSync(Reads read) {
    }

    @Override
    public final void readSyncManual(FloatBuffer buffer) {
    }

    @Override
    public final void remove() {
    }

    @Override
    public final void removed(Unit unit) {
    }

    @Override
    public final void reset() {
    }

    @Override
    public final void sendMessage(String text) {
    }

    @Override
    public final void sendMessage(String text, Player from) {
    }

    @Override
    public final void sendMessage(String text, Player from, String fromName) {
    }

    @Override
    public final boolean serialize() {
        return false;
    }

    @Override
    public final void set(Position pos) {
    }

    @Override
    public final void set(float x, float y) {
    }

    @Override
    public final boolean shooting() {
        return false;
    }

    @Override
    public final void shooting(boolean shooting) {
    }

    @Override
    public final void snapInterpolation() {
    }

    @Override
    public final void snapSync() {
    }

    @Override
    public final Team team() {
        return Team.sharded;
    }

    @Override
    public final void team(Team team) {
    }

    @Override
    public final float textFadeTime() {
        return 0.0f;
    }

    @Override
    public final void textFadeTime(float textFadeTime) {
    }

    @Override
    public final Tile tileOn() {
        return null;
    }

    @Override
    public final int tileX() {
        return 0;
    }

    @Override
    public final int tileY() {
        return 0;
    }

    @Override
    public final Interval timer() {
        return new Interval(6);
    }

    @Override
    public final void timer(Interval timer) {
    }

    @Override
    public final boolean timer(int index, float time) {
        return false;
    }

    @Override
    public final void trns(Position pos) {
    }

    @Override
    public final void trns(float x, float y) {
    }

    @Override
    public final boolean typing() {
        return false;
    }

    @Override
    public final void typing(boolean typing) {
    }

    @Override
    public final Unit unit() {
        return null;
    }

    @Override
    public final void unit(Unit unit) {
    }

    @Override
    public final void update() {
    }

    @Override
    public final long updateSpacing() {
        return 0L;
    }

    @Override
    public final void updateSpacing(long updateSpacing) {
    }

    @Override
    public final void updateUnit() {
    }

    @Override
    public final String usid() {
        return null;
    }

    @Override
    public final String uuid() {
        return null;
    }

    @Override
    public final boolean within(Position other, float dst) {
        return false;
    }

    @Override
    public final boolean within(float x, float y, float dst) {
        return false;
    }

    @Override
    public final void write(Writes write) {
    }

    @Override
    public final void writeSync(Writes write) {
    }

    @Override
    public final void writeSyncManual(FloatBuffer buffer) {
    }

    @Override
    public final float x() {
        return 0.0f;
    }

    @Override
    public final void x(float x) {
    }

    @Override
    public final float y() {
        return 0.0f;
    }

    @Override
    public final void y(float y) {
    }
}

