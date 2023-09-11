package com.trace.trace.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo  extends CrudRepository<Report, Integer>{
	public abstract List<Report> findAll();
}
