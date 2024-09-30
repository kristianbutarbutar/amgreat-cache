package com.amgreat.cache.ctrl;

import com.amgreat.cache.vo.ListVO;

public interface RedisListVOInterface {
	public ListVO getCache(ListVO o, String cmd);
}
