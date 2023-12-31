package net.zeeraa.mochadoom.rr.parallel;

import net.zeeraa.mochadoom.rr.drawfuns.ColVars;

public interface RWI<T,V> {
	public interface Init<T,V>{
		RenderWallExecutor<T,V>[] InitRWIExecutors(int num,ColVars<T,V>[] RWI);
	}
	
	public interface Get<T,V>{
		ColVars<T,V>[] getRWI();
		void setExecutors(RenderWallExecutor<T,V>[] RWIExec);
	}
}
