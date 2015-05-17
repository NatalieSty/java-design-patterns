package com.iluwatar;

public class PotatoPeelingTask extends Task {

	private static int TIME_PER_POTATO = 500;
	
	public PotatoPeelingTask(int numPotatoes) {
		super(numPotatoes * TIME_PER_POTATO);
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
	}
}
