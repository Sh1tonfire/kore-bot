/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.geom.Position;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Entityc;
import mindustry.gen.Hitboxc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullHitbox
implements Hitboxc {
    NullHitbox() {
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
    public final boolean collides(Hitboxc other) {
        return false;
    }

    @Override
    public final void collision(Hitboxc other, float x, float y) {
    }

    @Override
    public final float deltaAngle() {
        return 0.0f;
    }

    @Override
    public final float deltaLen() {
        return 0.0f;
    }

    @Override
    public final float deltaX() {
        return 0.0f;
    }

    @Override
    public final void deltaX(float deltaX) {
    }

    @Override
    public final float deltaY() {
        return 0.0f;
    }

    @Override
    public final void deltaY(float deltaY) {
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
    public final void getCollisions(Cons<QuadTree> consumer) {
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
    public final float hitSize() {
        return 0.0f;
    }

    @Override
    public final void hitSize(float hitSize) {
    }

    @Override
    public final void hitbox(Rect arg0) {
    }

    @Override
    public final void hitboxTile(Rect rect) {
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
    public final float lastX() {
        return 0.0f;
    }

    @Override
    public final void lastX(float lastX) {
    }

    @Override
    public final float lastY() {
        return 0.0f;
    }

    @Override
    public final void lastY(float lastY) {
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
    public final void updateLastPosition() {
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

