Gun Duel
========
Original idea is from [Codegolf Stackexchange](http://codegolf.stackexchange.com/questions/104896/the-futuristic-gun-duel). The basic rules are as follows.

**In general:**
Each player starts with an unloaded gun that can load an infinite amount of bullets and a shield with a certain amount of health.
Each turn, players will choose from one of the following actions simultaneously:
 - load a bullet into the gun (+1 ammo)
 - fire a bullet from the gun (-1 ammo)
 - defend an incoming bullet (-1 health if hit)
 - repair your shield (+1 health)

No bullet can be fired from an empty gun. If done anyway the shooting player dies. If both players choose the action fire in the same turn no one will be hit and the duel continues. An incoming bullet cannot be defended with a shield on zero health hence the defending player dies.

**Loss:**
A player got hit by a bullet.

**Draw:**
No player has been hit after 100 turns.
