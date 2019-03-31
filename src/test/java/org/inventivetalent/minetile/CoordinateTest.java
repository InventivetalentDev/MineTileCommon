package org.inventivetalent.minetile;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void localToGlobal() {
		int tileSize = 16;

		double x = 128;
		int tileX = 4;
		double centerX = 0;

		double gX = CoordinateConverter.localToGlobal(x, tileX, tileSize, centerX);
		assert gX == 2176;// used in the test below
	}

	@Test
	public void globalToLocal() {
		int tileSize = 16;

		double gX = 2176;

		int tileX = 4;
		double centerX = 0;

		double x = CoordinateConverter.globalToLocal(gX, tileX, tileSize, centerX);
		assert x == 128;// should equal the original value from above
	}

}
