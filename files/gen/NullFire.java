/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.Interp;
import arc.math.geom.Position;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.nio.FloatBuffer;
import mindustry.gen.Entityc;
import mindustry.gen.Fire;
import mindustry.gen.Firec;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullFire
extends Fire
implements Firec {
    NullFire() {
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
    public final Block blockOn() {
        return null;
    }

    @Override
    public final int classId() {
        return 0;
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
    public final float fin() {
        return 0.0f;
    }

    @Override
    public final float fin(Interp i) {
        return 0.0f;
    }

    @Override
    public final float finpow() {
        return 0.0f;
    }

    @Override
    public final Floor floorOn() {
        return null;
    }

    @Override
    public final float fout() {
        return 0.0f;
    }

    @Override
    public final float fout(Interp i) {
        return 0.0f;
    }

    @Override
    public final float fout(float margin) {
        return 0.0f;
    }

    @Override
    public final float fslope() {
        return 0.0f;
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
    public final boolean isAdded() {
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
    public final long lastUpdated() {
        return 0L;
    }

    @Override
    public final void lastUpdated(long lastUpdated) {
    }

    @Override
    public final float lifetime() {
        return 0.0f;
    }

    @Override
    public final void lifetime(float lifetime) {
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
    public final void snapInterpolation() {
    }

    @Override
    public final void snapSync() {
    }

    @Override
    public final Tile tile() {
        return null;
    }

    @Override
    public final void tile(Tile tile) {
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
    public final float time() {
        return 0.0f;
    }

    @Override
    public final void time(float time) {
    }

    @Override
    public final void trns(Position pos) {
    }

    @Override
    public final void trns(float x, float y) {
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

