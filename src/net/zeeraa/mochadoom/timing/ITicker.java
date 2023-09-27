package net.zeeraa.mochadoom.timing;

import static net.zeeraa.mochadoom.doom.SourceCode.I_IBM.*;

import net.zeeraa.mochadoom.doom.CVarManager;
import net.zeeraa.mochadoom.doom.CommandVariable;
import net.zeeraa.mochadoom.doom.SourceCode.I_IBM;

public interface ITicker {

    static ITicker createTicker(CVarManager CVM) {
        if (CVM.bool(CommandVariable.MILLIS)) {
            return new MilliTicker();
        } else if (CVM.bool(CommandVariable.FASTTIC) || CVM.bool(CommandVariable.FASTDEMO)) {
            return new DelegateTicker();
        } else {
            return new NanoTicker();
        }
    }
    
    @I_IBM.C(I_GetTime)
    public int GetTime();
}