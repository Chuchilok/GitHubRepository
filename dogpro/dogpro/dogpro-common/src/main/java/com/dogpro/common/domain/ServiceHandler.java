package com.dogpro.common.domain;

 

public abstract interface ServiceHandler {
	public abstract ResultObject handle(ParameterObject paramParameterObject);
	public abstract DataGridResult dataGridHandle(ParameterObject paramParameterObject);
}