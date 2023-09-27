/*
 * Copyright (C) 2017 Good Sign
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.zeeraa.mochadoom.p.Actions.ActiveStates;

import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Bosses;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Demonspawns;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.HorrendousVisages;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Mancubi;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.PainsSouls;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Skels;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Spiders;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Viles;
import net.zeeraa.mochadoom.p.Actions.ActiveStates.MonsterStates.Zombies;

/**
 * Include all from Monsters package
 * 
 * @author Good Sign
 */
public interface Monsters extends
    Bosses,
    Demonspawns,
    HorrendousVisages,
    Mancubi,
    PainsSouls,
    Skels,
    Spiders,
    Viles,
    Zombies
{
    
}
