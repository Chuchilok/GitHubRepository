package com.dogpro.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.VersionControl;
import com.dogpro.domain.model.VersionControlExample;

public interface VersionControldbService {
	/**
	 * 查询   VersionControl  集合
	 * @param example
	 * @return
	 */
	public List<VersionControl> findVersionList(VersionControlExample example);
	/**
	 * 操作 VersionControl 增加
	 * @param versionControl
	 * @return
	 */
	public int addVersionControl(VersionControl versionControl);
	/**
	 * 操作 VersionControl 编辑 
	 * @param versionControl
	 * @return
	 */
	public int editVersionControl(VersionControl versionControl);
	/**
	 * 操作 VersionControl  删除
	 * @param versionControl
	 * @return
	 */
	public int delVersionControl(VersionControl versionControl);
	/**
	 * 获取总数
	 * @param example
	 * @return
	 */
	public int getCountByExample(VersionControlExample example);
	/**
	 * app检测版本更新
	 * @param example
	 */
	public List checkVersion(VersionControlExample example);
}
