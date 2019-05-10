package org.inventivetalent.minetile;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void localToGlobal() {
		int tileSize = 16;

		double x = 128;
		int tileX = 4;
		double centerX = 0;

		double gX = CoordinateConverter.localToGlobal(x, tileX, tileSize, centerX,false);
		assert gX == 2176;// used in the test below
	}

	@Test
	public void globalToLocal() {
		int tileSize = 16;

		double gX = 2176;

		int tileX = 4;
		double centerX = 0;

		double x = CoordinateConverter.globalToLocal(gX, tileX, tileSize, centerX,false);
		assert x == 128;// should equal the original value from above
	}

	@Test
	public void tileTest1() {
		int tileSize = 4;
		int offset=4*16;

		assert CoordinateConverter.tile(0, tileSize, offset) == -1;
		assert CoordinateConverter.tile(64, tileSize, offset) == 0;
		assert CoordinateConverter.tile(65, tileSize, offset) == 0;
		assert CoordinateConverter.tile(128, tileSize, offset) == 0;
		assert CoordinateConverter.tile(-64, tileSize, offset) == -1;
		assert CoordinateConverter.tile(-128, tileSize, offset) == -2;
	}

	@Test
	public void tileTest2() {
		int tileSize = 4;
		int offset=0;

		assert CoordinateConverter.tile(0, tileSize, offset) == 0;
		assert CoordinateConverter.tile(64, tileSize, offset) == 0;
		assert CoordinateConverter.tile(65, tileSize, offset) == 0;
		assert CoordinateConverter.tile(128, tileSize, offset) == 1;
		assert CoordinateConverter.tile(-64, tileSize, offset) == -1;
		assert CoordinateConverter.tile(-128, tileSize, offset) == -1;
		assert CoordinateConverter.tile(192, tileSize, offset) == 1;
		assert CoordinateConverter.tile(256, tileSize, offset) == 2;
	}

}
