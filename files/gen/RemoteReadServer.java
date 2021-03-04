/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.util.io.Reads;
import mindustry.core.NetClient;
import mindustry.core.NetServer;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.input.InputHandler;
import mindustry.io.TypeIO;
import mindustry.net.Packets;
import mindustry.type.Item;
import mindustry.ui.fragments.HudFragment;
import mindustry.world.Tile;

public class RemoteReadServer {
    public static void readPacket(Reads read, int id, Player player) {
        if (id == 0) {
            try {
                Player other = (Player)TypeIO.readEntity(read);
                Packets.AdminAction action = TypeIO.readAction(read);
                NetServer.adminRequest(player, other, action);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'adminRequest'!", e);
            }
        } else if (id == 8) {
            try {
                int snapshotID = read.i();
                int unitID = read.i();
                boolean dead = read.bool();
                float x = read.f();
                float y = read.f();
                float pointerX = read.f();
                float pointerY = read.f();
                float rotation = read.f();
                float baseRotation = read.f();
                float xVelocity = read.f();
                float yVelocity = read.f();
                Tile mining = TypeIO.readTile(read);
                boolean boosting = read.bool();
                boolean shooting = read.bool();
                boolean chatting = read.bool();
                boolean building = read.bool();
                BuildPlan[] requests = TypeIO.readRequests(read);
                float viewX = read.f();
                float viewY = read.f();
                float viewWidth = read.f();
                float viewHeight = read.f();
                NetServer.clientSnapshot(player, snapshotID, unitID, dead, x, y, pointerX, pointerY, rotation, baseRotation, xVelocity, yVelocity, mining, boosting, shooting, chatting, building, requests, viewX, viewY, viewWidth, viewHeight);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'clientSnapshot'!", e);
            }
        } else if (id == 10) {
            try {
                NetServer.connectConfirm(player);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'connectConfirm'!", e);
            }
        } else if (id == 15) {
            try {
                float angle = read.f();
                InputHandler.dropItem(player, angle);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'dropItem'!", e);
            }
        } else if (id == 30) {
            try {
                long time = read.l();
                NetClient.ping(player, time);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'ping'!", e);
            }
        } else if (id == 36) {
            try {
                Building build = TypeIO.readBuilding(read);
                InputHandler.requestBuildPayload(player, build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestBuildPayload'!", e);
            }
        } else if (id == 37) {
            try {
                float x = read.f();
                float y = read.f();
                InputHandler.requestDropPayload(player, x, y);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestDropPayload'!", e);
            }
        } else if (id == 38) {
            try {
                Building build = TypeIO.readBuilding(read);
                Item item = TypeIO.readItem(read);
                int amount = read.i();
                InputHandler.requestItem(player, build, item, amount);
                Call.requestItem__forward(player.con, player, build, item, amount);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestItem'!", e);
            }
        } else if (id == 39) {
            try {
                Unit target = TypeIO.readUnit(read);
                InputHandler.requestUnitPayload(player, target);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestUnitPayload'!", e);
            }
        } else if (id == 41) {
            try {
                Building build = TypeIO.readBuilding(read);
                boolean direction = read.bool();
                InputHandler.rotateBlock(player, build, direction);
                Call.rotateBlock__forward(player.con, player, build, direction);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'rotateBlock'!", e);
            }
        } else if (id == 44) {
            try {
                String message = TypeIO.readString(read);
                NetClient.sendChatMessage(player, message);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'sendChatMessage'!", e);
            }
        } else if (id == 47) {
            try {
                String type = TypeIO.readString(read);
                String contents = TypeIO.readString(read);
                NetServer.serverPacketReliable(player, type, contents);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'serverPacketReliable'!", e);
            }
        } else if (id == 48) {
            try {
                String type = TypeIO.readString(read);
                String contents = TypeIO.readString(read);
                NetServer.serverPacketUnreliable(player, type, contents);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'serverPacketUnreliable'!", e);
            }
        } else if (id == 53) {
            try {
                Team team = TypeIO.readTeam(read);
                HudFragment.setPlayerTeamEditor(player, team);
                Call.setPlayerTeamEditor__forward(player.con, player, team);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setPlayerTeamEditor'!", e);
            }
        } else if (id == 61) {
            try {
                Building build = TypeIO.readBuilding(read);
                Object value = TypeIO.readObject(read);
                InputHandler.tileConfig(player, build, value);
                Call.tileConfig__forward(player.con, player, build, value);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'tileConfig'!", e);
            }
        } else if (id == 64) {
            try {
                Tile tile = TypeIO.readTile(read);
                InputHandler.tileTap(player, tile);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'tileTap'!", e);
            }
        } else if (id == 66) {
            try {
                Building build = TypeIO.readBuilding(read);
                InputHandler.transferInventory(player, build);
                Call.transferInventory__forward(player.con, player, build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'transferInventory'!", e);
            }
        } else if (id == 72) {
            try {
                InputHandler.unitClear(player);
                Call.unitClear__forward(player.con, player);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitClear'!", e);
            }
        } else if (id == 73) {
            try {
                InputHandler.unitCommand(player);
                Call.unitCommand__forward(player.con, player);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitCommand'!", e);
            }
        } else if (id == 74) {
            try {
                Unit unit = TypeIO.readUnit(read);
                InputHandler.unitControl(player, unit);
                Call.unitControl__forward(player.con, player, unit);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitControl'!", e);
            }
        } else {
            throw new RuntimeException("Invalid read method ID: " + id + "");
        }
    }
}

