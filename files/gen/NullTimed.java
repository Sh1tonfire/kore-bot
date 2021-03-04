/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.Interp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Entityc;
import mindustry.gen.Timedc;

final class NullTimed
implements Timedc {
    NullTimed() {
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
    public final int classId() {
        return 0;
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
    public final int id() {
        return -1;
    }

    @Override
    public final void id(int id) {
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
    public final float lifetime() {
        return 0.0f;
    }

    @Override
    public final void lifetime(float lifetime) {
    }

    @Override
    public final void read(Reads read) {
    }

    @Override
    public final void remove() {
    }

    @Override
    public final boolean serialize() {
        return false;
    }

    @Override
    public final float time() {
        return 0.0f;
    }

    @Override
    public final void time(float time) {
    }

    @Override
    public final void update() {
    }

    @Override
    public final void write(Writes write) {
    }
}

