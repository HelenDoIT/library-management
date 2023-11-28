package com.mylibrary.service.impl;

import com.mylibrary.dao.ILendSerialDao;
import com.mylibrary.dao.impl.LendSerialDaoImpl;
import com.mylibrary.domain.LendSerial;
import com.mylibrary.service.ILendSerialService;
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
 * @date: 2023/11/28
 */
class LendSerialServiceImplTest {

    @Mock
    private LendSerialDaoImpl lendSerialDao;
    @InjectMocks
    private LendSerialServiceImpl lendSerialService;
    @Captor
    private ArgumentCaptor<LendSerial> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void recodeLending() {
        LendSerial lendSerial = new LendSerial(null,1L,22L,1,0,null,null);
        Mockito.when(lendSerialDao.save(argumentCaptor.capture()));
        lendSerialService.recodeLending(lendSerial);
        Assertions.assertEquals(1L,argumentCaptor.getValue().getUserId());

    }

    @Test
    void queryByUserId() {
        List<LendSerial> mockReturnList = new ArrayList<>();
        mockReturnList.add(new LendSerial(null,1L,22L,1,0,null,null));
        Mockito.when(lendSerialDao.queryByUserId(ArgumentMatchers.anyLong())).thenReturn(mockReturnList);
        List<LendSerial> serialList = lendSerialService.queryByUserId(1L, 0);
        Assertions.assertEquals(1,serialList.size());
        Assertions.assertEquals(1L,serialList.get(0).getUserId());

    }

    @Test
    void returnBook() {
        Mockito.when(lendSerialDao.updateLendStatus(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt())).thenReturn(1);
        Assertions.assertEquals(1,lendSerialService.returnBook(6L));
    }
}