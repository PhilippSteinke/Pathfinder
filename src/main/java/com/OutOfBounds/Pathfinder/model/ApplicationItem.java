package com.OutOfBounds.Pathfinder.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class ApplicationItem {
private String name;

//@DBRef
//private String pointOfInterestId;
private int value;

public ApplicationItem(String name, int value) {
	this.name = name;
	this.value = value;
}

public int getValue() {
	return this.value;
}

public void setValue(int value) {
	this.value = value;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public boolean kindaEquals(ApplicationItem other) {
	return other.getName().equals(this.getName()) && other.getValue() == (this.getValue());
}
}
