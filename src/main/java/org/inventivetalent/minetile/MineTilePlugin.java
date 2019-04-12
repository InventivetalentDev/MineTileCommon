package org.inventivetalent.minetile;

import org.redisson.Redisson;

public interface MineTilePlugin {

	Redisson getRedis();

	MySQL getSQL();


}
