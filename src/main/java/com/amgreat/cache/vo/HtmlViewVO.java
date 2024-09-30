package com.amgreat.cache.vo;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("HtmlView")
public class HtmlViewVO extends PageVO {}