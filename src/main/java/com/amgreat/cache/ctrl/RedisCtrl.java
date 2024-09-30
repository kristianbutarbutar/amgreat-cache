package com.amgreat.cache.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amgreat.cache.vo.AttributeVO;
import com.amgreat.cache.vo.HtmlAddVO;
import com.amgreat.cache.vo.HtmlButtonVO;
import com.amgreat.cache.vo.HtmlEditVO;
import com.amgreat.cache.vo.HtmlSearchVO;
import com.amgreat.cache.vo.HtmlViewVO;
import com.amgreat.cache.vo.ListVO;
import com.amgreat.cache.vo.PageVO;
import com.amgreat.cache.vo.TemplateVO;
import com.amgreat.cache.repository.AttributeRepository;

@RestController
public class RedisCtrl {
	
	@Autowired private RedisAdapterInterface redisAdapter;
	@Autowired private RedisListVOInterface listVOInterface;
	@Autowired private RedisPageVOInterface pageVOInterface;
	@Autowired private RedisObjectInterface objectInterface; 
	@Autowired private RedisHtml html;
	
	@RequestMapping( "/api/amgreat/cache/cmd" )
	public AttributeVO getCacheS( @RequestBody AttributeVO o) {
		return o = redisAdapter.getCache( o, o.getCmdName() );
	}
	
	@RequestMapping( "/api/amgreat/cache/vo" )
	public ListVO getCacheS( @RequestBody ListVO o) {
		return o = listVOInterface.getCache( o, o.getCmdName() );
	}
	
	@RequestMapping( "/api/amgreat/cache/page" )
	public PageVO getCacheS( @RequestBody PageVO o) {
		return o = pageVOInterface.getCache( o, o.getCmdName() );
	}
	
	@RequestMapping( "/api/amgreat/cache/generalpage/html" )
	public PageVO getCacheHtml( @RequestBody PageVO o ) {
		
		HtmlSearchVO h = new HtmlSearchVO();
		h.setId( o.getId() );
		h.setHtml( o.getHtml() );
		h.setViewType( o.getViewType() );
		h.setCmdName( o.getCmdName() );
		
		System.out.println("in cache service: " + h.getId() );
		
		o = html.generalCache( h , h.getCmdName() );
		
		return o;
	}
	
	@RequestMapping( "/api/amgreat/cache/template" )
	public TemplateVO getCacheS( @RequestBody TemplateVO o) {
		return o = objectInterface.getCache( o, o.getCmdName(), o.getId() );
	}
}
