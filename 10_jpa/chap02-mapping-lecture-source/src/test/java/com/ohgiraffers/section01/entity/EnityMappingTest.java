package com.ohgiraffers.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EnityMappingTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void 테이블_만들기_테스트() {
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-5678");
        member.setEmail("hong@gmail.com");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);


        Member foundMember = entityManager.find(Member.class, 1);
        foundMember.setNickname("동해번쩍");

        transaction.commit();
    }
}
