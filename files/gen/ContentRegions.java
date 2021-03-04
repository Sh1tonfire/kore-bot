/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import mindustry.ctype.MappableContent;
import mindustry.world.Block;
import mindustry.world.blocks.campaign.Accelerator;
import mindustry.world.blocks.campaign.LaunchPad;
import mindustry.world.blocks.defense.Door;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.MendProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.Thruster;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PointDefenseTurret;
import mindustry.world.blocks.defense.turrets.TractorBeamTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.distribution.PayloadConveyor;
import mindustry.world.blocks.distribution.PayloadRouter;
import mindustry.world.blocks.distribution.StackConveyor;
import mindustry.world.blocks.environment.Cliff;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.environment.TreeBlock;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBlock;
import mindustry.world.blocks.logic.SwitchBlock;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.BurnerGenerator;
import mindustry.world.blocks.power.ImpactReactor;
import mindustry.world.blocks.power.ItemLiquidGenerator;
import mindustry.world.blocks.power.LightBlock;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerDiode;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.production.Cultivator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.Fracker;
import mindustry.world.blocks.production.GenericSmelter;
import mindustry.world.blocks.production.PayloadAcceptor;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.production.SolidPump;
import mindustry.world.blocks.units.RepairPoint;

public class ContentRegions {
    public static void loadRegions(MappableContent content) {
        int INDEX0;
        if (content instanceof BurnerGenerator) {
            ((BurnerGenerator)content).turbineRegions = new TextureRegion[2];
            for (INDEX0 = 0; INDEX0 < 2; ++INDEX0) {
                ((BurnerGenerator)content).turbineRegions[INDEX0] = Core.atlas.find("" + content.name + "-turbine" + INDEX0 + "");
            }
            ((BurnerGenerator)content).capRegion = Core.atlas.find("" + content.name + "-cap");
        }
        if (content instanceof PayloadRouter) {
            ((PayloadRouter)content).overRegion = Core.atlas.find("" + content.name + "-over");
        }
        if (content instanceof PointDefenseTurret) {
            ((PointDefenseTurret)content).baseRegion = Core.atlas.find("block-" + ((Block)content).size + "");
        }
        if (content instanceof Drill) {
            ((Drill)content).rimRegion = Core.atlas.find("" + content.name + "-rim");
            ((Drill)content).rotatorRegion = Core.atlas.find("" + content.name + "-rotator");
            ((Drill)content).topRegion = Core.atlas.find("" + content.name + "-top");
            ((Drill)content).itemRegion = Core.atlas.find("" + content.name + "-item", "drill-item-" + ((Block)content).size + "");
        }
        if (content instanceof Thruster) {
            ((Thruster)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof StaticWall) {
            ((StaticWall)content).large = Core.atlas.find("" + content.name + "-large");
        }
        if (content instanceof PayloadConveyor) {
            ((PayloadConveyor)content).topRegion = Core.atlas.find("" + content.name + "-top");
            ((PayloadConveyor)content).edgeRegion = Core.atlas.find("" + content.name + "-edge");
        }
        if (content instanceof StackConveyor) {
            ((StackConveyor)content).regions = new TextureRegion[3];
            for (INDEX0 = 0; INDEX0 < 3; ++INDEX0) {
                ((StackConveyor)content).regions[INDEX0] = Core.atlas.find("" + content.name + "-" + INDEX0 + "");
            }
            ((StackConveyor)content).edgeRegion = Core.atlas.find("" + content.name + "-edge");
            ((StackConveyor)content).stackRegion = Core.atlas.find("" + content.name + "-stack");
        }
        if (content instanceof Block) {
            ((Block)content).teamRegion = Core.atlas.find("" + content.name + "-team");
        }
        if (content instanceof Fracker) {
            ((Fracker)content).liquidRegion = Core.atlas.find("" + content.name + "-liquid");
            ((Fracker)content).rotatorRegion = Core.atlas.find("" + content.name + "-rotator");
            ((Fracker)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof PowerDiode) {
            ((PowerDiode)content).arrow = Core.atlas.find("" + content.name + "-arrow");
        }
        if (content instanceof Separator) {
            ((Separator)content).liquidRegion = Core.atlas.find("" + content.name + "-liquid");
            ((Separator)content).spinnerRegion = Core.atlas.find("" + content.name + "-spinner");
        }
        if (content instanceof Conveyor) {
            ((Conveyor)content).regions = new TextureRegion[7][4];
            for (INDEX0 = 0; INDEX0 < 7; ++INDEX0) {
                for (int INDEX1 = 0; INDEX1 < 4; ++INDEX1) {
                    ((Conveyor)content).regions[INDEX0][INDEX1] = Core.atlas.find("" + content.name + "-" + INDEX0 + "-" + INDEX1 + "");
                }
            }
        }
        if (content instanceof ImpactReactor) {
            ((ImpactReactor)content).bottomRegion = Core.atlas.find("" + content.name + "-bottom");
            ((ImpactReactor)content).plasmaRegions = new TextureRegion[4];
            for (INDEX0 = 0; INDEX0 < 4; ++INDEX0) {
                ((ImpactReactor)content).plasmaRegions[INDEX0] = Core.atlas.find("" + content.name + "-plasma-" + INDEX0 + "");
            }
        }
        if (content instanceof ItemBridge) {
            ((ItemBridge)content).endRegion = Core.atlas.find("" + content.name + "-end");
            ((ItemBridge)content).bridgeRegion = Core.atlas.find("" + content.name + "-bridge");
            ((ItemBridge)content).arrowRegion = Core.atlas.find("" + content.name + "-arrow");
        }
        if (content instanceof Cliff) {
            ((Cliff)content).cliffs = new TextureRegion[256];
            for (INDEX0 = 0; INDEX0 < 256; ++INDEX0) {
                ((Cliff)content).cliffs[INDEX0] = Core.atlas.find("cliffmask" + INDEX0 + "");
            }
        }
        if (content instanceof PowerNode) {
            ((PowerNode)content).laser = Core.atlas.find("laser");
            ((PowerNode)content).laserEnd = Core.atlas.find("laser-end");
        }
        if (content instanceof LaunchPad) {
            ((LaunchPad)content).lightRegion = Core.atlas.find("" + content.name + "-light");
            ((LaunchPad)content).podRegion = Core.atlas.find("" + content.name + "-pod", "launchpod");
        }
        if (content instanceof MendProjector) {
            ((MendProjector)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof Turret) {
            ((Turret)content).baseRegion = Core.atlas.find("" + content.name + "-base", "block-" + ((Block)content).size + "");
            ((Turret)content).heatRegion = Core.atlas.find("" + content.name + "-heat");
        }
        if (content instanceof SolidPump) {
            ((SolidPump)content).rotatorRegion = Core.atlas.find("" + content.name + "-rotator");
        }
        if (content instanceof SwitchBlock) {
            ((SwitchBlock)content).onRegion = Core.atlas.find("" + content.name + "-on");
        }
        if (content instanceof OverdriveProjector) {
            ((OverdriveProjector)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof Battery) {
            ((Battery)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof LiquidBlock) {
            ((LiquidBlock)content).liquidRegion = Core.atlas.find("" + content.name + "-liquid");
            ((LiquidBlock)content).topRegion = Core.atlas.find("" + content.name + "-top");
            ((LiquidBlock)content).bottomRegion = Core.atlas.find("" + content.name + "-bottom");
        }
        if (content instanceof GenericSmelter) {
            ((GenericSmelter)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof Accelerator) {
            ((Accelerator)content).arrowRegion = Core.atlas.find("launch-arrow");
        }
        if (content instanceof PayloadAcceptor) {
            ((PayloadAcceptor)content).topRegion = Core.atlas.find("" + content.name + "-top", "factory-top-" + ((Block)content).size + "");
            ((PayloadAcceptor)content).outRegion = Core.atlas.find("" + content.name + "-out", "factory-out-" + ((Block)content).size + "");
            ((PayloadAcceptor)content).inRegion = Core.atlas.find("" + content.name + "-in", "factory-in-" + ((Block)content).size + "");
        }
        if (content instanceof NuclearReactor) {
            ((NuclearReactor)content).topRegion = Core.atlas.find("" + content.name + "-top");
            ((NuclearReactor)content).lightsRegion = Core.atlas.find("" + content.name + "-lights");
        }
        if (content instanceof Conduit) {
            ((Conduit)content).topRegions = new TextureRegion[5];
            for (INDEX0 = 0; INDEX0 < 5; ++INDEX0) {
                ((Conduit)content).topRegions[INDEX0] = Core.atlas.find("" + content.name + "-top-" + INDEX0 + "");
            }
            ((Conduit)content).botRegions = new TextureRegion[5];
            for (INDEX0 = 0; INDEX0 < 5; ++INDEX0) {
                ((Conduit)content).botRegions[INDEX0] = Core.atlas.find("" + content.name + "-bottom-" + INDEX0 + "", "conduit-bottom-" + INDEX0 + "");
            }
        }
        if (content instanceof Cultivator) {
            ((Cultivator)content).middleRegion = Core.atlas.find("" + content.name + "-middle");
            ((Cultivator)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof Door) {
            ((Door)content).openRegion = Core.atlas.find("" + content.name + "-open");
        }
        if (content instanceof TreeBlock) {
            ((TreeBlock)content).shadow = Core.atlas.find("" + content.name + "-shadow");
        }
        if (content instanceof MassDriver) {
            ((MassDriver)content).baseRegion = Core.atlas.find("" + content.name + "-base");
        }
        if (content instanceof LightBlock) {
            ((LightBlock)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof TractorBeamTurret) {
            ((TractorBeamTurret)content).baseRegion = Core.atlas.find("block-" + ((Block)content).size + "");
            ((TractorBeamTurret)content).laser = Core.atlas.find("" + content.name + "-laser");
            ((TractorBeamTurret)content).laserEnd = Core.atlas.find("" + content.name + "-laser-end");
        }
        if (content instanceof RepairPoint) {
            ((RepairPoint)content).baseRegion = Core.atlas.find("" + content.name + "-base");
            ((RepairPoint)content).laser = Core.atlas.find("laser");
            ((RepairPoint)content).laserEnd = Core.atlas.find("laser-end");
        }
        if (content instanceof ArmoredConduit) {
            ((ArmoredConduit)content).capRegion = Core.atlas.find("" + content.name + "-cap");
        }
        if (content instanceof ForceProjector) {
            ((ForceProjector)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
        if (content instanceof ItemLiquidGenerator) {
            ((ItemLiquidGenerator)content).topRegion = Core.atlas.find("" + content.name + "-top");
            ((ItemLiquidGenerator)content).liquidRegion = Core.atlas.find("" + content.name + "-liquid");
        }
        if (content instanceof LiquidTurret) {
            ((LiquidTurret)content).liquidRegion = Core.atlas.find("" + content.name + "-liquid");
            ((LiquidTurret)content).topRegion = Core.atlas.find("" + content.name + "-top");
        }
    }
}

