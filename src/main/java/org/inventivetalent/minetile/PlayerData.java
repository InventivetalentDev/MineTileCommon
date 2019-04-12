package org.inventivetalent.minetile;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {

//	public CompoundTag nbt;

//	public PlayerData(CompoundTag nbt) {
//		this.nbt = nbt;
//	}

	public PlayerData() {
	}

		@Expose public Map<String, Object> abilities  = new HashMap<>();
	@Expose public     Map<String, Object> attributes = new HashMap<>();

	// Player
	@Expose public boolean allowFlight;
	@Expose public boolean flying;
	@Expose public float flySpeed;
	@Expose public float walkSpeed;
	@Expose public float exhaustion;
	@Expose public float exp;
	@Expose public int level;
	@Expose public int totalExperience;
	@Expose public float saturation;
	@Expose public int foodLevel;
	@Expose public int gameMode;
	@Expose public boolean sprinting;
	@Expose public boolean sneaking;

	@Expose public String inventory;// Base64 String

	// LivingEntity
	@Expose public int remainingAir;
	@Expose public int maximumAir;
	@Expose public boolean gliding;
	@Expose public boolean swimming;

	// Damageable
	@Expose public double health;

	// Entity
	@Expose public float fallDistance;
	@Expose public boolean glowing;
	@Expose public boolean invulnerable;

//	@Expose public float   absorbtionAmount;
//	@Expose public short   air;
//	@Expose public float   fallDistance;
//	@Expose public short   fire;
//	@Expose public float   foodExhaustionLevel;
//	@Expose public int     foodLevel;
//	@Expose public float   foodSaturationLevel;
//	@Expose public int     foodTickTimer;
//	@Expose public float   health;
//	@Expose public short   hurtTime;
//	@Expose public boolean invulnerable;
//	@Expose public boolean onGround;
//	@Expose public int     playerGameType;
//	@Expose public int     portalCooldown;
//	@Expose public int     score;
//	@Expose public boolean seenCredits;
//	@Expose public int selectedItemSlot;
//	@Expose public boolean sleeping;// not sure how this would be true, lol
//	@Expose public short sleepTimer;
//	@Expose public int xpLevel;
//	@Expose public float xpProgress;
//	@Expose public int xpTotal;
//	@Expose public int xpSeed;

	public static PlayerData fromSQL(ResultSet res) throws SQLException {
		String data = res.getString("data");
		return new Gson().fromJson(data, PlayerData.class);
	}

}
