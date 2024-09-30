package com.amgreat.cache.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.cache.vo.TemplateVO;
import com.amgreat.cache.repository.ObjectRepository;

@Component
public class RedisObject implements RedisObjectInterface{

	@Autowired private ObjectRepository repo;
	
	public TemplateVO getCache(TemplateVO o, String cmd, String pageId) {
		if( o != null && cmd != null && !cmd.trim().equals("") ) {
			try {
				switch( cmd ) {
					case "s":
						o = repo.findById(pageId).get(); break;
					case "i": 
						if(o.getId()!=null && !o.getId().trim().equals("")) repo.save(o); break;
					case "d": 
						repo.deleteById(pageId); break;
				}
			} catch (Exception e) {
				System.out.println("[RedisAdapter.getCache.T]:" + e.getMessage() );
			}
		}
		return o;
	}
	
}
