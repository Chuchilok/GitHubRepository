package com.dogpro.admin.service.impl.dbservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.admin.service.dbservice.VersionControldbService;
import com.dogpro.dao.VersionControlMapper;
import com.dogpro.domain.model.VersionControl;
import com.dogpro.domain.model.VersionControlExample;
@Service("VersionControldbService")
public class VersionControldbServiceImpl implements VersionControldbService {
	@Autowired
	private VersionControlMapper versionControlMapper;
	
	public List<VersionControl> findVersionList(VersionControlExample example) {
		return versionControlMapper.selectByExample(example);
	}

	public int addVersionControl(VersionControl versionControl) {
		return versionControlMapper.insertSelective(versionControl);
	}

	public int editVersionControl(VersionControl versionControl) {
		return versionControlMapper.updateByPrimaryKeySelective(versionControl);
	}

	public int delVersionControl(VersionControl versionControl) {
//		versionControl.setDevicetype(null);
//		versionControl.setVersionname(null);
//		versionControl.setDownloadurl(null);
//		versionControl.setVersionnumber(null);
//		return versionControlMapper.updateByPrimaryKeySelective(versionControl);
		return versionControlMapper.deleteByPrimaryKey(versionControl.getId());
	}

	public int getCountByExample(VersionControlExample example) {
		return versionControlMapper.countByExample(example);
	}

	public List checkVersion(VersionControlExample example) {
		List<VersionControl> list = versionControlMapper.selectByExample(example);
		if (list!=null && list.size()>0) {
			return list;
		}
		return new ArrayList<VersionControl>();
	}

}
