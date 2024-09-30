package com.amgreat.cache.ctrl;

import com.amgreat.cache.vo.PageVO;

public interface RedisPageVOInterface {
	public PageVO getCache(PageVO o, String cmd);
}
