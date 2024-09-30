package com.amgreat.cache.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.cache.vo.HtmlSearchVO;
import com.amgreat.cache.vo.PageVO;
import com.amgreat.cache.repository.HtmlRepository;

@Component
public class RedisHtml {
	
	@Autowired private HtmlRepository repo;

	public PageVO generalCache( HtmlSearchVO o, String cmd ) {
		PageVO ovo = new PageVO();
		if( o != null && cmd != null && !cmd.trim().equals("") ) {
			try {
				HtmlSearchVO vo = new HtmlSearchVO(); vo.setId(o.getId());
				switch( cmd ) {
					case "s":
						ovo = repo.findById( vo.getId() ).get();  
						System.out.println("id = " + ovo.getId() + ", html = " + ovo.getHtml() );
						return ovo;				
					case "i": 
						if(o.getId()!=null && !o.getId().trim().equals("")) {o.setCmdName("s"); repo.save(o);} break;
					case "d": 
						repo.deleteById( vo.getId() ); return ovo;
				}
				
			} catch (Exception e) {
				System.out.println("[RedisAdapter.generalCache.HtmlSearchVO]:" + e.getMessage() );
			}
		}
		return ovo;
	}
	
	/*
	 
	 public PageVO generalCache( HtmlSearchVO o, String cmd ) {
		PageVO ovo = new PageVO();
		if( o != null && cmd != null && !cmd.trim().equals("") ) {
			try {
				switch( cmd ) {
					case "s":
						System.out.println("RedisPageVO.generalCache.Id:" + o.getId());
						ovo = repo.findById( o.getId() ).get();  break;
					case "i": 
						if(o.getId()!=null && !o.getId().trim().equals("")) {o.setCmdName("s"); repo.save(o);} break;
					case "d": 
						System.out.println("deletetion " + o.getId() );
						repo.deleteById( o.getId() ); break;
				}
			} catch (Exception e) {
				System.out.println("[RedisAdapter.generalCache.HtmlSearchVO]:" + e.getMessage() );
			}
		}
		return ovo;
	}
	
	 * */
}
