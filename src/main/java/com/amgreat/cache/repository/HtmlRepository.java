package com.amgreat.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amgreat.cache.vo.HtmlSearchVO;

@Repository
public interface HtmlRepository extends CrudRepository<HtmlSearchVO, String> {} //CrudRepository<HtmlSearchVO, String> {}
