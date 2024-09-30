package com.amgreat.cache.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.cache.vo.AttributeVO;
import com.amgreat.cache.repository.AttributeRepository;

@Component
public class RedisAdapter implements RedisAdapterInterface{

	@Autowired
	private AttributeRepository redisRepo;
	
	public AttributeVO getCache(AttributeVO o, String cmd) {
		if( o != null && cmd != null && !cmd.trim().equals("") ) {
			try {
				switch( cmd ) {
					case "s":
						o = redisRepo.findById( o.getId() ).get(); o.setCmdStatus("200"); break;
					case "i": 
						if(o.getId()!=null && !o.getId().trim().equals("")) {o.setCmdName("s"); redisRepo.save(o); o.setCmdStatus("200");} break;
					case "d": 
						redisRepo.deleteById( o.getId() ); o.setCmdStatus("200"); break;
					default:{
						o.setCmdStatus("500");
					}
				}
			} catch (Exception e) {
				System.out.println("[RedisAdapter.getCache.AttributeVO]:" + e.getMessage() );
			}
		}
		return o;
	}
}
