package com.amgreat.cache.ctrl;

import com.amgreat.cache.vo.TemplateVO;

public interface RedisObjectInterface {
	public TemplateVO getCache(TemplateVO o, String cmd, String pageId);
}
