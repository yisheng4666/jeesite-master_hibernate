package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 被考评用户Dao接口
 * 
 * @author JianHui
 * @date 2017年9月6日--上午10:42:11
 *
 */

@Repository
public class AssementUserDao extends BaseDao<AssementUser> {

	/**
	 * 根据考评ID查询被考评单位
	 * 
	 * @param id
	 *            考评ID
	 * @return
	 * @author JianHui
	 */
	public List<AssementUser> findByAssement(String id) {
		return find("from AssementUser u where u.assement.id=:p1 and delFlag=:p2",
				new Parameter(id, AssementUser.DEL_FLAG_NORMAL));
	}

	/**
	 * 获得组成员数量
	 * 
	 * @author JianHui
	 * @param office
	 *            组
	 * @return
	 */
	public Integer getGroupMembersNum(Office office) {
		Integer result = Integer.parseInt(
				uniqueResult("select count(*) from AssementUser u where u.office.id=:p1", new Parameter(office.getId()))
						.toString());
		return result;
	}

	/**
	 * 根据考评和考评单位ID查询考评单位
	 * 
	 * @author JianHui
	 * @param assement
	 *            考评
	 * @param id
	 *            考评单位id
	 * @return
	 */
	public AssementUser findByAssementAndId(Assement assement, String id) {
		return getByHql("from AssementUser u where u.assement.id = :p1 and u.id = :p2 and delFlag = :p3",
				new Parameter(assement.getId(), id, AssementUser.DEL_FLAG_NORMAL));
	}

  /**
   * 根据考评和考评用户Id查询考评单位
   * 
   * @author JianHui
   * @param assement
   *            考评
   * @param id
   *            考评单位id
   * @return
   */
  public AssementUser findByAssementAndUserId(Assement assement, User user) {
    return getByHql("from AssementUser u where u.assement.id = :p1 and u.user.id = :p2 and delFlag = :p3",
        new Parameter(assement.getId(), user.getId(), AssementUser.DEL_FLAG_NORMAL));
  }
	
	/**
	 * 查询考评组内的组长单位
	 * 
	 * @author JianHui
	 * @param assement
	 *            考评
	 * @param office
	 *            考评组
	 * @return
	 */
	public List<AssementUser> findGroupLeaderByAssement(Assement assement, Office office) {
		return find(
				"from AssementUser u where u.assement.id = :p1 and u.office.id = :p2 and delFlag = :p3 and isGroupLeader=1",
				new Parameter(assement.getId(), office.getId(), AssementUser.DEL_FLAG_NORMAL));
	}
}
