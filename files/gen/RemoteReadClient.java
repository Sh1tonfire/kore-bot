/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.Color;
import arc.util.io.Reads;
import mindustry.ai.WaveSpawner;
import mindustry.core.Logic;
import mindustry.core.NetClient;
import mindustry.ctype.Content;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Rules;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Itemsc;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.input.InputHandler;
import mindustry.io.TypeIO;
import mindustry.net.Administration;
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

public class RemoteReadClient {
    public static void readPacket(Reads read, int id) {
        if (id == 1) {
            try {
                String message = TypeIO.readString(read);
                NetClient.announce(message);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'announce'!", e);
            }
        } else if (id == 2) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Team team = TypeIO.readTeam(read);
                int x = read.i();
                int y = read.i();
                Build.beginBreak(unit, team, x, y);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'beginBreak'!", e);
            }
        } else if (id == 3) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Block result = TypeIO.readBlock(read);
                Team team = TypeIO.readTeam(read);
                int x = read.i();
                int y = read.i();
                int rotation = read.i();
                Build.beginPlace(unit, result, team, x, y, rotation);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'beginPlace'!", e);
            }
        } else if (id == 4) {
            try {
                short amount = read.s();
                short dataLen = read.s();
                byte[] data = TypeIO.readBytes(read);
                NetClient.blockSnapshot(amount, dataLen, data);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'blockSnapshot'!", e);
            }
        } else if (id == 5) {
            try {
                Building build = TypeIO.readBuilding(read);
                InputHandler.clearItems(build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'clearItems'!", e);
            }
        } else if (id == 6) {
            try {
                String type = TypeIO.readString(read);
                String contents = TypeIO.readString(read);
                NetClient.clientPacketReliable(type, contents);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'clientPacketReliable'!", e);
            }
        } else if (id == 7) {
            try {
                String type = TypeIO.readString(read);
                String contents = TypeIO.readString(read);
                NetClient.clientPacketUnreliable(type, contents);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'clientPacketUnreliable'!", e);
            }
        } else if (id == 9) {
            try {
                String ip = TypeIO.readString(read);
                int port = read.i();
                NetClient.connect(ip, port);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'connect'!", e);
            }
        } else if (id == 11) {
            try {
                Tile tile = TypeIO.readTile(read);
                Block block = TypeIO.readBlock(read);
                Unit builder = TypeIO.readUnit(read);
                byte rotation = read.b();
                Team team = TypeIO.readTeam(read);
                Object config = TypeIO.readObject(read);
                ConstructBlock.constructFinish(tile, block, builder, rotation, team, config);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'constructFinish'!", e);
            }
        } else if (id == 12) {
            try {
                BulletType type = TypeIO.readBulletType(read);
                Team team = TypeIO.readTeam(read);
                float x = read.f();
                float y = read.f();
                float angle = read.f();
                float damage = read.f();
                float velocityScl = read.f();
                float lifetimeScl = read.f();
                BulletType.createBullet(type, team, x, y, angle, damage, velocityScl, lifetimeScl);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'createBullet'!", e);
            }
        } else if (id == 13) {
            try {
                Weather weather = TypeIO.readWeather(read);
                float intensity = read.f();
                float duration = read.f();
                float windX = read.f();
                float windY = read.f();
                Weather.createWeather(weather, intensity, duration, windX, windY);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'createWeather'!", e);
            }
        } else if (id == 14) {
            try {
                Tile tile = TypeIO.readTile(read);
                Block block = TypeIO.readBlock(read);
                Unit builder = TypeIO.readUnit(read);
                ConstructBlock.deconstructFinish(tile, block, builder);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'deconstructFinish'!", e);
            }
        } else if (id == 16) {
            try {
                Effect effect = TypeIO.readEffect(read);
                float x = read.f();
                float y = read.f();
                float rotation = read.f();
                Color color = TypeIO.readColor(read);
                NetClient.effect(effect, x, y, rotation, color);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'effect'!", e);
            }
        } else if (id == 17) {
            try {
                Effect effect = TypeIO.readEffect(read);
                float x = read.f();
                float y = read.f();
                float rotation = read.f();
                Color color = TypeIO.readColor(read);
                NetClient.effectReliable(effect, x, y, rotation, color);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'effectReliable'!", e);
            }
        } else if (id == 18) {
            try {
                short amount = read.s();
                short dataLen = read.s();
                byte[] data = TypeIO.readBytes(read);
                NetClient.entitySnapshot(amount, dataLen, data);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'entitySnapshot'!", e);
            }
        } else if (id == 19) {
            try {
                Team winner = TypeIO.readTeam(read);
                Logic.gameOver(winner);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'gameOver'!", e);
            }
        } else if (id == 20) {
            try {
                NetClient.hideHudText();
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'hideHudText'!", e);
            }
        } else if (id == 21) {
            try {
                String message = TypeIO.readString(read);
                NetClient.infoMessage(message);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'infoMessage'!", e);
            }
        } else if (id == 22) {
            try {
                String message = TypeIO.readString(read);
                float duration = read.f();
                int align = read.i();
                int top = read.i();
                int left = read.i();
                int bottom = read.i();
                int right = read.i();
                NetClient.infoPopup(message, duration, align, top, left, bottom, right);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'infoPopup'!", e);
            }
        } else if (id == 23) {
            try {
                String message = TypeIO.readString(read);
                float duration = read.f();
                NetClient.infoToast(message, duration);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'infoToast'!", e);
            }
        } else if (id == 24) {
            try {
                String reason = TypeIO.readString(read);
                NetClient.kick(reason);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'kick'!", e);
            }
        } else if (id == 25) {
            try {
                Packets.KickReason reason = TypeIO.readKick(read);
                NetClient.kick(reason);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'kick'!", e);
            }
        } else if (id == 26) {
            try {
                String message = TypeIO.readString(read);
                float duration = read.f();
                float worldx = read.f();
                float worldy = read.f();
                NetClient.label(message, duration, worldx, worldy);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'label'!", e);
            }
        } else if (id == 27) {
            try {
                Unit unit = TypeIO.readUnit(read);
                float x = read.f();
                float y = read.f();
                InputHandler.payloadDropped(unit, x, y);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'payloadDropped'!", e);
            }
        } else if (id == 28) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Building build = TypeIO.readBuilding(read);
                boolean onGround = read.bool();
                InputHandler.pickedBuildPayload(unit, build, onGround);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'pickedBuildPayload'!", e);
            }
        } else if (id == 29) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Unit target = TypeIO.readUnit(read);
                InputHandler.pickedUnitPayload(unit, target);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'pickedUnitPayload'!", e);
            }
        } else if (id == 31) {
            try {
                long time = read.l();
                NetClient.pingResponse(time);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'pingResponse'!", e);
            }
        } else if (id == 32) {
            try {
                int playerid = read.i();
                NetClient.playerDisconnect(playerid);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'playerDisconnect'!", e);
            }
        } else if (id == 33) {
            try {
                Tile tile = TypeIO.readTile(read);
                Player player = (Player)TypeIO.readEntity(read);
                CoreBlock.playerSpawn(tile, player);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'playerSpawn'!", e);
            }
        } else if (id == 34) {
            try {
                int x = read.i();
                int y = read.i();
                boolean breaking = read.bool();
                InputHandler.removeQueueBlock(x, y, breaking);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'removeQueueBlock'!", e);
            }
        } else if (id == 35) {
            try {
                Tile tile = TypeIO.readTile(read);
                Tile.removeTile(tile);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'removeTile'!", e);
            }
        } else if (id == 36) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Building build = TypeIO.readBuilding(read);
                InputHandler.requestBuildPayload(player, build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestBuildPayload'!", e);
            }
        } else if (id == 37) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                float x = read.f();
                float y = read.f();
                InputHandler.requestDropPayload(player, x, y);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestDropPayload'!", e);
            }
        } else if (id == 38) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Building build = TypeIO.readBuilding(read);
                Item item = TypeIO.readItem(read);
                int amount = read.i();
                InputHandler.requestItem(player, build, item, amount);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestItem'!", e);
            }
        } else if (id == 39) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Unit target = TypeIO.readUnit(read);
                InputHandler.requestUnitPayload(player, target);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'requestUnitPayload'!", e);
            }
        } else if (id == 40) {
            try {
                Content content = TypeIO.readContent(read);
                Logic.researched(content);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'researched'!", e);
            }
        } else if (id == 41) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Building build = TypeIO.readBuilding(read);
                boolean direction = read.bool();
                InputHandler.rotateBlock(player, build, direction);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'rotateBlock'!", e);
            }
        } else if (id == 42) {
            try {
                Logic.sectorCapture();
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'sectorCapture'!", e);
            }
        } else if (id == 43) {
            try {
                int[] amounts = TypeIO.readInts(read);
                Logic.sectorProduced(amounts);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'sectorProduced'!", e);
            }
        } else if (id == 45) {
            try {
                String message = TypeIO.readString(read);
                NetClient.sendMessage(message);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'sendMessage'!", e);
            }
        } else if (id == 46) {
            try {
                String message = TypeIO.readString(read);
                String sender = TypeIO.readString(read);
                Player playersender = (Player)TypeIO.readEntity(read);
                NetClient.sendMessage(message, sender, playersender);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'sendMessage'!", e);
            }
        } else if (id == 49) {
            try {
                Tile tile = TypeIO.readTile(read);
                Block floor = TypeIO.readBlock(read);
                Block overlay = TypeIO.readBlock(read);
                Tile.setFloor(tile, floor, overlay);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setFloor'!", e);
            }
        } else if (id == 50) {
            try {
                String message = TypeIO.readString(read);
                NetClient.setHudText(message);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setHudText'!", e);
            }
        } else if (id == 51) {
            try {
                String message = TypeIO.readString(read);
                NetClient.setHudTextReliable(message);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setHudTextReliable'!", e);
            }
        } else if (id == 52) {
            try {
                Building build = TypeIO.readBuilding(read);
                Item item = TypeIO.readItem(read);
                int amount = read.i();
                InputHandler.setItem(build, item, amount);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setItem'!", e);
            }
        } else if (id == 53) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Team team = TypeIO.readTeam(read);
                HudFragment.setPlayerTeamEditor(player, team);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setPlayerTeamEditor'!", e);
            }
        } else if (id == 54) {
            try {
                float x = read.f();
                float y = read.f();
                NetClient.setPosition(x, y);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setPosition'!", e);
            }
        } else if (id == 55) {
            try {
                Rules rules = TypeIO.readRules(read);
                NetClient.setRules(rules);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setRules'!", e);
            }
        } else if (id == 56) {
            try {
                Building build = TypeIO.readBuilding(read);
                Team team = TypeIO.readTeam(read);
                Tile.setTeam(build, team);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setTeam'!", e);
            }
        } else if (id == 57) {
            try {
                Tile tile = TypeIO.readTile(read);
                Block block = TypeIO.readBlock(read);
                Team team = TypeIO.readTeam(read);
                int rotation = read.i();
                Tile.setTile(tile, block, team, rotation);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'setTile'!", e);
            }
        } else if (id == 58) {
            try {
                float x = read.f();
                float y = read.f();
                float rotation = read.f();
                UnitType u = TypeIO.readUnitType(read);
                WaveSpawner.spawnEffect(x, y, rotation, u);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'spawnEffect'!", e);
            }
        } else if (id == 59) {
            try {
                float waveTime = read.f();
                int wave = read.i();
                int enemies = read.i();
                boolean paused = read.bool();
                boolean gameOver = read.bool();
                int timeData = read.i();
                short coreDataLen = read.s();
                byte[] coreData = TypeIO.readBytes(read);
                NetClient.stateSnapshot(waveTime, wave, enemies, paused, gameOver, timeData, coreDataLen, coreData);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'stateSnapshot'!", e);
            }
        } else if (id == 60) {
            try {
                Building build = TypeIO.readBuilding(read);
                Item item = TypeIO.readItem(read);
                int amount = read.i();
                Unit to = TypeIO.readUnit(read);
                InputHandler.takeItems(build, item, amount, to);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'takeItems'!", e);
            }
        } else if (id == 61) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Building build = TypeIO.readBuilding(read);
                Object value = TypeIO.readObject(read);
                InputHandler.tileConfig(player, build, value);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'tileConfig'!", e);
            }
        } else if (id == 62) {
            try {
                Building build = TypeIO.readBuilding(read);
                float health = read.f();
                Tile.tileDamage(build, health);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'tileDamage'!", e);
            }
        } else if (id == 63) {
            try {
                Building build = TypeIO.readBuilding(read);
                Tile.tileDestroyed(build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'tileDestroyed'!", e);
            }
        } else if (id == 64) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Tile tile = TypeIO.readTile(read);
                InputHandler.tileTap(player, tile);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'tileTap'!", e);
            }
        } else if (id == 65) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Administration.TraceInfo info = TypeIO.readTraceInfo(read);
                NetClient.traceInfo(player, info);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'traceInfo'!", e);
            }
        } else if (id == 66) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Building build = TypeIO.readBuilding(read);
                InputHandler.transferInventory(player, build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'transferInventory'!", e);
            }
        } else if (id == 67) {
            try {
                Item item = TypeIO.readItem(read);
                float x = read.f();
                float y = read.f();
                Itemsc to = (Itemsc)TypeIO.readEntity(read);
                InputHandler.transferItemEffect(item, x, y, to);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'transferItemEffect'!", e);
            }
        } else if (id == 68) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Item item = TypeIO.readItem(read);
                int amount = read.i();
                float x = read.f();
                float y = read.f();
                Building build = TypeIO.readBuilding(read);
                InputHandler.transferItemTo(unit, item, amount, x, y, build);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'transferItemTo'!", e);
            }
        } else if (id == 69) {
            try {
                Item item = TypeIO.readItem(read);
                float x = read.f();
                float y = read.f();
                Itemsc to = (Itemsc)TypeIO.readEntity(read);
                InputHandler.transferItemToUnit(item, x, y, to);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'transferItemToUnit'!", e);
            }
        } else if (id == 70) {
            try {
                Tile tile = TypeIO.readTile(read);
                UnitBlock.unitBlockSpawn(tile);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitBlockSpawn'!", e);
            }
        } else if (id == 71) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Units.unitCapDeath(unit);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitCapDeath'!", e);
            }
        } else if (id == 72) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                InputHandler.unitClear(player);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitClear'!", e);
            }
        } else if (id == 73) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                InputHandler.unitCommand(player);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitCommand'!", e);
            }
        } else if (id == 74) {
            try {
                Player player = (Player)TypeIO.readEntity(read);
                Unit unit = TypeIO.readUnit(read);
                InputHandler.unitControl(player, unit);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitControl'!", e);
            }
        } else if (id == 75) {
            try {
                int uid = read.i();
                Units.unitDeath(uid);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitDeath'!", e);
            }
        } else if (id == 76) {
            try {
                Unit unit = TypeIO.readUnit(read);
                Units.unitDespawn(unit);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitDespawn'!", e);
            }
        } else if (id == 77) {
            try {
                int uid = read.i();
                Units.unitDestroy(uid);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'unitDestroy'!", e);
            }
        } else if (id == 78) {
            try {
                Team winner = TypeIO.readTeam(read);
                Logic.updateGameOver(winner);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'updateGameOver'!", e);
            }
        } else if (id == 79) {
            try {
                int unicode = read.i();
                String text = TypeIO.readString(read);
                NetClient.warningToast(unicode, text);
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'warningToast'!", e);
            }
        } else if (id == 80) {
            try {
                NetClient.worldDataBegin();
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read remote method 'worldDataBegin'!", e);
            }
        } else {
            throw new RuntimeException("Invalid read method ID: " + id + "");
        }
    }
}

