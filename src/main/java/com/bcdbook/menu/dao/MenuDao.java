package com.bcdbook.menu.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.menu.pojo.Menu;

@Repository(value="menuDao")
public interface MenuDao {
	int insert(Menu menu);
}
