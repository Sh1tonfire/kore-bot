/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.util.io.Reads;
import arc.util.io.Writes;

public interface Entityc {
    public boolean isAdded();

    public void update();

    public void remove();

    public void add();

    public boolean isLocal();

    public boolean isRemote();

    public boolean isNull();

    public <T extends Entityc> T self();

    public <T> T as();

    public <T> T with(Cons<T> var1);

    public int classId();

    public boolean serialize();

    public void read(Reads var1);

    public void write(Writes var1);

    public void afterRead();

    public int id();

    public void id(int var1);
}

