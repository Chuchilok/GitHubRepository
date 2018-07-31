package com.dogpro.domain.model;


import java.io.Serializable;

public class Table1 implements Serializable {
String  a1;
String a2;
/**
 * @return the a1
 */
public String getA1() {
	return a1;
}
/**
 * @param a1 the a1 to set
 */
public void setA1(String a1) {
	this.a1 = a1;
}
/**
 * @return the a2
 */
public String getA2() {
	return a2;
}
/**
 * @param a2 the a2 to set
 */
public void setA2(String a2) {
	this.a2 = a2;
}
public Table1( ) {
	super();
}
public Table1(String a1, String a2) {
	super();
	this.a1 = a1;
	this.a2 = a2;
}

}
