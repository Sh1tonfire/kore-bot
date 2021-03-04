/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.Interp;
import arc.math.geom.Position;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.struct.IntSeq;
import arc.util.Interval;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.EntityCollisions;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Bulletc;
import mindustry.gen.Entityc;
import mindustry.gen.Hitboxc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullBullet
extends Bullet
implements Bulletc {
    NullBullet() {
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
    public final void absorb() {
    }

    @Override
    public final boolean absorbed() {
        return false;
    }

    @Override
    public final void absorbed(boolean absorbed) {
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
    public final boolean canPass(int tileX, int tileY) {
        return false;
    }

    @Override
    public final boolean canPassOn() {
        return false;
    }

    @Override
    public final boolean cheating() {
        return false;
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
    public final Building closestCore() {
        return null;
    }

    @Override
    public final Building closestEnemyCore() {
        return null;
    }

    @Override
    public final IntSeq collided() {
        return new IntSeq(6);
    }

    @Override
    public final void collided(IntSeq collided) {
    }

    @Override
    public final boolean collides(Hitboxc other) {
        return false;
    }

    @Override
    public final void collision(Hitboxc other, float x, float y) {
    }

    @Override
    public final Building core() {
        return null;
    }

    @Override
    public final float damage() {
        return 0.0f;
    }

    @Override
    public final void damage(float damage) {
    }

    @Override
    public final float damageMultiplier() {
        return 0.0f;
    }

    @Override
    public final Object data() {
        return null;
    }

    @Override
    public final void data(Object data) {
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
    public final float drag() {
        return 0.0f;
    }

    @Override
    public final void drag(float drag) {
    }

    @Override
    public final void draw() {
    }

    @Override
    public final void drawBullets() {
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
    public final float fdata() {
        return 0.0f;
    }

    @Override
    public final void fdata(float fdata) {
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
    public final boolean hit() {
        return false;
    }

    @Override
    public final void hit(boolean hit) {
    }

    @Override
    public final float hitSize() {
        return 0.0f;
    }

    @Override
    public final void hitSize(float hitSize) {
    }

    @Override
    public final void hitbox(Rect rect) {
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
    public final float lifetime() {
        return 0.0f;
    }

    @Override
    public final void lifetime(float lifetime) {
    }

    @Override
    public final void move(float cx, float cy) {
    }

    @Override
    public final boolean moving() {
        return false;
    }

    @Override
    public final boolean onSolid() {
        return false;
    }

    @Override
    public final Entityc owner() {
        return null;
    }

    @Override
    public final void owner(Entityc owner) {
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
    public final void rotation(float angle) {
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
    public final EntityCollisions.SolidPred solidity() {
        return null;
    }

    @Override
    public final Team team() {
        return Team.derelict;
    }

    @Override
    public final void team(Team team) {
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
    public final BulletType type() {
        return null;
    }

    @Override
    public final void type(BulletType type) {
    }

    @Override
    public final void update() {
    }

    @Override
    public final void updateLastPosition() {
    }

    @Override
    public final Vec2 vel() {
        return new Vec2();
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

