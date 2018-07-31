package com.dogpro.domain.model.extend;

import java.util.List;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogTrack;

public class WalkingDogGroupExtend extends WalkingDogGroup{
	private DogLocation dogLocation;
	private List<WalkingDogTrack> walkingDogTracks;
	public DogLocation getDogLocation() {
		return dogLocation;
	}
	public void setDogLocation(DogLocation dogLocation) {
		this.dogLocation = dogLocation;
	}
	public List<WalkingDogTrack> getWalkingDogTracks() {
		return walkingDogTracks;
	}
	public void setWalkingDogTracks(List<WalkingDogTrack> walkingDogTracks) {
		this.walkingDogTracks = walkingDogTracks;
	}
	
}
