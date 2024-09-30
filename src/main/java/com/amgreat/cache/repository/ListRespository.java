package com.amgreat.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amgreat.cache.vo.ListVO;

@Repository
public interface ListRespository extends CrudRepository<ListVO, String> {}