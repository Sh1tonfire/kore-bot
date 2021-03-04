/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.geom.Position;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Entityc;
import mindustry.gen.Shieldc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullShield
implements Shieldc {
    NullShield() {
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
    public final float armor() {
        return 0.0f;
    }

    @Override
    public final void armor(float armor) {
    }

    @Override
    public final Block blockOn() {
        return null;
    }

    @Override
    public final void clampHealth() {
    }

    @Override
    public final int classId() {
        return 0;
    }

    @Override
    public final void damage(float amount) {
    }

    @Override
    public final void damage(float amount, boolean withEffect) {
    }

    @Override
    public final void damageContinuous(float amount) {
    }

    @Override
    public final void damageContinuousPierce(float amount) {
    }

    @Override
    public final void damagePierce(float amount) {
    }

    @Override
    public final void damagePierce(float amount, boolean withEffect) {
    }

    @Override
    public final boolean damaged() {
        return false;
    }

    @Override
    public final boolean dead() {
        return false;
    }

    @Override
    public final void dead(boolean dead) {
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
    public final float getX() {
        return 0.0f;
    }

    @Override
    public final float getY() {
        return 0.0f;
    }

    @Override
    public final void heal() {
    }

    @Override
    public final void heal(float amount) {
    }

    @Override
    public final void healFract(float amount) {
    }

    @Override
    public final float health() {
        return 0.0f;
    }

    @Override
    public final void health(float health) {
    }

    @Override
    public final float healthf() {
        return 0.0f;
    }

    @Override
    public final float hitTime() {
        return 0.0f;
    }

    @Override
    public final void hitTime(float hitTime) {
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
    public final boolean isValid() {
        return false;
    }

    @Override
    public final void kill() {
    }

    @Override
    public final void killed() {
    }

    @Override
    public final float maxHealth() {
        return 1.0f;
    }

    @Override
    public final void maxHealth(float maxHealth) {
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
    public final float shield() {
        return 0.0f;
    }

    @Override
    public final void shield(float shield) {
    }

    @Override
    public final float shieldAlpha() {
        return 0.0f;
    }

    @Override
    public final void shieldAlpha(float shieldAlpha) {
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

