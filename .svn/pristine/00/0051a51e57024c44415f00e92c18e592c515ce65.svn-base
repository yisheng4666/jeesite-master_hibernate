/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.AwardedMark;


/**
 * 加分DAO接口
 * @author Lucy
 * @version 2017-10-18
 */
@Repository
public class AwardedMarkDao extends BaseDao<AwardedMark> {
  public  List<AwardedMark> findbyasmtUserandruleId(String asmUserId,String ruleid){
    return find("from AwardedMark where assementUser.id=:p1 and rule.id=:p2", new Parameter(asmUserId,ruleid));
  }
}
