/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Position;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.Effect;
import mindustry.gen.EffectState;
import mindustry.gen.EffectStatec;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullEffectState
extends EffectState
implements EffectStatec {
    NullEffectState() {
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
    public final float clipSize() {
        return 0.0f;
    }

    @Override
    public final Color color() {
        return new Color(Color.white);
    }

    @Override
    public final void color(Color color) {
    }

    @Override
    public final Object data() {
        return null;
    }

    @Override
    public final void data(Object data) {
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
    public final Effect effect() {
        return null;
    }

    @Override
    public final void effect(Effect effect) {
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
    public final float offsetX() {
        return 0.0f;
    }

    @Override
    public final void offsetX(float offsetX) {
    }

    @Override
    public final float offsetY() {
        return 0.0f;
    }

    @Override
    public final void offsetY(float offsetY) {
    }

    @Override
    public final boolean onSolid() {
        return false;
    }

    @Override
    public final Posc parent() {
        return null;
    }

    @Override
    public final void parent(Posc parent) {
    }

    @Override
    public final void read(Reads read) {
    }

    @Override
    public final void remove() {
    }

    @Override
    public final float rotation() {
        return 0.0f;
    }

    @Override
    public final void rotation(float rotation) {
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

