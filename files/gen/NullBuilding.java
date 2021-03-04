/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Graphics;
import arc.func.Cons;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Position;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.ctype.Content;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Buildingc;
import mindustry.gen.Bullet;
import mindustry.gen.Entityc;
import mindustry.gen.Player;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.logic.LAccess;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.meta.BlockStatus;
import mindustry.world.modules.ConsumeModule;
import mindustry.world.modules.ItemModule;
import mindustry.world.modules.LiquidModule;
import mindustry.world.modules.PowerModule;

final class NullBuilding
extends Building
implements Buildingc {
    NullBuilding() {
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
    public final boolean acceptItem(Building source, Item item) {
        return false;
    }

    @Override
    public final boolean acceptLiquid(Building source, Liquid liquid) {
        return false;
    }

    @Override
    public final boolean acceptPayload(Building source, Payload payload) {
        return false;
    }

    @Override
    public final int acceptStack(Item item, int amount, Teamc source) {
        return 0;
    }

    @Override
    public final void add() {
    }

    @Override
    public final void addPlan(boolean checkPrevious) {
    }

    @Override
    public final void afterRead() {
    }

    @Override
    public final float ambientVolume() {
        return 0.0f;
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
    public final void applyBoost(float intensity, float duration) {
    }

    @Override
    public final Building back() {
        return null;
    }

    @Override
    public final Block block() {
        return null;
    }

    @Override
    public final void block(Block block) {
    }

    @Override
    public final Block blockOn() {
        return null;
    }

    @Override
    public final void buildConfiguration(Table table) {
    }

    @Override
    public final boolean canDump(Building to, Item item) {
        return false;
    }

    @Override
    public final boolean canDumpLiquid(Building to, Liquid liquid) {
        return false;
    }

    @Override
    public final boolean canPickup() {
        return false;
    }

    @Override
    public final boolean canUnload() {
        return false;
    }

    @Override
    public final byte cdump() {
        return 0;
    }

    @Override
    public final void cdump(byte cdump) {
    }

    @Override
    public final boolean cheating() {
        return false;
    }

    @Override
    public final boolean checkSolid() {
        return false;
    }

    @Override
    public final void clampHealth() {
    }

    @Override
    public final int classId() {
        return 0;
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
    public final boolean collide(Bullet other) {
        return false;
    }

    @Override
    public final boolean collision(Bullet other) {
        return false;
    }

    @Override
    public final boolean conductsTo(Building other) {
        return false;
    }

    @Override
    public final Object config() {
        return null;
    }

    @Override
    public final boolean configTapped() {
        return false;
    }

    @Override
    public final void configure(Object value) {
    }

    @Override
    public final void configureAny(Object value) {
    }

    @Override
    public final void configured(Unit builder, Object value) {
    }

    @Override
    public final ConsumeModule cons() {
        return null;
    }

    @Override
    public final void cons(ConsumeModule cons) {
    }

    @Override
    public final boolean consValid() {
        return false;
    }

    @Override
    public final void consume() {
    }

    @Override
    public final void control(LAccess type, double p1, double p2, double p3, double p4) {
    }

    @Override
    public final void control(LAccess type, Object p1, double p2, double p3, double p4) {
    }

    @Override
    public final Building core() {
        return null;
    }

    @Override
    public final Building create(Block block, Team team) {
        return null;
    }

    @Override
    public final void created() {
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
    public final float delta() {
        return 0.0f;
    }

    @Override
    public final void deselect() {
    }

    @Override
    public final void display(Table table) {
    }

    @Override
    public final void displayBars(Table table) {
    }

    @Override
    public final void displayConsumption(Table table) {
    }

    @Override
    public final void draw() {
    }

    @Override
    public final void drawConfigure() {
    }

    @Override
    public final void drawCracks() {
    }

    @Override
    public final void drawDisabled() {
    }

    @Override
    public final void drawLight() {
    }

    @Override
    public final void drawLiquidLight(Liquid liquid, float amount) {
    }

    @Override
    public final void drawSelect() {
    }

    @Override
    public final void drawStatus() {
    }

    @Override
    public final void drawTeam() {
    }

    @Override
    public final void drawTeamTop() {
    }

    @Override
    public final void dropped() {
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
    public final boolean dump() {
        return false;
    }

    @Override
    public final boolean dump(Item todump) {
        return false;
    }

    @Override
    public final void dumpLiquid(Liquid liquid) {
    }

    @Override
    public final void dumpLiquid(Liquid liquid, float scaling) {
    }

    @Override
    public final boolean dumpPayload(Payload todump) {
        return false;
    }

    @Override
    public final float edelta() {
        return 0.0f;
    }

    @Override
    public final float efficiency() {
        return 0.0f;
    }

    @Override
    public final boolean enabled() {
        return true;
    }

    @Override
    public final void enabled(boolean enabled) {
    }

    @Override
    public final float enabledControlTime() {
        return 0.0f;
    }

    @Override
    public final void enabledControlTime(float enabledControlTime) {
    }

    @Override
    public final Floor floor() {
        return null;
    }

    @Override
    public final Floor floorOn() {
        return null;
    }

    @Override
    public final Building front() {
        return null;
    }

    @Override
    public final Graphics.Cursor getCursor() {
        return null;
    }

    @Override
    public final float getDisplayEfficiency() {
        return 0.0f;
    }

    @Override
    public final TextureRegion getDisplayIcon() {
        return null;
    }

    @Override
    public final String getDisplayName() {
        return null;
    }

    @Override
    public final Building getLiquidDestination(Building from, Liquid liquid) {
        return null;
    }

    @Override
    public final int getMaximumAccepted(Item item) {
        return 0;
    }

    @Override
    public final Payload getPayload() {
        return null;
    }

    @Override
    public final Seq<Building> getPowerConnections(Seq<Building> out) {
        return null;
    }

    @Override
    public final float getPowerProduction() {
        return 0.0f;
    }

    @Override
    public final float getProgressIncrease(float baseTime) {
        return 0.0f;
    }

    @Override
    public final void getStackOffset(Item item, Vec2 trns) {
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
    public final float handleDamage(float amount) {
        return 0.0f;
    }

    @Override
    public final void handleItem(Building source, Item item) {
    }

    @Override
    public final void handleLiquid(Building source, Liquid liquid, float amount) {
    }

    @Override
    public final void handlePayload(Building source, Payload payload) {
    }

    @Override
    public final void handleStack(Item item, int amount, Teamc source) {
    }

    @Override
    public final void handleString(Object value) {
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
    public final float hitSize() {
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
    public final void hitbox(Rect arg0) {
    }

    @Override
    public final int id() {
        return -1;
    }

    @Override
    public final void id(int id) {
    }

    @Override
    public final void incrementDump(int prox) {
    }

    @Override
    public final Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {
        return null;
    }

    @Override
    public final boolean interactable(Team team) {
        return false;
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
    public final void itemTaken(Item item) {
    }

    @Override
    public final ItemModule items() {
        return null;
    }

    @Override
    public final void items(ItemModule items) {
    }

    @Override
    public final void kill() {
    }

    @Override
    public final void killed() {
    }

    @Override
    public final String lastAccessed() {
        return null;
    }

    @Override
    public final void lastAccessed(String lastAccessed) {
    }

    @Override
    public final Building left() {
        return null;
    }

    @Override
    public final LiquidModule liquids() {
        return null;
    }

    @Override
    public final void liquids(LiquidModule liquids) {
    }

    @Override
    public final float maxHealth() {
        return 1.0f;
    }

    @Override
    public final void maxHealth(float maxHealth) {
    }

    @Override
    public final boolean moveForward(Item item) {
        return false;
    }

    @Override
    public final float moveLiquid(Building next, Liquid liquid) {
        return 0.0f;
    }

    @Override
    public final float moveLiquidForward(boolean leaks, Liquid liquid) {
        return 0.0f;
    }

    @Override
    public final boolean movePayload(Payload todump) {
        return false;
    }

    @Override
    public final Building nearby(int rotation) {
        return null;
    }

    @Override
    public final Building nearby(int dx, int dy) {
        return null;
    }

    @Override
    public final void noSleep() {
    }

    @Override
    public final void offload(Item item) {
    }

    @Override
    public final boolean onConfigureTileTapped(Building other) {
        return false;
    }

    @Override
    public final void onDestroyed() {
    }

    @Override
    public final void onProximityAdded() {
    }

    @Override
    public final void onProximityRemoved() {
    }

    @Override
    public final void onProximityUpdate() {
    }

    @Override
    public final void onRemoved() {
    }

    @Override
    public final boolean onSolid() {
        return false;
    }

    @Override
    public final void overwrote(Seq<Building> previous) {
    }

    @Override
    public final void pickedUp() {
    }

    @Override
    public final void placed() {
    }

    @Override
    public final void playerPlaced(Object config) {
    }

    @Override
    public final int pos() {
        return 0;
    }

    @Override
    public final PowerModule power() {
        return null;
    }

    @Override
    public final void power(PowerModule power) {
    }

    @Override
    public final void powerGraphRemoved() {
    }

    @Override
    public final void produced(Item item) {
    }

    @Override
    public final void produced(Item item, int amount) {
    }

    @Override
    public final boolean productionValid() {
        return false;
    }

    @Override
    public final Seq<Building> proximity() {
        return new Seq<Building>(8);
    }

    @Override
    public final void proximity(Seq<Building> proximity) {
    }

    @Override
    public final boolean put(Item item) {
        return false;
    }

    @Override
    public final void read(Reads read) {
    }

    @Override
    public final void read(Reads read, byte revision) {
    }

    @Override
    public final void readAll(Reads read, byte revision) {
    }

    @Override
    public final void readBase(Reads read) {
    }

    @Override
    public final byte relativeTo(Building tile) {
        return 0;
    }

    @Override
    public final byte relativeTo(int cx, int cy) {
        return 0;
    }

    @Override
    public final byte relativeTo(Tile tile) {
        return 0;
    }

    @Override
    public final byte relativeToEdge(Tile other) {
        return 0;
    }

    @Override
    public final void remove() {
    }

    @Override
    public final void removeFromProximity() {
    }

    @Override
    public final int removeStack(Item item, int amount) {
        return 0;
    }

    @Override
    public final Building right() {
        return null;
    }

    @Override
    public final int rotation() {
        return 0;
    }

    @Override
    public final void rotation(int rotation) {
    }

    @Override
    public final float rotdeg() {
        return 0.0f;
    }

    @Override
    public final double sense(Content content) {
        return 0.0;
    }

    @Override
    public final double sense(LAccess sensor) {
        return 0.0;
    }

    @Override
    public final Object senseObject(LAccess sensor) {
        return null;
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
    public final boolean shouldActiveSound() {
        return false;
    }

    @Override
    public final boolean shouldAmbientSound() {
        return false;
    }

    @Override
    public final boolean shouldConsume() {
        return false;
    }

    @Override
    public final boolean shouldHideConfigure(Player player) {
        return false;
    }

    @Override
    public final boolean shouldShowConfigure(Player player) {
        return false;
    }

    @Override
    public final void sleep() {
    }

    @Override
    public final BlockStatus status() {
        return null;
    }

    @Override
    public final Payload takePayload() {
        return null;
    }

    @Override
    public final void tapped() {
    }

    @Override
    public final Team team() {
        return null;
    }

    @Override
    public final void team(Team team) {
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
    public final float timeScale() {
        return 1.0f;
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
    public final void transferLiquid(Building next, float amount, Liquid liquid) {
    }

    @Override
    public final void trns(Position pos) {
    }

    @Override
    public final void trns(float x, float y) {
    }

    @Override
    public final void unitOn(Unit unit) {
    }

    @Override
    public final void unitRemoved(Unit unit) {
    }

    @Override
    public final void update() {
    }

    @Override
    public final boolean updateFlow() {
        return false;
    }

    @Override
    public final void updateFlow(boolean updateFlow) {
    }

    @Override
    public final void updatePowerGraph() {
    }

    @Override
    public final void updateProximity() {
    }

    @Override
    public final void updateTableAlign(Table table) {
    }

    @Override
    public final void updateTile() {
    }

    @Override
    public final byte version() {
        return 0;
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
    public final void writeAll(Writes write) {
    }

    @Override
    public final void writeBase(Writes write) {
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

