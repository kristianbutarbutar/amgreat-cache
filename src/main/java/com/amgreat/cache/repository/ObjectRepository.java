package com.amgreat.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amgreat.cache.vo.TemplateVO;

@Repository
public interface ObjectRepository extends CrudRepository<TemplateVO, String> {}