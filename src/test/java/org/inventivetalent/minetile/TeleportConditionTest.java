package org.inventivetalent.minetile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TeleportConditionTest {

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ CustomTeleport.ConditionType.SMALLER, -5000 /* <= -5000 */, null, 0, -20000, 0, true },
				{ CustomTeleport.ConditionType.GREATER, 10 /* >= 10 */, null, 0, 20, 0, true },

				{ null, 0, CustomTeleport.ConditionType.SMALLER, -500 /* <= -500 */, 0, -2000, true },
				{ null, 0, CustomTeleport.ConditionType.GREATER, 1 /* >= 1 -500 */, 0, 2, true }
		});
	}

	CustomTeleport.ConditionType xType;
	int                          xIn;
	CustomTeleport.ConditionType zType;
	int                          zIn;
	int                          xTest;
	int                          zTest;
	boolean                      shouldApply;

	public TeleportConditionTest(CustomTeleport.ConditionType xType, int xIn, CustomTeleport.ConditionType zType, int zIn, int xTest, int zTest, boolean shouldApply) {
		this.xType = xType;
		this.xIn = xIn;
		this.zType = zType;
		this.zIn = zIn;
		this.xTest = xTest;
		this.zTest = zTest;
		this.shouldApply = shouldApply;
	}

	@Test
	public void test() {
		CustomTeleport tp = new CustomTeleport(
				new CustomTeleport.Condition(
						xType, xIn,
						zType, zIn
				),
				new CustomTeleport.Action()
		);

		assert tp.condition.applies(xTest, zTest) == shouldApply;
	}
}
