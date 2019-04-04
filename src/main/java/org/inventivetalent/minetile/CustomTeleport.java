package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

public class CustomTeleport {

	@Expose public Condition condition;
	@Expose public Action    action;

	public CustomTeleport() {
	}

	public CustomTeleport(Condition condition, Action action) {
		this.condition = condition;
		this.action = action;
	}

	public boolean applies(int x, int z) {
		return this.condition != null && this.action != null && this.condition.applies(x, z);
	}

	public static class Condition {

		@Expose public ConditionType typeX;
		@Expose public int           coordinateX;

		@Expose public ConditionType typeZ;
		@Expose public int           coordinateZ;

		public Condition() {
		}

		public Condition(ConditionType typeX, int coordinateX, ConditionType typeZ, int coordinateZ) {
			this.typeX = typeX;
			this.coordinateX = coordinateX;
			this.typeZ = typeZ;
			this.coordinateZ = coordinateZ;
		}

		public boolean hasX() {
			return typeX != null && typeX != ConditionType.NONE;
		}

		public boolean hasZ() {
			return typeZ != null && typeZ != ConditionType.NONE;
		}

		public boolean applies(int x, int z) {
			boolean applies = false;

			if (hasX()) {
				if (typeX == ConditionType.SMALLER) {
					applies = x <= coordinateX;
				} else if (typeX == ConditionType.GREATER) {
					applies = x >= coordinateX;
				}
			}
			if (hasZ()) {
				if (typeZ == ConditionType.SMALLER) {
					applies = z <= coordinateZ;
				} else if (typeZ == ConditionType.GREATER) {
					applies = z >= coordinateZ;
				}
			}

			return applies;
		}

	}

	public static enum ConditionType {
		NONE,
		SMALLER,
		GREATER;
	}

	public static class Action {

		@Expose public boolean hasX;
		@Expose public int     coordinateX;

		@Expose public boolean hasZ;
		@Expose public int     coordinateZ;

		public Action() {
		}

		public Action(boolean hasX, int coordinateX, boolean hasZ, int coordinateZ) {
			this.hasX = hasX;
			this.coordinateX = coordinateX;
			this.hasZ = hasZ;
			this.coordinateZ = coordinateZ;
		}
	}

}
