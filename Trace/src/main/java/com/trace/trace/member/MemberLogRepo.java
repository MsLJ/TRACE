package com.trace.trace.member;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLogRepo extends CrudRepository<MemberLog, Integer> {

	public abstract List<MemberLog> findAllByOrderByLogDateDesc();
	
	public abstract List<MemberLog>findAll();
}
