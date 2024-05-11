package org.nuclearfog.apollo.model;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * Represents an audio effect preset
 *
 * @author nuclearfog
 */
public class AudioPreset {

	public static final int MAX_NAME = 15;

	private String name;
	private int[] eq_bands;
	private int bass_level;
	private int reverb_level;

	/**
	 * @param name         preset name
	 * @param eq_bands     equalizer band level
	 * @param bass_level   bass level
	 * @param reverb_level reverb level
	 */
	public AudioPreset(String name, int[] eq_bands, int bass_level, int reverb_level) {
		this.name = name.substring(0, Math.min(name.length() - 1, MAX_NAME));
		this.eq_bands = eq_bands;
		this.bass_level = bass_level;
		this.reverb_level = reverb_level;
	}

	/**
	 * set preset name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get preset name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get equalizer band level
	 */
	public int[] getBands() {
		return Arrays.copyOf(eq_bands, eq_bands.length);
	}

	/**
	 * get bass level
	 */
	public int getBassLevel() {
		return bass_level;
	}

	/**
	 * get reverb level
	 */
	public int getReverbLevel() {
		return reverb_level;
	}


	@NonNull
	@Override
	public String toString() {
		return "name=" + name + " bass=" + bass_level + " reverb=" + reverb_level;
	}
}
