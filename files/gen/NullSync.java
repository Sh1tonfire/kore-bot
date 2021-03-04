/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.nio.FloatBuffer;
import mindustry.gen.Entityc;
import mindustry.gen.Syncc;

final class NullSync
implements Syncc {
    NullSync() {
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
    public final int classId() {
        return 0;
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
    public final void snapInterpolation() {
    }

    @Override
    public final void snapSync() {
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
    public final void write(Writes write) {
    }

    @Override
    public final void writeSync(Writes write) {
    }

    @Override
    public final void writeSyncManual(FloatBuffer buffer) {
    }
}

