/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Prov;
import arc.struct.Seq;
import mindustry.logic.ConditionOp;
import mindustry.logic.LAccess;
import mindustry.logic.LLocate;
import mindustry.logic.LStatement;
import mindustry.logic.LStatements;
import mindustry.logic.LUnitControl;
import mindustry.logic.LogicOp;
import mindustry.logic.RadarSort;
import mindustry.logic.RadarTarget;
import mindustry.world.blocks.logic.LogicDisplay;
import mindustry.world.meta.BlockFlag;

public class LogicIO {
    public static Seq<Prov<LStatement>> allStatements = Seq.with(LStatements.InvalidStatement::new, LStatements.ReadStatement::new, LStatements.WriteStatement::new, LStatements.DrawStatement::new, LStatements.PrintStatement::new, LStatements.DrawFlushStatement::new, LStatements.PrintFlushStatement::new, LStatements.GetLinkStatement::new, LStatements.ControlStatement::new, LStatements.RadarStatement::new, LStatements.SensorStatement::new, LStatements.SetStatement::new, LStatements.OperationStatement::new, LStatements.EndStatement::new, LStatements.JumpStatement::new, LStatements.UnitBindStatement::new, LStatements.UnitControlStatement::new, LStatements.UnitRadarStatement::new, LStatements.UnitLocateStatement::new);

