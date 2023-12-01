package com.mylibrary.suit;

import com.mylibrary.service.impl.BookInfoServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;


/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/12/1
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({
        "com.mylibrary.service.impl",
        "com.mylibrary.dao.impl"
})
//@SelectClasses(BookInfoServiceImpl.class)
public class RunWithAllTest {

}
