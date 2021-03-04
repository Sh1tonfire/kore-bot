/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.audio.Sound;

public class Sounds {
    public static Sound respawning = new Sound();
    public static Sound mud = new Sound();
    public static Sound rain = new Sound();
    public static Sound beam = new Sound();
    public static Sound sap = new Sound();
    public static Sound shootSnap = new Sound();
    public static Sound respawn = new Sound();
    public static Sound pew = new Sound();
    public static Sound machine = new Sound();
    public static Sound corexplode = new Sound();
    public static Sound tractorbeam = new Sound();
    public static Sound lasershoot = new Sound();
    public static Sound explosion = new Sound();
    public static Sound railgun = new Sound();
    public static Sound shootBig = new Sound();
    public static Sound thruster = new Sound();
    public static Sound click = new Sound();
    public static Sound windhowl = new Sound();
    public static Sound wind = new Sound();
    public static Sound combustion = new Sound();
    public static Sound lasercharge = new Sound();
    public static Sound noammo = new Sound();
    public static Sound lasercharge2 = new Sound();
    public static Sound shoot = new Sound();
    public static Sound plasmadrop = new Sound();
    public static Sound spark = new Sound();
    public static Sound fire = new Sound();
    public static Sound swish = new Sound();
    public static Sound shotgun = new Sound();
    public static Sound flame2 = new Sound();
    public static Sound shield = new Sound();
    public static Sound release = new Sound();
    public static Sound artillery = new Sound();
    public static Sound buttonClick = new Sound();
    public static Sound breaks = new Sound();
    public static Sound cutter = new Sound();
    public static Sound door = new Sound();
    public static Sound place = new Sound();
    public static Sound build = new Sound();
    public static Sound explosionbig = new Sound();
    public static Sound bang = new Sound();
    public static Sound windowHide = new Sound();
    public static Sound smelter = new Sound();
    public static Sound wave = new Sound();
    public static Sound hum = new Sound();
    public static Sound conveyor = new Sound();
    public static Sound laser = new Sound();
    public static Sound laserblast = new Sound();
    public static Sound pulse = new Sound();
    public static Sound techloop = new Sound();
    public static Sound plasmaboom = new Sound();
    public static Sound missile = new Sound();
    public static Sound boom = new Sound();
    public static Sound grinding = new Sound();
    public static Sound drill = new Sound();
    public static Sound spray = new Sound();
    public static Sound splash = new Sound();
    public static Sound press = new Sound();
    public static Sound back = new Sound();
    public static Sound unlock = new Sound();
    public static Sound message = new Sound();
    public static Sound flame = new Sound();
    public static Sound minebeam = new Sound();
    public static Sound laserbig = new Sound();
    public static Sound steam = new Sound();
    public static Sound bigshot = new Sound();
    public static Sound wind2 = new Sound();
    public static Sound none = new Sound();

