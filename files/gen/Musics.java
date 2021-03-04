/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.audio.Music;

public class Musics {
    public static Music menu = new Music();
    public static Music boss2 = new Music();
    public static Music game6 = new Music();
    public static Music launch = new Music();
    public static Music game5 = new Music();
    public static Music game9 = new Music();
    public static Music game1 = new Music();
    public static Music editor = new Music();
    public static Music game8 = new Music();
    public static Music game2 = new Music();
    public static Music game3 = new Music();
    public static Music boss1 = new Music();
    public static Music land = new Music();
    public static Music game7 = new Music();
    public static Music game4 = new Music();

    public static void load() {
        Core.assets.load((String)"music/menu.mp3", Music.class).loaded = a -> {
            menu = (Music)a;
        };
        Core.assets.load((String)"music/boss2.mp3", Music.class).loaded = a -> {
            boss2 = (Music)a;
        };
        Core.assets.load((String)"music/game6.mp3", Music.class).loaded = a -> {
            game6 = (Music)a;
        };
        Core.assets.load((String)"music/launch.mp3", Music.class).loaded = a -> {
            launch = (Music)a;
        };
        Core.assets.load((String)"music/game5.mp3", Music.class).loaded = a -> {
            game5 = (Music)a;
        };
        Core.assets.load((String)"music/game9.mp3", Music.class).loaded = a -> {
            game9 = (Music)a;
        };
        Core.assets.load((String)"music/game1.mp3", Music.class).loaded = a -> {
            game1 = (Music)a;
        };
        Core.assets.load((String)"music/editor.mp3", Music.class).loaded = a -> {
            editor = (Music)a;
        };
        Core.assets.load((String)"music/game8.mp3", Music.class).loaded = a -> {
            game8 = (Music)a;
        };
        Core.assets.load((String)"music/game2.mp3", Music.class).loaded = a -> {
            game2 = (Music)a;
        };
        Core.assets.load((String)"music/game3.mp3", Music.class).loaded = a -> {
            game3 = (Music)a;
        };
        Core.assets.load((String)"music/boss1.mp3", Music.class).loaded = a -> {
            boss1 = (Music)a;
        };
        Core.assets.load((String)"music/land.mp3", Music.class).loaded = a -> {
            land = (Music)a;
        };
        Core.assets.load((String)"music/game7.mp3", Music.class).loaded = a -> {
            game7 = (Music)a;
        };
        Core.assets.load((String)"music/game4.mp3", Music.class).loaded = a -> {
            game4 = (Music)a;
        };
    }

    public static void dispose() {
        Core.assets.unload("music/menu.mp3");
        menu = null;
        Core.assets.unload("music/boss2.mp3");
        boss2 = null;
        Core.assets.unload("music/game6.mp3");
        game6 = null;
        Core.assets.unload("music/launch.mp3");
        launch = null;
        Core.assets.unload("music/game5.mp3");
        game5 = null;
        Core.assets.unload("music/game9.mp3");
        game9 = null;
        Core.assets.unload("music/game1.mp3");
        game1 = null;
        Core.assets.unload("music/editor.mp3");
        editor = null;
        Core.assets.unload("music/game8.mp3");
        game8 = null;
        Core.assets.unload("music/game2.mp3");
        game2 = null;
        Core.assets.unload("music/game3.mp3");
        game3 = null;
        Core.assets.unload("music/boss1.mp3");
        boss1 = null;
        Core.assets.unload("music/land.mp3");
        land = null;
        Core.assets.unload("music/game7.mp3");
        game7 = null;
        Core.assets.unload("music/game4.mp3");
        game4 = null;
    }
}

