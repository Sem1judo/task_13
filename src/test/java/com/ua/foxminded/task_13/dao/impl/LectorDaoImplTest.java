package com.ua.foxminded.task_13.dao.impl;

import com.ua.foxminded.task_13.model.Lector;
import com.ua.foxminded.task_13.model.mapper.LectorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class LectorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private LectorDaoImpl lectorDao;



    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(lectorDao, "jdbcTemplate", jdbcTemplate);

    }

    @Test
    public void shouldCreateLector() {
        when(jdbcTemplate.update(eq("insert into lectors(first_name, last_name) values(?,?)"), eq("testName"), eq("testSurname")))
                .thenReturn(1);

        Lector lector = new Lector();
        lector.setFirstName("testName");
        lector.setLastName("testSurname");

        boolean isCreated = lectorDao.create(lector);
        assertTrue(isCreated);
    }

    @Test
    public void shouldUpdatedLector() {

        when(jdbcTemplate.update(eq("update lectors set first_name = ?, last_name = ? where lector_id = ?"),
                eq("testName"),
                eq("testSurname"), eq(1L)))
                .thenReturn(1);

        Lector lector = new Lector();
        lector.setFacultyId(1L);
        lector.setFirstName("testName");
        lector.setLastName("testSurname");
        lector.setLectorId(1L);
        boolean isUpdated = lectorDao.update(lector);

        assertTrue(isUpdated);
    }

    @Test
    public void shouldDeleteLector() {
        when(jdbcTemplate.update(eq("delete from lectors where lector_id = ?"),
                eq(1L))).
                thenReturn(1);

        boolean isDeleted = lectorDao.delete(1L);

        assertTrue(isDeleted);
    }

    @Test
    public void shouldReturnAppropriateFirstNameAndLastName() {
        when(jdbcTemplate.queryForObject(eq("select * from lectors where lector_id = ?")
                , eq(new Object[]{100L})
                , (LectorMapper) any())).thenReturn(getMeTestLector());

        Lector lector = lectorDao.getById(100L);
        System.out.println(lector);

        assertNotNull(lector);
        assertEquals("testName", lector.getFirstName());
        assertEquals("testSurname", lector.getLastName());
    }


    private Lector getMeTestLector() {
        Lector lector = new Lector();
        lector.setLectorId(100L);
        lector.setFirstName("testName");
        lector.setLastName("testSurname");
        return lector;
    }

}

