package com.dogpro.domain.model.extend;

import java.util.List;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.WalkingDogGroup;

public class DogLocationExtend extends DogLocation{
	private List<WalkingDogGroup> walkingDogGroups;

	public List<WalkingDogGroup> getWalkingDogGroups() {
		return walkingDogGroups;
	}

	public void setWalkingDogGroups(List<WalkingDogGroup> walkingDogGroups) {
		this.walkingDogGroups = walkingDogGroups;
	}
	
}
