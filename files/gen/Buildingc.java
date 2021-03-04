/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Graphics;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.ctype.Content;
import mindustry.entities.comp.Sized;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Entityc;
import mindustry.gen.Healthc;
import mindustry.gen.Player;
import mindustry.gen.Posc;
import mindustry.gen.Teamc;
import mindustry.gen.Timerc;
import mindustry.gen.Unit;
import mindustry.logic.Controllable;
import mindustry.logic.LAccess;
import mindustry.logic.Senseable;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.ui.Displayable;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.meta.BlockStatus;
import mindustry.world.modules.ConsumeModule;
import mindustry.world.modules.ItemModule;
import mindustry.world.modules.LiquidModule;
import mindustry.world.modules.PowerModule;

public interface Buildingc
extends QuadTree.QuadTreeObject,
Displayable,
Senseable,
Controllable,
Sized,
Healthc,
Entityc,
Posc,
Teamc,
Timerc {
    public Building init(Tile var1, Team var2, boolean var3, int var4);

    public Building create(Block var1, Team var2);

    @Override
    public int tileX();

    @Override
    public int tileY();

    public void writeBase(Writes var1);

    public void readBase(Reads var1);

    public void writeAll(Writes var1);

    public void readAll(Reads var1, byte var2);

    @Override
    public void write(Writes var1);

    public void read(Reads var1, byte var2);

    public void addPlan(boolean var1);

    public void configure(Object var1);

    public void configureAny(Object var1);

    public void deselect();

    public boolean configTapped();

    public void applyBoost(float var1, float var2);

    public Building nearby(int var1, int var2);

    public Building nearby(int var1);

    public byte relativeTo(Tile var1);

    public byte relativeTo(Building var1);

    public byte relativeToEdge(Tile var1);

    public byte relativeTo(int var1, int var2);

    public Building front();

    public Building back();

    public Building left();

    public Building right();

    public int pos();

    public float rotdeg();

    public Floor floor();

    public boolean interactable(Team var1);

    public float timeScale();

    public boolean consValid();

    public void consume();

    public float delta();

    public float edelta();

    public float efficiency();

    public BlockStatus status();

    public void sleep();

    public void noSleep();

    public byte version();

    public boolean canUnload();

    public void itemTaken(Item var1);

    public void dropped();

    public void handleString(Object var1);

    public void created();

    public boolean shouldConsume();

    public boolean productionValid();

    public float getPowerProduction();

    public int acceptStack(Item var1, int var2, Teamc var3);

    public int getMaximumAccepted(Item var1);

    public int removeStack(Item var1, int var2);

    public void handleStack(Item var1, int var2, Teamc var3);

    public void getStackOffset(Item var1, Vec2 var2);

    public void onProximityUpdate();

    public boolean acceptPayload(Building var1, Payload var2);

    public void handlePayload(Building var1, Payload var2);

    public boolean movePayload(Payload var1);

    public boolean dumpPayload(Payload var1);

    public void handleItem(Building var1, Item var2);

    public boolean acceptItem(Building var1, Item var2);

    public boolean acceptLiquid(Building var1, Liquid var2);

    public void handleLiquid(Building var1, Liquid var2, float var3);

    public void dumpLiquid(Liquid var1);

    public void dumpLiquid(Liquid var1, float var2);

    public boolean canDumpLiquid(Building var1, Liquid var2);

    public void transferLiquid(Building var1, float var2, Liquid var3);

    public float moveLiquidForward(boolean var1, Liquid var2);

    public float moveLiquid(Building var1, Liquid var2);

    public Building getLiquidDestination(Building var1, Liquid var2);

    public Payload getPayload();

    public Payload takePayload();

    public void offload(Item var1);

    public boolean put(Item var1);

    public void produced(Item var1);

    public void produced(Item var1, int var2);

    public boolean dump();

    public boolean dump(Item var1);

    public void incrementDump(int var1);

    public boolean canDump(Building var1, Item var2);

    public boolean moveForward(Item var1);

    public void onProximityRemoved();

    public void onProximityAdded();

    public void updatePowerGraph();

    public void powerGraphRemoved();

    public boolean conductsTo(Building var1);

    public Seq<Building> getPowerConnections(Seq<Building> var1);

    public float getProgressIncrease(float var1);

    public float getDisplayEfficiency();

    public boolean shouldActiveSound();

    public boolean shouldAmbientSound();

    public void drawStatus();

    public void drawCracks();

    public void drawSelect();

    public void drawDisabled();

    public void draw();

    public void drawTeamTop();

    public void drawLight();

    public void drawLiquidLight(Liquid var1, float var2);

    public void drawTeam();

    public void playerPlaced(Object var1);

    public void placed();

    public void overwrote(Seq<Building> var1);

    public void onRemoved();

    public void unitOn(Unit var1);

    public void unitRemoved(Unit var1);

    public void configured(Unit var1, Object var2);

    public void tapped();

    public void onDestroyed();

    public String getDisplayName();

    public TextureRegion getDisplayIcon();

    @Override
    public void display(Table var1);

    public void displayConsumption(Table var1);

    public void displayBars(Table var1);

    public void buildConfiguration(Table var1);

    public void updateTableAlign(Table var1);

    public Graphics.Cursor getCursor();

    public boolean onConfigureTileTapped(Building var1);

    public boolean shouldShowConfigure(Player var1);

    public boolean shouldHideConfigure(Player var1);

    public void drawConfigure();

    public boolean checkSolid();

    public float handleDamage(float var1);

    public boolean collide(Bullet var1);

    public boolean collision(Bullet var1);

    public boolean canPickup();

    public void pickedUp();

    public void removeFromProximity();

    public void updateProximity();

    public void updateTile();

    public float ambientVolume();

    public Object config();

    @Override
    public boolean isValid();

    @Override
    public float hitSize();

    @Override
    public void kill();

    @Override
    public void damage(float var1);

    @Override
    public double sense(LAccess var1);

    @Override
    public Object senseObject(LAccess var1);

    @Override
    public double sense(Content var1);

    @Override
    public void control(LAccess var1, double var2, double var4, double var6, double var8);

    @Override
    public void control(LAccess var1, Object var2, double var3, double var5, double var7);

    @Override
    public void remove();

    @Override
    public void killed();

    @Override
    public void update();

    @Override
    public void hitbox(Rect var1);

    public Tile tile();

    public void tile(Tile var1);

    public Block block();

    public void block(Block var1);

    public Seq<Building> proximity();

    public void proximity(Seq<Building> var1);

    public boolean updateFlow();

    public void updateFlow(boolean var1);

    public byte cdump();

    public void cdump(byte var1);

    public int rotation();

    public void rotation(int var1);

    public boolean enabled();

    public void enabled(boolean var1);

    public float enabledControlTime();

    public void enabledControlTime(float var1);

    public String lastAccessed();

    public void lastAccessed(String var1);

    public PowerModule power();

    public void power(PowerModule var1);

    public ItemModule items();

    public void items(ItemModule var1);

    public LiquidModule liquids();

    public void liquids(LiquidModule var1);

    public ConsumeModule cons();

    public void cons(ConsumeModule var1);
}

