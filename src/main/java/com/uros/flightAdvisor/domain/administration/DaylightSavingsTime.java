package com.uros.flightAdvisor.domain.administration;

public enum DaylightSavingsTime {

	E("Europe"), A("US/Canada"), S("South America"), O("Australia"), Z("New Zealand"), N("None"), U("Unknown");

	private String text;

	DaylightSavingsTime(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static DaylightSavingsTime fromString(String text) {
		for (DaylightSavingsTime daylightSavingsTime : DaylightSavingsTime.values()) {
			if (daylightSavingsTime.toString().equalsIgnoreCase(text)
					|| daylightSavingsTime.text.equalsIgnoreCase(text)) {
				return daylightSavingsTime;
			}
		}
		return null;
	}
}
