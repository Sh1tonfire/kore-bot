/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.struct.ObjectIntMap;

public class Iconc {
    public static final char blockSpawn = '\uf8ff';
    public static final char blockDeepwater = '\uf8fe';
    public static final char blockWater = '\uf8fd';
    public static final char blockTaintedWater = '\uf8fc';
    public static final char blockDarksandTaintedWater = '\uf8fb';
    public static final char blockSandWater = '\uf8fa';
    public static final char blockDarksandWater = '\uf8f9';
    public static final char blockTar = '\uf8f8';
    public static final char blockStone = '\uf8f7';
    public static final char blockCraters = '\uf8f6';
    public static final char blockChar = '\uf8f5';
    public static final char blockIgnarock = '\uf8f4';
    public static final char blockHotrock = '\uf8f3';
    public static final char blockMagmarock = '\uf8f2';
    public static final char blockSand = '\uf8f1';
    public static final char blockDarksand = '\uf8f0';
    public static final char blockGrass = '\uf8ee';
    public static final char blockSalt = '\uf8ed';
    public static final char blockSnow = '\uf8ec';
    public static final char blockIce = '\uf8eb';
    public static final char blockIceSnow = '\uf8ea';
    public static final char blockCliffs = '\uf8e9';
    public static final char blockRock = '\uf8e6';
    public static final char blockSnowrock = '\uf8e5';
    public static final char blockSporePine = '\uf8df';
    public static final char blockSnowPine = '\uf8de';
    public static final char blockPine = '\uf8dd';
    public static final char blockShrubs = '\uf8dc';
    public static final char blockWhiteTreeDead = '\uf8db';
    public static final char blockWhiteTree = '\uf8da';
    public static final char blockSporeCluster = '\uf8d9';
    public static final char blockShale = '\uf8d8';
    public static final char blockShaleBoulder = '\uf8d6';
    public static final char blockSandBoulder = '\uf8d5';
    public static final char blockMoss = '\uf8d4';
    public static final char blockSporeMoss = '\uf8d3';
    public static final char blockMetalFloor = '\uf8d2';
    public static final char blockMetalFloorDamaged = '\uf8d1';
    public static final char blockMetalFloor2 = '\uf8d0';
    public static final char blockMetalFloor3 = '\uf8cf';
    public static final char blockMetalFloor5 = '\uf8ce';
    public static final char blockDarkPanel1 = '\uf8cd';
    public static final char blockDarkPanel2 = '\uf8cc';
    public static final char blockDarkPanel3 = '\uf8cb';
    public static final char blockDarkPanel4 = '\uf8ca';
    public static final char blockDarkPanel5 = '\uf8c9';
    public static final char blockDarkPanel6 = '\uf8c8';
    public static final char blockDarkMetal = '\uf8c7';
    public static final char blockPebbles = '\uf8c6';
    public static final char blockTendrils = '\uf8c5';
    public static final char blockOreCopper = '\uf8c4';
    public static final char blockOreLead = '\uf8c3';
    public static final char blockOreScrap = '\uf8c2';
    public static final char blockOreCoal = '\uf8c1';
    public static final char blockOreTitanium = '\uf8c0';
    public static final char blockOreThorium = '\uf8bf';
    public static final char blockGraphitePress = '\uf8be';
    public static final char blockMultiPress = '\uf8bd';
    public static final char blockSiliconSmelter = '\uf8bc';
    public static final char blockKiln = '\uf8bb';
    public static final char blockPlastaniumCompressor = '\uf8ba';
    public static final char blockPhaseWeaver = '\uf8b9';
    public static final char blockAlloySmelter = '\uf8b8';
    public static final char blockCryofluidMixer = '\uf8b7';
    public static final char blockBlastMixer = '\uf8b6';
    public static final char blockPyratiteMixer = '\uf8b5';
    public static final char blockMelter = '\uf8b4';
    public static final char blockSeparator = '\uf8b3';
    public static final char blockSporePress = '\uf8b2';
    public static final char blockPulverizer = '\uf8b1';
    public static final char blockCoalCentrifuge = '\uf8b0';
    public static final char blockIncinerator = '\uf8af';
    public static final char blockCopperWall = '\uf8ae';
    public static final char blockCopperWallLarge = '\uf8ad';
    public static final char blockTitaniumWall = '\uf8ac';
    public static final char blockTitaniumWallLarge = '\uf8ab';
    public static final char blockPlastaniumWall = '\uf8aa';
    public static final char blockPlastaniumWallLarge = '\uf8a9';
    public static final char blockThoriumWall = '\uf8a8';
    public static final char blockThoriumWallLarge = '\uf8a7';
    public static final char blockPhaseWall = '\uf8a6';
    public static final char blockPhaseWallLarge = '\uf8a5';
    public static final char blockSurgeWall = '\uf8a4';
    public static final char blockSurgeWallLarge = '\uf8a3';
    public static final char blockDoor = '\uf8a2';
    public static final char blockDoorLarge = '\uf8a1';
    public static final char blockScrapWall = '\uf8a0';
    public static final char blockScrapWallLarge = '\uf89f';
    public static final char blockScrapWallHuge = '\uf89e';
    public static final char blockScrapWallGigantic = '\uf89d';
    public static final char blockThruster = '\uf89c';
    public static final char blockMender = '\uf89b';
    public static final char blockMendProjector = '\uf89a';
    public static final char blockOverdriveProjector = '\uf899';
    public static final char blockForceProjector = '\uf898';
    public static final char blockShockMine = '\uf897';
    public static final char blockConveyor = '\uf896';
    public static final char blockTitaniumConveyor = '\uf895';
    public static final char blockArmoredConveyor = '\uf894';
    public static final char blockJunction = '\uf893';
    public static final char blockBridgeConveyor = '\uf892';
    public static final char blockPhaseConveyor = '\uf891';
    public static final char blockSorter = '\uf890';
    public static final char blockInvertedSorter = '\uf88f';
    public static final char blockRouter = '\uf88e';
    public static final char blockDistributor = '\uf88d';
    public static final char blockOverflowGate = '\uf88c';
    public static final char blockMassDriver = '\uf88b';
    public static final char blockMechanicalPump = '\uf88a';
    public static final char blockRotaryPump = '\uf889';
    public static final char blockThermalPump = '\uf888';
    public static final char blockConduit = '\uf887';
    public static final char blockPulseConduit = '\uf886';
    public static final char blockPlatedConduit = '\uf885';
    public static final char blockLiquidRouter = '\uf884';
    public static final char blockLiquidTank = '\uf883';
    public static final char blockLiquidJunction = '\uf882';
    public static final char blockBridgeConduit = '\uf881';
    public static final char blockPhaseConduit = '\uf880';
    public static final char blockPowerNode = '\uf87f';
    public static final char blockPowerNodeLarge = '\uf87e';
    public static final char blockSurgeTower = '\uf87d';
    public static final char blockDiode = '\uf87c';
    public static final char blockBattery = '\uf87b';
    public static final char blockBatteryLarge = '\uf87a';
    public static final char blockCombustionGenerator = '\uf879';
    public static final char blockThermalGenerator = '\uf878';
    public static final char blockSteamGenerator = '\uf877';
    public static final char blockDifferentialGenerator = '\uf876';
    public static final char blockRtgGenerator = '\uf875';
    public static final char blockSolarPanel = '\uf874';
    public static final char blockSolarPanelLarge = '\uf873';
    public static final char blockThoriumReactor = '\uf872';
    public static final char blockImpactReactor = '\uf871';
    public static final char blockMechanicalDrill = '\uf870';
    public static final char blockPneumaticDrill = '\uf86f';
    public static final char blockLaserDrill = '\uf86e';
    public static final char blockBlastDrill = '\uf86d';
    public static final char blockWaterExtractor = '\uf86c';
    public static final char blockCultivator = '\uf86b';
    public static final char blockOilExtractor = '\uf86a';
    public static final char blockCoreShard = '\uf869';
    public static final char blockCoreFoundation = '\uf868';
    public static final char blockCoreNucleus = '\uf867';
    public static final char blockVault = '\uf866';
    public static final char blockContainer = '\uf865';
    public static final char blockUnloader = '\uf864';
    public static final char blockLaunchPad = '\uf863';
    public static final char blockLaunchPadLarge = '\uf862';
    public static final char blockDuo = '\uf861';
    public static final char blockScatter = '\uf860';
    public static final char blockScorch = '\uf85f';
    public static final char blockHail = '\uf85e';
    public static final char blockWave = '\uf85d';
    public static final char blockLancer = '\uf85c';
    public static final char blockArc = '\uf85b';
    public static final char blockSwarmer = '\uf85a';
    public static final char blockSalvo = '\uf859';
    public static final char blockFuse = '\uf858';
    public static final char blockRipple = '\uf857';
    public static final char blockCyclone = '\uf856';
    public static final char blockSpectre = '\uf855';
    public static final char blockMeltdown = '\uf854';
    public static final char blockDraugFactory = '\uf853';
    public static final char blockSpiritFactory = '\uf852';
    public static final char blockPhantomFactory = '\uf851';
    public static final char blockCommandCenter = '\uf850';
    public static final char blockWraithFactory = '\uf84f';
    public static final char blockGhoulFactory = '\uf84e';
    public static final char blockRevenantFactory = '\uf84d';
    public static final char blockDaggerFactory = '\uf84c';
    public static final char blockCrawlerFactory = '\uf84b';
    public static final char blockTitanFactory = '\uf84a';
    public static final char blockFortressFactory = '\uf849';
    public static final char blockRepairPoint = '\uf848';
    public static final char blockDartMechPad = '\uf847';
    public static final char blockDeltaMechPad = '\uf846';
    public static final char blockTauMechPad = '\uf845';
    public static final char blockOmegaMechPad = '\uf844';
    public static final char blockJavelinShipPad = '\uf843';
    public static final char blockTridentShipPad = '\uf842';
    public static final char blockGlaiveShipPad = '\uf841';
    public static final char blockPowerSource = '\uf840';
    public static final char blockPowerVoid = '\uf83f';
    public static final char blockItemSource = '\uf83e';
    public static final char blockItemVoid = '\uf83d';
    public static final char blockLiquidSource = '\uf83c';
    public static final char blockLiquidVoid = '\uf83b';
    public static final char blockMessage = '\uf83a';
    public static final char blockIlluminator = '\uf839';
    public static final char itemCopper = '\uf838';
    public static final char itemLead = '\uf837';
    public static final char itemMetaglass = '\uf836';
    public static final char itemGraphite = '\uf835';
    public static final char itemSand = '\uf834';
    public static final char itemCoal = '\uf833';
    public static final char itemTitanium = '\uf832';
    public static final char itemThorium = '\uf831';
    public static final char itemScrap = '\uf830';
    public static final char itemSilicon = '\uf82f';
    public static final char itemPlastanium = '\uf82e';
    public static final char itemPhaseFabric = '\uf82d';
    public static final char itemSurgeAlloy = '\uf82c';
    public static final char itemSporePod = '\uf82b';
    public static final char itemBlastCompound = '\uf82a';
    public static final char itemPyratite = '\uf829';
    public static final char liquidWater = '\uf828';
    public static final char liquidSlag = '\uf827';
    public static final char liquidOil = '\uf826';
    public static final char liquidCryofluid = '\uf825';
    public static final char blockUnderflowGate = '\uf824';
    public static final char blockDartShipPad = '\uf823';
    public static final char blockAlphaMechPad = '\uf822';
    public static final char blockCliff = '\uf821';
    public static final char blockLegacyMechPad = '\uf820';
    public static final char blockGroundFactory = '\uf81f';
    public static final char blockLegacyUnitFactory = '\uf81e';
    public static final char blockMassConveyor = '\uf81d';
    public static final char blockLegacyCommandCenter = '\uf81c';
    public static final char blockBlockForge = '\uf81b';
    public static final char blockBlockLauncher = '\uf81a';
    public static final char blockPlastaniumConveyor = '\uf819';
    public static final char crater = '\uf818';
    public static final char blockNavalFactory = '\uf817';
    public static final char blockAirFactory = '\uf816';
    public static final char blockBasicReconstructor = '\uf815';
    public static final char blockBlockLoader = '\uf814';
    public static final char blockBlockUnloader = '\uf813';
    public static final char blockCoreSilo = '\uf812';
    public static final char blockDataProcessor = '\uf811';
    public static final char blockPayloadRouter = '\uf810';
    public static final char blockSiliconCrucible = '\uf80f';
    public static final char blockSegment = '\uf80e';
    public static final char blockLargeOverdriveProjector = '\uf80d';
    public static final char blockDisassembler = '\uf80c';
    public static final char blockAdvancedReconstructor = '\uf80b';
    public static final char blockReconstructorBasis = '\uf80a';
    public static final char blockReconstructorMorphism = '\uf809';
    public static final char blockReconstructorFunctor = '\uf808';
    public static final char blockReconstructorPrime = '\uf807';
    public static final char blockAdditiveReconstructor = '\uf806';
    public static final char blockMultiplicativeReconstructor = '\uf805';
    public static final char blockExponentialReconstructor = '\uf804';
    public static final char blockTetrativeReconstructor = '\uf803';
    public static final char blockResupplyPoint = '\uf802';
    public static final char blockParallax = '\uf801';
    public static final char unitDagger = '\uf800';
    public static final char unitMace = '\uf7ff';
    public static final char unitFortress = '\uf7fe';
    public static final char unitNova = '\uf7fd';
    public static final char unitPulsar = '\uf7fc';
    public static final char unitQuasar = '\uf7fb';
    public static final char unitCrawler = '\uf7fa';
    public static final char unitAtrax = '\uf7f9';
    public static final char unitSpiroct = '\uf7f8';
    public static final char unitArkyid = '\uf7f7';
    public static final char unitFlare = '\uf7f6';
    public static final char unitHorizon = '\uf7f5';
    public static final char unitZenith = '\uf7f4';
    public static final char unitAntumbra = '\uf7f3';
    public static final char unitEclipse = '\uf7f2';
    public static final char unitMono = '\uf7f1';
    public static final char unitPoly = '\uf7f0';
    public static final char unitMega = '\uf7ef';
    public static final char unitRisse = '\uf7ee';
    public static final char unitMinke = '\uf7ed';
    public static final char unitBryde = '\uf7ec';
    public static final char unitAlpha = '\uf7eb';
    public static final char unitBeta = '\uf7ea';
    public static final char unitGamma = '\uf7e9';
    public static final char unitBlock = '\uf7e8';
    public static final char unitRisso = '\uf7e7';
    public static final char blockOverdriveDome = '\uf7e6';
    public static final char blockLogicProcessor = '\uf7e5';
    public static final char blockMicroProcessor = '\uf7e4';
    public static final char blockLogicDisplay = '\uf7e3';
    public static final char blockSwitch = '\uf7e2';
    public static final char blockMemoryCell = '\uf7e1';
    public static final char blockPayloadConveyor = '\uf7e0';
    public static final char blockHyperProcessor = '\uf7df';
    public static final char unitToxopid = '\uf7de';
    public static final char unitVestige = '\uf7dd';
    public static final char unitCataclyst = '\uf7dc';
    public static final char unitScepter = '\uf7db';
    public static final char unitReign = '\uf7da';
    public static final char blockDirt = '\uf7d9';
    public static final char blockStoneWall = '\uf7d7';
    public static final char blockSporeWall = '\uf7d6';
    public static final char blockIceWall = '\uf7d5';
    public static final char blockSnowWall = '\uf7d4';
    public static final char blockDuneWall = '\uf7d3';
    public static final char blockSandWall = '\uf7d2';
    public static final char blockSaltWall = '\uf7d1';
    public static final char blockShaleWall = '\uf7d0';
    public static final char blockDirtWall = '\uf7cf';
    public static final char blockBasalt = '\uf7cd';
    public static final char blockDacite = '\uf7cc';
    public static final char blockBoulder = '\uf7cb';
    public static final char blockSnowBoulder = '\uf7ca';
    public static final char blockDaciteWall = '\uf7c9';
    public static final char blockDaciteBoulder = '\uf7c8';
    public static final char blockLargeLogicDisplay = '\uf7c7';
    public static final char unitOmura = '\uf7c6';
    public static final char blockMud = '\uf7c5';
    public static final char unitSei = '\uf7c4';
    public static final char unitQuad = '\uf7c3';
    public static final char unitOct = '\uf7c2';
    public static final char unitVela = '\uf7c1';
    public static final char unitCorvus = '\uf7c0';
    public static final char blockMemoryBank = '\uf7bf';
    public static final char blockForeshadow = '\uf7be';
    public static final char blockTsunami = '\uf7bd';
    public static final char blockSpace = '\uf7bc';
    public static final char blockLegacyUnitFactoryAir = '\uf7bb';
    public static final char blockLegacyUnitFactoryGround = '\uf7ba';
    public static final char blockInterplanetaryAccelerator = '\uf7b9';
    public static final char blockBasaltBoulder = '\uf7b8';
    public static final ObjectIntMap<String> codes = new ObjectIntMap();
    public static final char fileTextFill = '\uf15c';
    public static final char file = '\uf15b';
    public static final char fileText = '\uf0f6';
    public static final char left = '\ue802';
    public static final char right = '\ue803';
    public static final char up = '\ue804';
    public static final char down = '\ue805';
    public static final char home = '\ue807';
    public static final char ok = '\ue800';
    public static final char image = '\ue808';
    public static final char star = '\ue809';
    public static final char resize = '\ue80b';
    public static final char wrench = '\ue80f';
    public static final char githubSquare = '\uf300';
    public static final char fileImage = '\uf1c5';
    public static final char add = '\ue813';
    public static final char edit = '\ue816';
    public static final char chartBar = '\ue819';
    public static final char planeOutline = '\ue81a';
    public static final char filter = '\uf0b0';
    public static final char folder = '\ue81d';
    public static final char steam = '\ue822';
    public static final char downOpen = '\ue824';
    public static final char leftOpen = '\ue825';
    public static final char upOpen = '\ue826';
    public static final char map = '\ue827';
    public static final char rotate = '\ue823';
    public static final char play = '\ue829';
    public static final char pause = '\ue806';
    public static final char list = '\ue811';
    public static final char cancel = '\ue815';
    public static final char move = '\ue818';
    public static final char terminal = '\uf120';
    public static final char undo = '\ue835';
    public static final char redo = '\ue836';
    public static final char info = '\uf129';
    public static final char infoCircle = '\ue837';
    public static final char rightOpenOut = '\ue839';
    public static final char rightOpen = '\ue83a';
    public static final char waves = '\ue83b';
    public static final char filters = '\ue83e';
    public static final char layers = '\ue83f';
    public static final char eraser = '\uf12d';
    public static final char bookOpen = '\ue801';
    public static final char grid = '\uf029';
    public static final char flipX = '\ue812';
    public static final char flipY = '\ue842';
    public static final char diagonal = '\ue844';
    public static final char discord = '\ue80d';
    public static final char box = '\ue81e';
    public static final char redditAlien = '\uf281';
    public static final char github = '\uf308';
    public static final char googleplay = '\ue83d';
    public static final char android = '\ue845';
    public static final char trello = '\uf181';
    public static final char logic = '\ue80e';
    public static final char distribution = '\ue814';
    public static final char hammer = '\ue817';
    public static final char save = '\ue81b';
    public static final char link = '\ue81c';
    public static final char itchio = '\ue82a';
    public static final char line = '\ue82b';
    public static final char admin = '\ue82c';
    public static final char spray1 = '\ue82d';
    public static final char crafting = '\ue830';
    public static final char fill = '\ue84c';
    public static final char paste = '\ue852';
    public static final char effect = '\ue853';
    public static final char book = '\ue85b';
    public static final char liquid = '\ue85c';
    public static final char host = '\ue85d';
    public static final char production = '\ue85e';
    public static final char exit = '\ue85f';
    public static final char modePvp = '\ue861';
    public static final char modeAttack = '\ue865';
    public static final char refresh1 = '\ue867';
    public static final char none = '\ue868';
    public static final char pencil = '\ue869';
    public static final char refresh = '\ue86a';
    public static final char modeSurvival = '\ue86b';
    public static final char commandRally = '\ue86c';
    public static final char units = '\ue86d';
    public static final char commandAttack = '\ue86e';
    public static final char trash = '\ue86f';
    public static final char chat = '\ue870';
    public static final char turret = '\ue871';
    public static final char players = '\ue872';
    public static final char editor = '\ue873';
    public static final char copy = '\ue874';
    public static final char tree = '\ue875';
    public static final char lockOpen = '\ue876';
    public static final char pick = '\ue877';
    public static final char export = '\ue878';
    public static final char download = '\ue879';
    public static final char upload = '\ue87b';
    public static final char settings = '\ue87c';
    public static final char spray = '\ue87d';
    public static final char zoom = '\ue88a';
    public static final char powerOld = '\ue88b';
    public static final char power = '\ue810';
    public static final char menu = '\ue88c';
    public static final char lock = '\ue88d';
    public static final char eye = '\ue88e';
    public static final char eyeOff = '\ue88f';
    public static final char warning = '\u26a0';
    public static final char terrain = '\ue864';
    public static final char defense = '\ue84d';
    public static final String all = "\uf15c\uf15b\uf0f6\ue802\ue803\ue804\ue805\ue807\ue800\ue808\ue809\ue80b\ue80f\uf300\uf1c5\ue813\ue816\ue819\ue81a\uf0b0\ue81d\ue822\ue824\ue825\ue826\ue827\ue823\ue829\ue806\ue811\ue815\ue818\uf120\ue835\ue836\uf129\ue837\ue839\ue83a\ue83b\ue83e\ue83f\uf12d\ue801\uf029\ue812\ue842\ue844\ue80d\ue81e\uf281\uf308\ue83d\ue845\uf181\ue80e\ue814\ue817\ue81b\ue81c\ue82a\ue82b\ue82c\ue82d\ue830\ue84c\ue852\ue853\ue85b\ue85c\ue85d\ue85e\ue85f\ue861\ue865\ue867\ue868\ue869\ue86a\ue86b\ue86c\ue86d\ue86e\ue86f\ue870\ue871\ue872\ue873\ue874\ue875\ue876\ue877\ue878\ue879\ue87b\ue87c\ue87d\ue88a\ue88b\ue810\ue88c\ue88d\ue88e\ue88f\u26a0\ue864\ue84d";

