package com.amgreat.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amgreat.cache.vo.AttributeVO;

@Repository
public interface AttributeRepository extends CrudRepository<AttributeVO, String> {}