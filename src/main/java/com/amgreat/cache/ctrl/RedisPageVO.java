package com.amgreat.cache.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.cache.vo.HtmlSearchVO;
import com.amgreat.cache.vo.PageVO;
import com.amgreat.cache.repository.PageRepository;


@Component
public class RedisPageVO implements RedisPageVOInterface {
	@Autowired private PageRepository repo;
	
	@Override
	public PageVO getCache(PageVO o, String cmd) {
		if( o != null && cmd != null && !cmd.trim().equals("") ) {
			try {
				switch( cmd ) {
					case "s":
						o = repo.findById( o.getId() ).get(); break;
					case "i": 
						if(o.getId()!=null && !o.getId().trim().equals("")) {o.setCmdName("s"); repo.save(o);} break;
					case "d": 
						repo.deleteById( o.getId() ); break;
				}
			} catch (Exception e) {
				System.out.println("[RedisAdapter.getCache.PageVO]:" + e.getMessage() );
			}
		}
		return o;
	}
}
