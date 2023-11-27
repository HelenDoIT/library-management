package com.mylibrary.dao.impl;

import com.mylibrary.domain.LendSerial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
class LendSerialDaoImplTest {

    @Mock
    private LendSerialDaoImpl lendSerialDaoMock;
    @Spy
    private LendSerialDaoImpl lendSerialDao;
    @Captor
    private ArgumentCaptor<LendSerial> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        LendSerial lendSerial = new LendSerial(null,1L,22L,1,0,null,null);
        //Below will real call database
        Mockito.when(lendSerialDao.save(lendSerial)).thenReturn(1);
        Assertions.assertEquals(1,lendSerialDao.save(lendSerial));

        Mockito.when(lendSerialDaoMock.save(lendSerial)).thenReturn(1);
        Assertions.assertEquals(1,lendSerialDaoMock.save(lendSerial));
    }

    @Test
    void updateLendStatus() {
        LendSerial lendSerial = new LendSerial();
        lendSerial.setSerialNo(1L);
        lendSerial.setLendStatus(1);

//        Mockito.when(lendSerialDao.updateLendStatus(lendSerial.getSerialNo(),lendSerial.getLendStatus())).thenReturn(1);
//        Assertions.assertEquals(1,lendSerialDao.updateLendStatus(lendSerial.getSerialNo(),lendSerial.getLendStatus()));

        Mockito.when(lendSerialDaoMock.updateLendStatus(lendSerial.getSerialNo(),lendSerial.getLendStatus())).thenReturn(1);
        Assertions.assertEquals(1,lendSerialDaoMock.updateLendStatus(lendSerial.getSerialNo(),lendSerial.getLendStatus()));

    }

    @Test
    void queryOne() {
    }

    @Test
    void queryByUserId() {
        List<LendSerial> lendSerialList = new ArrayList<>();
        lendSerialList.add(new LendSerial(null,1L,22L,1,0,null,null));
        Mockito.when(lendSerialDao.queryByUserId(1L)).thenReturn(lendSerialList);
        Assertions.assertEquals(22L,lendSerialDao.queryByUserId(1L).get(0).getBookId());

        Mockito.when(lendSerialDaoMock.queryByUserId(1L)).thenReturn(lendSerialList);
        Assertions.assertEquals(22L,lendSerialDaoMock.queryByUserId(1L).get(0).getBookId());
    }

    @Test
    void queryByBookIdAndStatus() {
        List<LendSerial> lendSerialList = new ArrayList<>();
        lendSerialList.add(new LendSerial(null,1L,22L,1,0,null,null));
        Mockito.when(lendSerialDao.queryByBookIdAndStatus(1L,0)).thenReturn(lendSerialList);
        Assertions.assertEquals(22L,lendSerialDao.queryByBookIdAndStatus(1L,0).get(0).getBookId());
    }
}