    public static void load() {
        Core.assets.load((String)"sounds/respawning.ogg", Sound.class).loaded = a -> {
            respawning = (Sound)a;
        };
        Core.assets.load((String)"sounds/mud.ogg", Sound.class).loaded = a -> {
            mud = (Sound)a;
        };
        Core.assets.load((String)"sounds/rain.ogg", Sound.class).loaded = a -> {
            rain = (Sound)a;
        };
        Core.assets.load((String)"sounds/beam.ogg", Sound.class).loaded = a -> {
            beam = (Sound)a;
        };
        Core.assets.load((String)"sounds/sap.ogg", Sound.class).loaded = a -> {
            sap = (Sound)a;
        };
        Core.assets.load((String)"sounds/shootSnap.ogg", Sound.class).loaded = a -> {
            shootSnap = (Sound)a;
        };
        Core.assets.load((String)"sounds/respawn.ogg", Sound.class).loaded = a -> {
            respawn = (Sound)a;
        };
        Core.assets.load((String)"sounds/pew.ogg", Sound.class).loaded = a -> {
            pew = (Sound)a;
        };
        Core.assets.load((String)"sounds/machine.ogg", Sound.class).loaded = a -> {
            machine = (Sound)a;
        };
        Core.assets.load((String)"sounds/corexplode.ogg", Sound.class).loaded = a -> {
            corexplode = (Sound)a;
        };
        Core.assets.load((String)"sounds/tractorbeam.ogg", Sound.class).loaded = a -> {
            tractorbeam = (Sound)a;
        };
        Core.assets.load((String)"sounds/lasershoot.ogg", Sound.class).loaded = a -> {
            lasershoot = (Sound)a;
        };
        Core.assets.load((String)"sounds/explosion.ogg", Sound.class).loaded = a -> {
            explosion = (Sound)a;
        };
        Core.assets.load((String)"sounds/railgun.ogg", Sound.class).loaded = a -> {
            railgun = (Sound)a;
        };
        Core.assets.load((String)"sounds/shootBig.ogg", Sound.class).loaded = a -> {
            shootBig = (Sound)a;
        };
        Core.assets.load((String)"sounds/thruster.ogg", Sound.class).loaded = a -> {
            thruster = (Sound)a;
        };
        Core.assets.load((String)"sounds/click.ogg", Sound.class).loaded = a -> {
            click = (Sound)a;
        };
        Core.assets.load((String)"sounds/windhowl.ogg", Sound.class).loaded = a -> {
            windhowl = (Sound)a;
        };
        Core.assets.load((String)"sounds/wind.ogg", Sound.class).loaded = a -> {
            wind = (Sound)a;
        };
        Core.assets.load((String)"sounds/combustion.ogg", Sound.class).loaded = a -> {
            combustion = (Sound)a;
        };
        Core.assets.load((String)"sounds/lasercharge.ogg", Sound.class).loaded = a -> {
            lasercharge = (Sound)a;
        };
        Core.assets.load((String)"sounds/noammo.ogg", Sound.class).loaded = a -> {
            noammo = (Sound)a;
        };
        Core.assets.load((String)"sounds/lasercharge2.ogg", Sound.class).loaded = a -> {
            lasercharge2 = (Sound)a;
        };
        Core.assets.load((String)"sounds/shoot.ogg", Sound.class).loaded = a -> {
            shoot = (Sound)a;
        };
        Core.assets.load((String)"sounds/plasmadrop.ogg", Sound.class).loaded = a -> {
            plasmadrop = (Sound)a;
        };
        Core.assets.load((String)"sounds/spark.ogg", Sound.class).loaded = a -> {
            spark = (Sound)a;
        };
        Core.assets.load((String)"sounds/fire.ogg", Sound.class).loaded = a -> {
            fire = (Sound)a;
        };
        Core.assets.load((String)"sounds/swish.ogg", Sound.class).loaded = a -> {
            swish = (Sound)a;
        };
        Core.assets.load((String)"sounds/shotgun.ogg", Sound.class).loaded = a -> {
            shotgun = (Sound)a;
        };
        Core.assets.load((String)"sounds/flame2.ogg", Sound.class).loaded = a -> {
            flame2 = (Sound)a;
        };
        Core.assets.load((String)"sounds/shield.ogg", Sound.class).loaded = a -> {
            shield = (Sound)a;
        };
        Core.assets.load((String)"sounds/release.ogg", Sound.class).loaded = a -> {
            release = (Sound)a;
        };
        Core.assets.load((String)"sounds/artillery.ogg", Sound.class).loaded = a -> {
            artillery = (Sound)a;
        };
        Core.assets.load((String)"sounds/buttonClick.ogg", Sound.class).loaded = a -> {
            buttonClick = (Sound)a;
        };
        Core.assets.load((String)"sounds/break.ogg", Sound.class).loaded = a -> {
            breaks = (Sound)a;
        };
        Core.assets.load((String)"sounds/cutter.ogg", Sound.class).loaded = a -> {
            cutter = (Sound)a;
        };
        Core.assets.load((String)"sounds/door.ogg", Sound.class).loaded = a -> {
            door = (Sound)a;
        };
        Core.assets.load((String)"sounds/place.ogg", Sound.class).loaded = a -> {
            place = (Sound)a;
        };
        Core.assets.load((String)"sounds/build.ogg", Sound.class).loaded = a -> {
            build = (Sound)a;
        };
        Core.assets.load((String)"sounds/explosionbig.ogg", Sound.class).loaded = a -> {
            explosionbig = (Sound)a;
        };
        Core.assets.load((String)"sounds/bang.ogg", Sound.class).loaded = a -> {
            bang = (Sound)a;
        };
        Core.assets.load((String)"sounds/windowHide.ogg", Sound.class).loaded = a -> {
            windowHide = (Sound)a;
        };
        Core.assets.load((String)"sounds/smelter.ogg", Sound.class).loaded = a -> {
            smelter = (Sound)a;
        };
        Core.assets.load((String)"sounds/wave.ogg", Sound.class).loaded = a -> {
            wave = (Sound)a;
        };
        Core.assets.load((String)"sounds/hum.ogg", Sound.class).loaded = a -> {
            hum = (Sound)a;
        };
        Core.assets.load((String)"sounds/conveyor.ogg", Sound.class).loaded = a -> {
            conveyor = (Sound)a;
        };
        Core.assets.load((String)"sounds/laser.ogg", Sound.class).loaded = a -> {
            laser = (Sound)a;
        };
        Core.assets.load((String)"sounds/laserblast.ogg", Sound.class).loaded = a -> {
            laserblast = (Sound)a;
        };
        Core.assets.load((String)"sounds/pulse.ogg", Sound.class).loaded = a -> {
            pulse = (Sound)a;
        };
        Core.assets.load((String)"sounds/techloop.ogg", Sound.class).loaded = a -> {
            techloop = (Sound)a;
        };
        Core.assets.load((String)"sounds/plasmaboom.ogg", Sound.class).loaded = a -> {
            plasmaboom = (Sound)a;
        };
        Core.assets.load((String)"sounds/missile.ogg", Sound.class).loaded = a -> {
            missile = (Sound)a;
        };
        Core.assets.load((String)"sounds/boom.ogg", Sound.class).loaded = a -> {
            boom = (Sound)a;
        };
        Core.assets.load((String)"sounds/grinding.ogg", Sound.class).loaded = a -> {
            grinding = (Sound)a;
        };
        Core.assets.load((String)"sounds/drill.ogg", Sound.class).loaded = a -> {
            drill = (Sound)a;
        };
        Core.assets.load((String)"sounds/spray.ogg", Sound.class).loaded = a -> {
            spray = (Sound)a;
        };
        Core.assets.load((String)"sounds/splash.ogg", Sound.class).loaded = a -> {
            splash = (Sound)a;
        };
        Core.assets.load((String)"sounds/ui/press.ogg", Sound.class).loaded = a -> {
            press = (Sound)a;
        };
        Core.assets.load((String)"sounds/ui/back.ogg", Sound.class).loaded = a -> {
            back = (Sound)a;
        };
        Core.assets.load((String)"sounds/ui/unlock.ogg", Sound.class).loaded = a -> {
            unlock = (Sound)a;
        };
        Core.assets.load((String)"sounds/ui/message.ogg", Sound.class).loaded = a -> {
            message = (Sound)a;
        };
        Core.assets.load((String)"sounds/flame.ogg", Sound.class).loaded = a -> {
            flame = (Sound)a;
        };
        Core.assets.load((String)"sounds/minebeam.ogg", Sound.class).loaded = a -> {
            minebeam = (Sound)a;
        };
        Core.assets.load((String)"sounds/laserbig.ogg", Sound.class).loaded = a -> {
            laserbig = (Sound)a;
        };
        Core.assets.load((String)"sounds/steam.ogg", Sound.class).loaded = a -> {
            steam = (Sound)a;
        };
        Core.assets.load((String)"sounds/bigshot.ogg", Sound.class).loaded = a -> {
            bigshot = (Sound)a;
        };
        Core.assets.load((String)"sounds/wind2.ogg", Sound.class).loaded = a -> {
            wind2 = (Sound)a;
        };
    }

