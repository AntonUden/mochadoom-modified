package net.zeeraa.mochadoom.savegame;

import net.zeeraa.mochadoom.p.ThinkerList;

public interface ILoadSaveGame {
    void setThinkerList(ThinkerList li);
    void doSave(ThinkerList li);
    void doLoad(ThinkerList li);
}
