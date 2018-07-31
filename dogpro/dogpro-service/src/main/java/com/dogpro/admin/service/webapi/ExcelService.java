package com.dogpro.admin.service.webapi;

import java.io.IOException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dogpro.common.Interfacetool.ResultObject;

public interface ExcelService {
	
	public ResultObject importExcel(CommonsMultipartFile file) throws IOException;
}
