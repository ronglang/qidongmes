

package com.css.business.web.subsysplan.plaManage.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;



import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
@Repository("plaMacTaskMaterilDAO")
public class PlaMacTaskMaterilDAO extends BaseEntityDaoImpl<PlaMacTaskMateril>  {

	
	/**
	 * 
	 * @param workCode
	 * @param macCode
	 * @return
	 */
		@SuppressWarnings("unchecked")
		public ArrayList<PlaMacTaskMateril> queryByParam(String workCode,String macCode){
			String str = "select p from PlaMacTaskMateril p where  p.workcode=? and p.maccode=? ";
			List<PlaMacTaskMateril> lst = createQuery(str,workCode,macCode).list();
			ArrayList<PlaMacTaskMateril> arrlst = new ArrayList<PlaMacTaskMateril>();
			for(PlaMacTaskMateril pmtm:lst){
				arrlst.add(pmtm);
			}
			return arrlst;
		}
	
}