    public static void dispose() {
        Core.assets.unload("sounds/respawning.ogg");
        respawning = null;
        Core.assets.unload("sounds/mud.ogg");
        mud = null;
        Core.assets.unload("sounds/rain.ogg");
        rain = null;
        Core.assets.unload("sounds/beam.ogg");
        beam = null;
        Core.assets.unload("sounds/sap.ogg");
        sap = null;
        Core.assets.unload("sounds/shootSnap.ogg");
        shootSnap = null;
        Core.assets.unload("sounds/respawn.ogg");
        respawn = null;
        Core.assets.unload("sounds/pew.ogg");
        pew = null;
        Core.assets.unload("sounds/machine.ogg");
        machine = null;
        Core.assets.unload("sounds/corexplode.ogg");
        corexplode = null;
        Core.assets.unload("sounds/tractorbeam.ogg");
        tractorbeam = null;
        Core.assets.unload("sounds/lasershoot.ogg");
        lasershoot = null;
        Core.assets.unload("sounds/explosion.ogg");
        explosion = null;
        Core.assets.unload("sounds/railgun.ogg");
        railgun = null;
        Core.assets.unload("sounds/shootBig.ogg");
        shootBig = null;
        Core.assets.unload("sounds/thruster.ogg");
        thruster = null;
        Core.assets.unload("sounds/click.ogg");
        click = null;
        Core.assets.unload("sounds/windhowl.ogg");
        windhowl = null;
        Core.assets.unload("sounds/wind.ogg");
        wind = null;
        Core.assets.unload("sounds/combustion.ogg");
        combustion = null;
        Core.assets.unload("sounds/lasercharge.ogg");
        lasercharge = null;
        Core.assets.unload("sounds/noammo.ogg");
        noammo = null;
        Core.assets.unload("sounds/lasercharge2.ogg");
        lasercharge2 = null;
        Core.assets.unload("sounds/shoot.ogg");
        shoot = null;
        Core.assets.unload("sounds/plasmadrop.ogg");
        plasmadrop = null;
        Core.assets.unload("sounds/spark.ogg");
        spark = null;
        Core.assets.unload("sounds/fire.ogg");
        fire = null;
        Core.assets.unload("sounds/swish.ogg");
        swish = null;
        Core.assets.unload("sounds/shotgun.ogg");
        shotgun = null;
        Core.assets.unload("sounds/flame2.ogg");
        flame2 = null;
        Core.assets.unload("sounds/shield.ogg");
        shield = null;
        Core.assets.unload("sounds/release.ogg");
        release = null;
        Core.assets.unload("sounds/artillery.ogg");
        artillery = null;
        Core.assets.unload("sounds/buttonClick.ogg");
        buttonClick = null;
        Core.assets.unload("sounds/break.ogg");
        breaks = null;
        Core.assets.unload("sounds/cutter.ogg");
        cutter = null;
        Core.assets.unload("sounds/door.ogg");
        door = null;
        Core.assets.unload("sounds/place.ogg");
        place = null;
        Core.assets.unload("sounds/build.ogg");
        build = null;
        Core.assets.unload("sounds/explosionbig.ogg");
        explosionbig = null;
        Core.assets.unload("sounds/bang.ogg");
        bang = null;
        Core.assets.unload("sounds/windowHide.ogg");
        windowHide = null;
        Core.assets.unload("sounds/smelter.ogg");
        smelter = null;
        Core.assets.unload("sounds/wave.ogg");
        wave = null;
        Core.assets.unload("sounds/hum.ogg");
        hum = null;
        Core.assets.unload("sounds/conveyor.ogg");
        conveyor = null;
        Core.assets.unload("sounds/laser.ogg");
        laser = null;
        Core.assets.unload("sounds/laserblast.ogg");
        laserblast = null;
        Core.assets.unload("sounds/pulse.ogg");
        pulse = null;
        Core.assets.unload("sounds/techloop.ogg");
        techloop = null;
        Core.assets.unload("sounds/plasmaboom.ogg");
        plasmaboom = null;
        Core.assets.unload("sounds/missile.ogg");
        missile = null;
        Core.assets.unload("sounds/boom.ogg");
        boom = null;
        Core.assets.unload("sounds/grinding.ogg");
        grinding = null;
        Core.assets.unload("sounds/drill.ogg");
        drill = null;
        Core.assets.unload("sounds/spray.ogg");
        spray = null;
        Core.assets.unload("sounds/splash.ogg");
        splash = null;
        Core.assets.unload("sounds/ui/press.ogg");
        press = null;
        Core.assets.unload("sounds/ui/back.ogg");
        back = null;
        Core.assets.unload("sounds/ui/unlock.ogg");
        unlock = null;
        Core.assets.unload("sounds/ui/message.ogg");
        message = null;
        Core.assets.unload("sounds/flame.ogg");
        flame = null;
        Core.assets.unload("sounds/minebeam.ogg");
        minebeam = null;
        Core.assets.unload("sounds/laserbig.ogg");
        laserbig = null;
        Core.assets.unload("sounds/steam.ogg");
        steam = null;
        Core.assets.unload("sounds/bigshot.ogg");
        bigshot = null;
        Core.assets.unload("sounds/wind2.ogg");
        wind2 = null;
    }
}

