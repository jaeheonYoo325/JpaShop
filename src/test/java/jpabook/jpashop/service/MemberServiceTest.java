package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Assert;

//import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import jpabook.jpashop.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepositoryOld memberRepository;
	@Autowired EntityManager em;
	
	@Test
	//@Rollback(false)
	public void join() throws Exception {
		
		//given
		Member member = new Member();
		member.setName("yoo");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		assertEquals(member, memberRepository.findOne(saveId));
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void duplicateMemberCheck() throws Exception {
		
		//given
		Member member1 = new Member();
		member1.setName("yoo1");
		
		Member member2 = new Member();
		member2.setName("yoo1");
		
		//when
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야 한다!!!
		
		
		//then
		Assert.fail("예외가 발생해야 한다.");
	}

}
