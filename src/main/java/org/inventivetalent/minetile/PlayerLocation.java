package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

public class PlayerLocation {

	@Expose public double x;
	@Expose public double y;
	@Expose public double z;
	@Expose public float pitch;
	@Expose public float yaw;

	public PlayerLocation() {
	}

	public PlayerLocation(double x, double y, double z, float pitch, float yaw) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	@Override
	public String toString() {
		return "PlayerLocation{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				", pitch=" + pitch +
				", yaw=" + yaw +
				'}';
	}
}
