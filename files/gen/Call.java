/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.Color;
import arc.util.io.ReusableByteOutStream;
import arc.util.io.Writes;
import arc.util.pooling.Pools;
import java.io.DataOutputStream;
import mindustry.Vars;
import mindustry.ai.WaveSpawner;
import mindustry.core.Logic;
import mindustry.core.NetClient;
import mindustry.core.NetServer;
import mindustry.ctype.Content;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Rules;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Itemsc;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.input.InputHandler;
import mindustry.io.TypeIO;
import mindustry.net.Administration;
import mindustry.net.Net;
import mindustry.net.NetConnection;
import mindustry.net.Packets;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.type.Weather;
import mindustry.ui.fragments.HudFragment;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.blocks.ConstructBlock;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.UnitBlock;

public class Call {
    private static final ReusableByteOutStream OUT = new ReusableByteOutStream(8192);
    private static final Writes WRITE = new Writes(new DataOutputStream(OUT));

    public static void adminRequest(Player other, Packets.AdminAction action) {
        if (Vars.net.server() || !Vars.net.active()) {
            NetServer.adminRequest(Vars.player, other, action);
        }
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = 0;
            OUT.reset();
            TypeIO.writeEntity(WRITE, other);
            TypeIO.writeAction(WRITE, action);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void announce(String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = 1;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void announce(NetConnection playerConnection, String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = 1;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void beginBreak(Unit unit, Team team, int x, int y) {
        if (Vars.net.server() || !Vars.net.active()) {
            Build.beginBreak(unit, team, x, y);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)2;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            TypeIO.writeTeam(WRITE, team);
            WRITE.i(x);
            WRITE.i(y);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void beginPlace(Unit unit, Block result, Team team, int x, int y, int rotation) {
        if (Vars.net.server() || !Vars.net.active()) {
            Build.beginPlace(unit, result, team, x, y, rotation);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)3;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            TypeIO.writeBlock(WRITE, result);
            TypeIO.writeTeam(WRITE, team);
            WRITE.i(x);
            WRITE.i(y);
            WRITE.i(rotation);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void blockSnapshot(short amount, short dataLen, byte[] data) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = (byte)2;
            packet.type = (byte)4;
            OUT.reset();
            WRITE.s(amount);
            WRITE.s(dataLen);
            TypeIO.writeBytes(WRITE, data);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void blockSnapshot(NetConnection playerConnection, short amount, short dataLen, byte[] data) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = (byte)2;
            packet.type = (byte)4;
            OUT.reset();
            WRITE.s(amount);
            WRITE.s(dataLen);
            TypeIO.writeBytes(WRITE, data);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.udp);
        }
    }

    public static void clearItems(Building build) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.clearItems(build);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)5;
            OUT.reset();
            TypeIO.writeBuilding(WRITE, build);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void clientPacketReliable(String type, String contents) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)6;
            OUT.reset();
            TypeIO.writeString(WRITE, type);
            TypeIO.writeString(WRITE, contents);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void clientPacketReliable(NetConnection playerConnection, String type, String contents) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)6;
            OUT.reset();
            TypeIO.writeString(WRITE, type);
            TypeIO.writeString(WRITE, contents);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void clientPacketUnreliable(String type, String contents) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)7;
            OUT.reset();
            TypeIO.writeString(WRITE, type);
            TypeIO.writeString(WRITE, contents);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void clientPacketUnreliable(NetConnection playerConnection, String type, String contents) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)7;
            OUT.reset();
            TypeIO.writeString(WRITE, type);
            TypeIO.writeString(WRITE, contents);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.udp);
        }
    }

    public static void clientSnapshot(int snapshotID, int unitID, boolean dead, float x, float y, float pointerX, float pointerY, float rotation, float baseRotation, float xVelocity, float yVelocity, Tile mining, boolean boosting, boolean shooting, boolean chatting, boolean building, BuildPlan[] requests, float viewX, float viewY, float viewWidth, float viewHeight) {
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)8;
            OUT.reset();
            WRITE.i(snapshotID);
            WRITE.i(unitID);
            WRITE.bool(dead);
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(pointerX);
            WRITE.f(pointerY);
            WRITE.f(rotation);
            WRITE.f(baseRotation);
            WRITE.f(xVelocity);
            WRITE.f(yVelocity);
            TypeIO.writeTile(WRITE, mining);
            WRITE.bool(boosting);
            WRITE.bool(shooting);
            WRITE.bool(chatting);
            WRITE.bool(building);
            TypeIO.writeRequests(WRITE, requests);
            WRITE.f(viewX);
            WRITE.f(viewY);
            WRITE.f(viewWidth);
            WRITE.f(viewHeight);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void connect(NetConnection playerConnection, String ip, int port) {
        if (Vars.net.client() || !Vars.net.active()) {
            NetClient.connect(ip, port);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)9;
            OUT.reset();
            TypeIO.writeString(WRITE, ip);
            WRITE.i(port);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void connectConfirm() {
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)10;
            OUT.reset();
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void constructFinish(Tile tile, Block block, Unit builder, byte rotation, Team team, Object config) {
        if (Vars.net.server() || !Vars.net.active()) {
            ConstructBlock.constructFinish(tile, block, builder, rotation, team, config);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)11;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            TypeIO.writeBlock(WRITE, block);
            TypeIO.writeUnit(WRITE, builder);
            WRITE.b(rotation);
            TypeIO.writeTeam(WRITE, team);
            TypeIO.writeObject(WRITE, config);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void createBullet(BulletType type, Team team, float x, float y, float angle, float damage, float velocityScl, float lifetimeScl) {
        if (Vars.net.server() || !Vars.net.active()) {
            BulletType.createBullet(type, team, x, y, angle, damage, velocityScl, lifetimeScl);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)12;
            OUT.reset();
            TypeIO.writeBulletType(WRITE, type);
            TypeIO.writeTeam(WRITE, team);
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(angle);
            WRITE.f(damage);
            WRITE.f(velocityScl);
            WRITE.f(lifetimeScl);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void createWeather(Weather weather, float intensity, float duration, float windX, float windY) {
        if (Vars.net.server() || !Vars.net.active()) {
            Weather.createWeather(weather, intensity, duration, windX, windY);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)13;
            OUT.reset();
            TypeIO.writeWeather(WRITE, weather);
            WRITE.f(intensity);
            WRITE.f(duration);
            WRITE.f(windX);
            WRITE.f(windY);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void deconstructFinish(Tile tile, Block block, Unit builder) {
        if (Vars.net.server() || !Vars.net.active()) {
            ConstructBlock.deconstructFinish(tile, block, builder);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)14;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            TypeIO.writeBlock(WRITE, block);
            TypeIO.writeUnit(WRITE, builder);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void dropItem(float angle) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.dropItem(Vars.player, angle);
        }
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)15;
            OUT.reset();
            WRITE.f(angle);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void effect(Effect effect, float x, float y, float rotation, Color color) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)16;
            OUT.reset();
            TypeIO.writeEffect(WRITE, effect);
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(rotation);
            TypeIO.writeColor(WRITE, color);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void effect(NetConnection playerConnection, Effect effect, float x, float y, float rotation, Color color) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)16;
            OUT.reset();
            TypeIO.writeEffect(WRITE, effect);
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(rotation);
            TypeIO.writeColor(WRITE, color);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.udp);
        }
    }

    public static void effectReliable(Effect effect, float x, float y, float rotation, Color color) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)17;
            OUT.reset();
            TypeIO.writeEffect(WRITE, effect);
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(rotation);
            TypeIO.writeColor(WRITE, color);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void effectReliable(NetConnection playerConnection, Effect effect, float x, float y, float rotation, Color color) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)17;
            OUT.reset();
            TypeIO.writeEffect(WRITE, effect);
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(rotation);
            TypeIO.writeColor(WRITE, color);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void entitySnapshot(NetConnection playerConnection, short amount, short dataLen, byte[] data) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = (byte)2;
            packet.type = (byte)18;
            OUT.reset();
            WRITE.s(amount);
            WRITE.s(dataLen);
            TypeIO.writeBytes(WRITE, data);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.udp);
        }
    }

    public static void gameOver(Team winner) {
        Logic.gameOver(winner);
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)19;
            OUT.reset();
            TypeIO.writeTeam(WRITE, winner);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void hideHudText() {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)20;
            OUT.reset();
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void hideHudText(NetConnection playerConnection) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)20;
            OUT.reset();
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void infoMessage(String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)21;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void infoMessage(NetConnection playerConnection, String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)21;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void infoPopup(String message, float duration, int align, int top, int left, int bottom, int right) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)22;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            WRITE.f(duration);
            WRITE.i(align);
            WRITE.i(top);
            WRITE.i(left);
            WRITE.i(bottom);
            WRITE.i(right);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void infoPopup(NetConnection playerConnection, String message, float duration, int align, int top, int left, int bottom, int right) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)22;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            WRITE.f(duration);
            WRITE.i(align);
            WRITE.i(top);
            WRITE.i(left);
            WRITE.i(bottom);
            WRITE.i(right);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void infoToast(String message, float duration) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)23;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            WRITE.f(duration);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void infoToast(NetConnection playerConnection, String message, float duration) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)23;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            WRITE.f(duration);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void kick(NetConnection playerConnection, String reason) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 1;
            packet.type = (byte)24;
            OUT.reset();
            TypeIO.writeString(WRITE, reason);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void kick(NetConnection playerConnection, Packets.KickReason reason) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 1;
            packet.type = (byte)25;
            OUT.reset();
            TypeIO.writeKick(WRITE, reason);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void label(String message, float duration, float worldx, float worldy) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)26;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            WRITE.f(duration);
            WRITE.f(worldx);
            WRITE.f(worldy);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void label(NetConnection playerConnection, String message, float duration, float worldx, float worldy) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)26;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            WRITE.f(duration);
            WRITE.f(worldx);
            WRITE.f(worldy);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void payloadDropped(Unit unit, float x, float y) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.payloadDropped(unit, x, y);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)27;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            WRITE.f(x);
            WRITE.f(y);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void pickedBuildPayload(Unit unit, Building build, boolean onGround) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.pickedBuildPayload(unit, build, onGround);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)28;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            TypeIO.writeBuilding(WRITE, build);
            WRITE.bool(onGround);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void pickedUnitPayload(Unit unit, Unit target) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.pickedUnitPayload(unit, target);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)29;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            TypeIO.writeUnit(WRITE, target);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void ping(long time) {
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)30;
            OUT.reset();
            WRITE.l(time);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void pingResponse(NetConnection playerConnection, long time) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)31;
            OUT.reset();
            WRITE.l(time);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void playerDisconnect(int playerid) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)32;
            OUT.reset();
            WRITE.i(playerid);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void playerSpawn(Tile tile, Player player) {
        if (Vars.net.server() || !Vars.net.active()) {
            CoreBlock.playerSpawn(tile, player);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)33;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            TypeIO.writeEntity(WRITE, player);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void removeQueueBlock(NetConnection playerConnection, int x, int y, boolean breaking) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)34;
            OUT.reset();
            WRITE.i(x);
            WRITE.i(y);
            WRITE.bool(breaking);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void removeTile(Tile tile) {
        if (Vars.net.server() || !Vars.net.active()) {
            Tile.removeTile(tile);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)35;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void requestBuildPayload(Player player, Building build) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.requestBuildPayload(player, build);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)36;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void requestDropPayload(Player player, float x, float y) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.requestDropPayload(player, x, y);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)37;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            WRITE.f(x);
            WRITE.f(y);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void requestItem(Player player, Building build, Item item, int amount) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.requestItem(player, build, item, amount);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)38;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeItem(WRITE, item);
            WRITE.i(amount);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void requestItem__forward(NetConnection exceptConnection, Player player, Building build, Item item, int amount) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)38;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeItem(WRITE, item);
            WRITE.i(amount);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void requestUnitPayload(Player player, Unit target) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.requestUnitPayload(player, target);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)39;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeUnit(WRITE, target);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void researched(Content content) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)40;
            OUT.reset();
            TypeIO.writeContent(WRITE, content);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void rotateBlock(Player player, Building build, boolean direction) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.rotateBlock(player, build, direction);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)41;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            WRITE.bool(direction);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    static void rotateBlock__forward(NetConnection exceptConnection, Player player, Building build, boolean direction) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)41;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            WRITE.bool(direction);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void sectorCapture() {
        if (Vars.net.server() || !Vars.net.active()) {
            Logic.sectorCapture();
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)42;
            OUT.reset();
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void sectorProduced(int[] amounts) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)43;
            OUT.reset();
            TypeIO.writeInts(WRITE, amounts);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void sendChatMessage(String message) {
        if (Vars.net.server() || !Vars.net.active()) {
            NetClient.sendChatMessage(Vars.player, message);
        }
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)44;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void sendMessage(String message) {
        if (Vars.net.server() || !Vars.net.active()) {
            NetClient.sendMessage(message);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)45;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void sendMessage(String message, String sender, Player playersender) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)46;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            TypeIO.writeString(WRITE, sender);
            TypeIO.writeEntity(WRITE, playersender);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void sendMessage(NetConnection playerConnection, String message, String sender, Player playersender) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)46;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            TypeIO.writeString(WRITE, sender);
            TypeIO.writeEntity(WRITE, playersender);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void serverPacketReliable(String type, String contents) {
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)47;
            OUT.reset();
            TypeIO.writeString(WRITE, type);
            TypeIO.writeString(WRITE, contents);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void serverPacketUnreliable(String type, String contents) {
        if (Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)48;
            OUT.reset();
            TypeIO.writeString(WRITE, type);
            TypeIO.writeString(WRITE, contents);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void setFloor(Tile tile, Block floor, Block overlay) {
        if (Vars.net.server() || !Vars.net.active()) {
            Tile.setFloor(tile, floor, overlay);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)49;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            TypeIO.writeBlock(WRITE, floor);
            TypeIO.writeBlock(WRITE, overlay);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setHudText(String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)50;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void setHudText(NetConnection playerConnection, String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)50;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.udp);
        }
    }

    public static void setHudTextReliable(String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)51;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setHudTextReliable(NetConnection playerConnection, String message) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)51;
            OUT.reset();
            TypeIO.writeString(WRITE, message);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setItem(Building build, Item item, int amount) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.setItem(build, item, amount);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)52;
            OUT.reset();
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeItem(WRITE, item);
            WRITE.i(amount);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void setPlayerTeamEditor(Player player, Team team) {
        HudFragment.setPlayerTeamEditor(player, team);
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)53;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeTeam(WRITE, team);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void setPlayerTeamEditor__forward(NetConnection exceptConnection, Player player, Team team) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)53;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeTeam(WRITE, team);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.sendExcept(exceptConnection, packet, Net.SendMode.tcp);
        }
    }

    public static void setPosition(NetConnection playerConnection, float x, float y) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)54;
            OUT.reset();
            WRITE.f(x);
            WRITE.f(y);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setRules(Rules rules) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)55;
            OUT.reset();
            TypeIO.writeRules(WRITE, rules);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setRules(NetConnection playerConnection, Rules rules) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)55;
            OUT.reset();
            TypeIO.writeRules(WRITE, rules);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setTeam(Building build, Team team) {
        if (Vars.net.server() || !Vars.net.active()) {
            Tile.setTeam(build, team);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)56;
            OUT.reset();
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeTeam(WRITE, team);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void setTile(Tile tile, Block block, Team team, int rotation) {
        if (Vars.net.server() || !Vars.net.active()) {
            Tile.setTile(tile, block, team, rotation);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)57;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            TypeIO.writeBlock(WRITE, block);
            TypeIO.writeTeam(WRITE, team);
            WRITE.i(rotation);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void spawnEffect(float x, float y, float rotation, UnitType u) {
        if (Vars.net.server() || !Vars.net.active()) {
            WaveSpawner.spawnEffect(x, y, rotation, u);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)58;
            OUT.reset();
            WRITE.f(x);
            WRITE.f(y);
            WRITE.f(rotation);
            TypeIO.writeUnitType(WRITE, u);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void stateSnapshot(NetConnection playerConnection, float waveTime, int wave, int enemies, boolean paused, boolean gameOver, int timeData, short coreDataLen, byte[] coreData) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = (byte)2;
            packet.type = (byte)59;
            OUT.reset();
            WRITE.f(waveTime);
            WRITE.i(wave);
            WRITE.i(enemies);
            WRITE.bool(paused);
            WRITE.bool(gameOver);
            WRITE.i(timeData);
            WRITE.s(coreDataLen);
            TypeIO.writeBytes(WRITE, coreData);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.udp);
        }
    }

    public static void takeItems(Building build, Item item, int amount, Unit to) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.takeItems(build, item, amount, to);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)60;
            OUT.reset();
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeItem(WRITE, item);
            WRITE.i(amount);
            TypeIO.writeUnit(WRITE, to);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void tileConfig(Player player, Building build, Object value) {
        InputHandler.tileConfig(player, build, value);
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)61;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeObject(WRITE, value);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void tileConfig__forward(NetConnection exceptConnection, Player player, Building build, Object value) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)61;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            TypeIO.writeObject(WRITE, value);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.sendExcept(exceptConnection, packet, Net.SendMode.tcp);
        }
    }

    public static void tileDamage(Building build, float health) {
        if (Vars.net.server() || !Vars.net.active()) {
            Tile.tileDamage(build, health);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)62;
            OUT.reset();
            TypeIO.writeBuilding(WRITE, build);
            WRITE.f(health);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void tileDestroyed(Building build) {
        if (Vars.net.server() || !Vars.net.active()) {
            Tile.tileDestroyed(build);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)63;
            OUT.reset();
            TypeIO.writeBuilding(WRITE, build);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void tileTap(Player player, Tile tile) {
        InputHandler.tileTap(player, tile);
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)64;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeTile(WRITE, tile);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void traceInfo(NetConnection playerConnection, Player player, Administration.TraceInfo info) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)65;
            OUT.reset();
            TypeIO.writeEntity(WRITE, player);
            TypeIO.writeTraceInfo(WRITE, info);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void transferInventory(Player player, Building build) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.transferInventory(player, build);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)66;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void transferInventory__forward(NetConnection exceptConnection, Player player, Building build) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)66;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeBuilding(WRITE, build);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void transferItemEffect(Item item, float x, float y, Itemsc to) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.transferItemEffect(item, x, y, to);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)67;
            OUT.reset();
            TypeIO.writeItem(WRITE, item);
            WRITE.f(x);
            WRITE.f(y);
            TypeIO.writeEntity(WRITE, to);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void transferItemTo(Unit unit, Item item, int amount, float x, float y, Building build) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.transferItemTo(unit, item, amount, x, y, build);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)68;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            TypeIO.writeItem(WRITE, item);
            WRITE.i(amount);
            WRITE.f(x);
            WRITE.f(y);
            TypeIO.writeBuilding(WRITE, build);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void transferItemToUnit(Item item, float x, float y, Itemsc to) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.transferItemToUnit(item, x, y, to);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)69;
            OUT.reset();
            TypeIO.writeItem(WRITE, item);
            WRITE.f(x);
            WRITE.f(y);
            TypeIO.writeEntity(WRITE, to);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.udp);
        }
    }

    public static void unitBlockSpawn(Tile tile) {
        if (Vars.net.server() || !Vars.net.active()) {
            UnitBlock.unitBlockSpawn(tile);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)70;
            OUT.reset();
            TypeIO.writeTile(WRITE, tile);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void unitCapDeath(Unit unit) {
        if (Vars.net.server() || !Vars.net.active()) {
            Units.unitCapDeath(unit);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)71;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void unitClear(Player player) {
        InputHandler.unitClear(player);
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)72;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void unitClear__forward(NetConnection exceptConnection, Player player) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)72;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.sendExcept(exceptConnection, packet, Net.SendMode.tcp);
        }
    }

    public static void unitCommand(Player player) {
        if (Vars.net.server() || !Vars.net.active()) {
            InputHandler.unitCommand(player);
        }
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)73;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void unitCommand__forward(NetConnection exceptConnection, Player player) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)73;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void unitControl(Player player, Unit unit) {
        InputHandler.unitControl(player, unit);
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)74;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeUnit(WRITE, unit);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    static void unitControl__forward(NetConnection exceptConnection, Player player, Unit unit) {
        if (Vars.net.server() || Vars.net.client()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)74;
            OUT.reset();
            if (Vars.net.server()) {
                TypeIO.writeEntity(WRITE, player);
            }
            TypeIO.writeUnit(WRITE, unit);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.sendExcept(exceptConnection, packet, Net.SendMode.tcp);
        }
    }

    public static void unitDeath(int uid) {
        if (Vars.net.server() || !Vars.net.active()) {
            Units.unitDeath(uid);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)75;
            OUT.reset();
            WRITE.i(uid);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void unitDespawn(Unit unit) {
        if (Vars.net.server() || !Vars.net.active()) {
            Units.unitDespawn(unit);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)76;
            OUT.reset();
            TypeIO.writeUnit(WRITE, unit);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void unitDestroy(int uid) {
        if (Vars.net.server() || !Vars.net.active()) {
            Units.unitDestroy(uid);
        }
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)77;
            OUT.reset();
            WRITE.i(uid);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void updateGameOver(Team winner) {
        Logic.updateGameOver(winner);
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)78;
            OUT.reset();
            TypeIO.writeTeam(WRITE, winner);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void warningToast(int unicode, String text) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)79;
            OUT.reset();
            WRITE.i(unicode);
            TypeIO.writeString(WRITE, text);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void warningToast(NetConnection playerConnection, int unicode, String text) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)79;
            OUT.reset();
            WRITE.i(unicode);
            TypeIO.writeString(WRITE, text);
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }

    public static void worldDataBegin() {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)80;
            OUT.reset();
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            Vars.net.send(packet, Net.SendMode.tcp);
        }
    }

    public static void worldDataBegin(NetConnection playerConnection) {
        if (Vars.net.server()) {
            Packets.InvokePacket packet = Pools.obtain(Packets.InvokePacket.class, Packets.InvokePacket::new);
            packet.priority = 0;
            packet.type = (byte)80;
            OUT.reset();
            packet.bytes = OUT.getBytes();
            packet.length = OUT.size();
            playerConnection.send(packet, Net.SendMode.tcp);
        }
    }
}

