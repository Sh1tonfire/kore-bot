/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.geom.Position;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Entityc;
import mindustry.gen.Puddle;
import mindustry.gen.Puddlec;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullPuddle
extends Puddle
implements Puddlec {
    NullPuddle() {
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
    public final float accepting() {
        return 0.0f;
    }

    @Override
    public final void accepting(float accepting) {
    }

    @Override
    public final void add() {
    }

    @Override
    public final void afterRead() {
    }

    @Override
    public final float amount() {
        return 0.0f;
    }

    @Override
    public final void amount(float amount) {
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
    public final int generation() {
        return 0;
    }

    @Override
    public final void generation(int generation) {
    }

    @Override
    public final float getFlammability() {
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
    public final float lastRipple() {
        return 0.0f;
    }

    @Override
    public final void lastRipple(float lastRipple) {
    }

    @Override
    public final Liquid liquid() {
        return null;
    }

    @Override
    public final void liquid(Liquid liquid) {
    }

    @Override
    public final boolean onSolid() {
        return false;
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
    public final void set(Position pos) {
    }

    @Override
    public final void set(float x, float y) {
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
    public final void trns(Position pos) {
    }

    @Override
    public final void trns(float x, float y) {
    }

    @Override
    public final void update() {
    }

    @Override
    public final float updateTime() {
        return 0.0f;
    }

    @Override
    public final void updateTime(float updateTime) {
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

