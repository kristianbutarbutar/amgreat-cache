package com.amgreat.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amgreat.cache.vo.PageVO;

@Repository
public interface PageRepository extends CrudRepository<PageVO, String> {}