    public static void write(Object obj, StringBuilder out) {
        if (obj.getClass() == LStatements.InvalidStatement.class) {
            out.append("noop");
        } else if (obj.getClass() == LStatements.ReadStatement.class) {
            out.append("read");
            out.append(" ");
            out.append(((LStatements.ReadStatement)obj).output);
            out.append(" ");
            out.append(((LStatements.ReadStatement)obj).target);
            out.append(" ");
            out.append(((LStatements.ReadStatement)obj).address);
        } else if (obj.getClass() == LStatements.WriteStatement.class) {
            out.append("write");
            out.append(" ");
            out.append(((LStatements.WriteStatement)obj).input);
            out.append(" ");
            out.append(((LStatements.WriteStatement)obj).target);
            out.append(" ");
            out.append(((LStatements.WriteStatement)obj).address);
        } else if (obj.getClass() == LStatements.DrawStatement.class) {
            out.append("draw");
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).type.name());
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).x);
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).y);
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).p1);
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).p2);
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).p3);
            out.append(" ");
            out.append(((LStatements.DrawStatement)obj).p4);
        } else if (obj.getClass() == LStatements.PrintStatement.class) {
            out.append("print");
            out.append(" ");
            out.append(((LStatements.PrintStatement)obj).value);
        } else if (obj.getClass() == LStatements.DrawFlushStatement.class) {
            out.append("drawflush");
            out.append(" ");
            out.append(((LStatements.DrawFlushStatement)obj).target);
        } else if (obj.getClass() == LStatements.PrintFlushStatement.class) {
            out.append("printflush");
            out.append(" ");
            out.append(((LStatements.PrintFlushStatement)obj).target);
        } else if (obj.getClass() == LStatements.GetLinkStatement.class) {
            out.append("getlink");
            out.append(" ");
            out.append(((LStatements.GetLinkStatement)obj).output);
            out.append(" ");
            out.append(((LStatements.GetLinkStatement)obj).address);
        } else if (obj.getClass() == LStatements.ControlStatement.class) {
            out.append("control");
            out.append(" ");
            out.append(((LStatements.ControlStatement)obj).type.name());
            out.append(" ");
            out.append(((LStatements.ControlStatement)obj).target);
            out.append(" ");
            out.append(((LStatements.ControlStatement)obj).p1);
            out.append(" ");
            out.append(((LStatements.ControlStatement)obj).p2);
            out.append(" ");
            out.append(((LStatements.ControlStatement)obj).p3);
            out.append(" ");
            out.append(((LStatements.ControlStatement)obj).p4);
        } else if (obj.getClass() == LStatements.RadarStatement.class) {
            out.append("radar");
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).target1.name());
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).target2.name());
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).target3.name());
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).sort.name());
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).radar);
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).sortOrder);
            out.append(" ");
            out.append(((LStatements.RadarStatement)obj).output);
        } else if (obj.getClass() == LStatements.SensorStatement.class) {
            out.append("sensor");
            out.append(" ");
            out.append(((LStatements.SensorStatement)obj).to);
            out.append(" ");
            out.append(((LStatements.SensorStatement)obj).from);
            out.append(" ");
            out.append(((LStatements.SensorStatement)obj).type);
        } else if (obj.getClass() == LStatements.SetStatement.class) {
            out.append("set");
            out.append(" ");
            out.append(((LStatements.SetStatement)obj).to);
            out.append(" ");
            out.append(((LStatements.SetStatement)obj).from);
        } else if (obj.getClass() == LStatements.OperationStatement.class) {
            out.append("op");
            out.append(" ");
            out.append(((LStatements.OperationStatement)obj).op.name());
            out.append(" ");
            out.append(((LStatements.OperationStatement)obj).dest);
            out.append(" ");
            out.append(((LStatements.OperationStatement)obj).a);
            out.append(" ");
            out.append(((LStatements.OperationStatement)obj).b);
        } else if (obj.getClass() == LStatements.EndStatement.class) {
            out.append("end");
        } else if (obj.getClass() == LStatements.JumpStatement.class) {
            out.append("jump");
            out.append(" ");
            out.append(((LStatements.JumpStatement)obj).destIndex);
            out.append(" ");
            out.append(((LStatements.JumpStatement)obj).op.name());
            out.append(" ");
            out.append(((LStatements.JumpStatement)obj).value);
            out.append(" ");
            out.append(((LStatements.JumpStatement)obj).compare);
        } else if (obj.getClass() == LStatements.UnitBindStatement.class) {
            out.append("ubind");
            out.append(" ");
            out.append(((LStatements.UnitBindStatement)obj).type);
        } else if (obj.getClass() == LStatements.UnitControlStatement.class) {
            out.append("ucontrol");
            out.append(" ");
            out.append(((LStatements.UnitControlStatement)obj).type.name());
            out.append(" ");
            out.append(((LStatements.UnitControlStatement)obj).p1);
            out.append(" ");
            out.append(((LStatements.UnitControlStatement)obj).p2);
            out.append(" ");
            out.append(((LStatements.UnitControlStatement)obj).p3);
            out.append(" ");
            out.append(((LStatements.UnitControlStatement)obj).p4);
            out.append(" ");
            out.append(((LStatements.UnitControlStatement)obj).p5);
        } else if (obj.getClass() == LStatements.UnitRadarStatement.class) {
            out.append("uradar");
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).target1.name());
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).target2.name());
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).target3.name());
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).sort.name());
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).radar);
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).sortOrder);
            out.append(" ");
            out.append(((LStatements.UnitRadarStatement)obj).output);
        } else if (obj.getClass() == LStatements.UnitLocateStatement.class) {
            out.append("ulocate");
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).locate.name());
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).flag.name());
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).enemy);
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).ore);
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).outX);
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).outY);
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).outFound);
            out.append(" ");
            out.append(((LStatements.UnitLocateStatement)obj).outBuild);
        }
    }

    public static LStatement read(String[] tokens) {
        if (tokens[0].equals("noop")) {
            LStatements.InvalidStatement result = new LStatements.InvalidStatement();
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("read")) {
            LStatements.ReadStatement result = new LStatements.ReadStatement();
            if (tokens.length > 1) {
                result.output = tokens[1];
            }
            if (tokens.length > 2) {
                result.target = tokens[2];
            }
            if (tokens.length > 3) {
                result.address = tokens[3];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("write")) {
            LStatements.WriteStatement result = new LStatements.WriteStatement();
            if (tokens.length > 1) {
                result.input = tokens[1];
            }
            if (tokens.length > 2) {
                result.target = tokens[2];
            }
            if (tokens.length > 3) {
                result.address = tokens[3];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("draw")) {
            LStatements.DrawStatement result = new LStatements.DrawStatement();
            if (tokens.length > 1) {
                result.type = LogicDisplay.GraphicsType.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.x = tokens[2];
            }
            if (tokens.length > 3) {
                result.y = tokens[3];
            }
            if (tokens.length > 4) {
                result.p1 = tokens[4];
            }
            if (tokens.length > 5) {
                result.p2 = tokens[5];
            }
            if (tokens.length > 6) {
                result.p3 = tokens[6];
            }
            if (tokens.length > 7) {
                result.p4 = tokens[7];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("print")) {
            LStatements.PrintStatement result = new LStatements.PrintStatement();
            if (tokens.length > 1) {
                result.value = tokens[1];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("drawflush")) {
            LStatements.DrawFlushStatement result = new LStatements.DrawFlushStatement();
            if (tokens.length > 1) {
                result.target = tokens[1];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("printflush")) {
            LStatements.PrintFlushStatement result = new LStatements.PrintFlushStatement();
            if (tokens.length > 1) {
                result.target = tokens[1];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("getlink")) {
            LStatements.GetLinkStatement result = new LStatements.GetLinkStatement();
            if (tokens.length > 1) {
                result.output = tokens[1];
            }
            if (tokens.length > 2) {
                result.address = tokens[2];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("control")) {
            LStatements.ControlStatement result = new LStatements.ControlStatement();
            if (tokens.length > 1) {
                result.type = LAccess.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.target = tokens[2];
            }
            if (tokens.length > 3) {
                result.p1 = tokens[3];
            }
            if (tokens.length > 4) {
                result.p2 = tokens[4];
            }
            if (tokens.length > 5) {
                result.p3 = tokens[5];
            }
            if (tokens.length > 6) {
                result.p4 = tokens[6];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("radar")) {
            LStatements.RadarStatement result = new LStatements.RadarStatement();
            if (tokens.length > 1) {
                result.target1 = RadarTarget.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.target2 = RadarTarget.valueOf(tokens[2]);
            }
            if (tokens.length > 3) {
                result.target3 = RadarTarget.valueOf(tokens[3]);
            }
            if (tokens.length > 4) {
                result.sort = RadarSort.valueOf(tokens[4]);
            }
            if (tokens.length > 5) {
                result.radar = tokens[5];
            }
            if (tokens.length > 6) {
                result.sortOrder = tokens[6];
            }
            if (tokens.length > 7) {
                result.output = tokens[7];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("sensor")) {
            LStatements.SensorStatement result = new LStatements.SensorStatement();
            if (tokens.length > 1) {
                result.to = tokens[1];
            }
            if (tokens.length > 2) {
                result.from = tokens[2];
            }
            if (tokens.length > 3) {
                result.type = tokens[3];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("set")) {
            LStatements.SetStatement result = new LStatements.SetStatement();
            if (tokens.length > 1) {
                result.to = tokens[1];
            }
            if (tokens.length > 2) {
                result.from = tokens[2];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("op")) {
            LStatements.OperationStatement result = new LStatements.OperationStatement();
            if (tokens.length > 1) {
                result.op = LogicOp.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.dest = tokens[2];
            }
            if (tokens.length > 3) {
                result.a = tokens[3];
            }
            if (tokens.length > 4) {
                result.b = tokens[4];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("end")) {
            LStatements.EndStatement result = new LStatements.EndStatement();
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("jump")) {
            LStatements.JumpStatement result = new LStatements.JumpStatement();
            if (tokens.length > 1) {
                result.destIndex = Integer.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.op = ConditionOp.valueOf(tokens[2]);
            }
            if (tokens.length > 3) {
                result.value = tokens[3];
            }
            if (tokens.length > 4) {
                result.compare = tokens[4];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("ubind")) {
            LStatements.UnitBindStatement result = new LStatements.UnitBindStatement();
            if (tokens.length > 1) {
                result.type = tokens[1];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("ucontrol")) {
            LStatements.UnitControlStatement result = new LStatements.UnitControlStatement();
            if (tokens.length > 1) {
                result.type = LUnitControl.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.p1 = tokens[2];
            }
            if (tokens.length > 3) {
                result.p2 = tokens[3];
            }
            if (tokens.length > 4) {
                result.p3 = tokens[4];
            }
            if (tokens.length > 5) {
                result.p4 = tokens[5];
            }
            if (tokens.length > 6) {
                result.p5 = tokens[6];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("uradar")) {
            LStatements.UnitRadarStatement result = new LStatements.UnitRadarStatement();
            if (tokens.length > 1) {
                result.target1 = RadarTarget.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.target2 = RadarTarget.valueOf(tokens[2]);
            }
            if (tokens.length > 3) {
                result.target3 = RadarTarget.valueOf(tokens[3]);
            }
            if (tokens.length > 4) {
                result.sort = RadarSort.valueOf(tokens[4]);
            }
            if (tokens.length > 5) {
                result.radar = tokens[5];
            }
            if (tokens.length > 6) {
                result.sortOrder = tokens[6];
            }
            if (tokens.length > 7) {
                result.output = tokens[7];
            }
            result.afterRead();
            return result;
        }
        if (tokens[0].equals("ulocate")) {
            LStatements.UnitLocateStatement result = new LStatements.UnitLocateStatement();
            if (tokens.length > 1) {
                result.locate = LLocate.valueOf(tokens[1]);
            }
            if (tokens.length > 2) {
                result.flag = BlockFlag.valueOf(tokens[2]);
            }
            if (tokens.length > 3) {
                result.enemy = tokens[3];
            }
            if (tokens.length > 4) {
                result.ore = tokens[4];
            }
            if (tokens.length > 5) {
                result.outX = tokens[5];
            }
            if (tokens.length > 6) {
                result.outY = tokens[6];
            }
            if (tokens.length > 7) {
                result.outFound = tokens[7];
            }
            if (tokens.length > 8) {
                result.outBuild = tokens[8];
            }
            result.afterRead();
            return result;
        }
        return null;
    }
}

