package com.amgreat.cache.ctrl;

import com.amgreat.cache.vo.AttributeVO;

public interface RedisAdapterInterface {
	public AttributeVO getCache(AttributeVO o, String cmd);
}
