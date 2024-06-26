package org.yyym.back;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yyym.back.mapper.CartoonMapper;
import org.yyym.back.mapper.TagMapper;
import org.yyym.back.mapper.multi.AdminTableInfoMapper;
import org.yyym.back.mapper.multi.Category_sMapper;
import org.yyym.back.mapper.multi.PlayInfoMapper;
import org.yyym.back.serve.DataService;
import org.yyym.back.util.entity.Tag;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackApplicationTests {
	@Autowired
	private DataService dataService;

	@Autowired
	private PlayInfoMapper playInfoMapper;

	@Autowired
	private Category_sMapper categorySMapper;

	@Autowired
	private CartoonMapper cartoonMapper;

	@Autowired
	private AdminTableInfoMapper adminTableInfoMapper;

	@Autowired
	private TagMapper tagMapper;

	@Test
	void testDataGetYear() {
		System.out.println(adminTableInfoMapper.selectAllInfos());
	}

	@Test
	void test() {
		System.out.println(cartoonMapper.selectEdit("新"));
	}


}