    static {
        codes.put("fileTextFill", 61788);
        codes.put("file", 61787);
        codes.put("fileText", 61686);
        codes.put("left", 59394);
        codes.put("right", 59395);
        codes.put("up", 59396);
        codes.put("down", 59397);
        codes.put("home", 59399);
        codes.put("ok", 59392);
        codes.put("image", 59400);
        codes.put("star", 59401);
        codes.put("resize", 59403);
        codes.put("wrench", 59407);
        codes.put("githubSquare", 62208);
        codes.put("fileImage", 61893);
        codes.put("add", 59411);
        codes.put("edit", 59414);
        codes.put("chartBar", 59417);
        codes.put("planeOutline", 59418);
        codes.put("filter", 61616);
        codes.put("folder", 59421);
        codes.put("steam", 59426);
        codes.put("downOpen", 59428);
        codes.put("leftOpen", 59429);
        codes.put("upOpen", 59430);
        codes.put("map", 59431);
        codes.put("rotate", 59427);
        codes.put("play", 59433);
        codes.put("pause", 59398);
        codes.put("list", 59409);
        codes.put("cancel", 59413);
        codes.put("move", 59416);
        codes.put("terminal", 61728);
        codes.put("undo", 59445);
        codes.put("redo", 59446);
        codes.put("info", 61737);
        codes.put("infoCircle", 59447);
        codes.put("rightOpenOut", 59449);
        codes.put("rightOpen", 59450);
        codes.put("waves", 59451);
        codes.put("filters", 59454);
        codes.put("layers", 59455);
        codes.put("eraser", 61741);
        codes.put("bookOpen", 59393);
        codes.put("grid", 61481);
        codes.put("flipX", 59410);
        codes.put("flipY", 59458);
        codes.put("diagonal", 59460);
        codes.put("discord", 59405);
        codes.put("box", 59422);
        codes.put("redditAlien", 62081);
        codes.put("github", 62216);
        codes.put("googleplay", 59453);
        codes.put("android", 59461);
        codes.put("trello", 61825);
        codes.put("logic", 59406);
        codes.put("distribution", 59412);
        codes.put("hammer", 59415);
        codes.put("save", 59419);
        codes.put("link", 59420);
        codes.put("itchio", 59434);
        codes.put("line", 59435);
        codes.put("admin", 59436);
        codes.put("spray1", 59437);
        codes.put("crafting", 59440);
        codes.put("fill", 59468);
        codes.put("paste", 59474);
        codes.put("effect", 59475);
        codes.put("book", 59483);
        codes.put("liquid", 59484);
        codes.put("host", 59485);
        codes.put("production", 59486);
        codes.put("exit", 59487);
        codes.put("modePvp", 59489);
        codes.put("modeAttack", 59493);
        codes.put("refresh1", 59495);
        codes.put("none", 59496);
        codes.put("pencil", 59497);
        codes.put("refresh", 59498);
        codes.put("modeSurvival", 59499);
        codes.put("commandRally", 59500);
        codes.put("units", 59501);
        codes.put("commandAttack", 59502);
        codes.put("trash", 59503);
        codes.put("chat", 59504);
        codes.put("turret", 59505);
        codes.put("players", 59506);
        codes.put("editor", 59507);
        codes.put("copy", 59508);
        codes.put("tree", 59509);
        codes.put("lockOpen", 59510);
        codes.put("pick", 59511);
        codes.put("export", 59512);
        codes.put("download", 59513);
        codes.put("upload", 59515);
        codes.put("settings", 59516);
        codes.put("spray", 59517);
        codes.put("zoom", 59530);
        codes.put("powerOld", 59531);
        codes.put("power", 59408);
        codes.put("menu", 59532);
        codes.put("lock", 59533);
        codes.put("eye", 59534);
        codes.put("eyeOff", 59535);
        codes.put("warning", 9888);
        codes.put("terrain", 59492);
        codes.put("defense", 59469);
    }
}

