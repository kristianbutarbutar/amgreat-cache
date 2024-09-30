package com.amgreat.cache.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.cache.vo.ListVO;
import com.amgreat.cache.repository.AttributeRepository;
import com.amgreat.cache.repository.ListRespository;

@Component
public class RedisListVO implements RedisListVOInterface {

	@Autowired private ListRespository repo;
	
	
	public ListVO getCache(ListVO o, String cmd) {
		if( o != null && cmd != null && !cmd.trim().equals("") ) {
			try {
				switch( cmd ) {
					case "s":
						o = repo.findById( o.getId() ).get();  
						break;
					case "i": 
						if(o.getId()!=null && !o.getId().trim().equals("")) {o.setCmdName("s"); repo.save(o); } break;
					case "d": 
						repo.deleteById( o.getId() ); break;
				}
			} catch (Exception e) {
				System.out.println("[RedisAdapter.getCache.ListVO]:" + e.getMessage() );
			}
		}
		return o;
	}
	
}
