package com.mnrc.core.repositories;

import com.mnrc.core.AbstractCoreTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountRepositoryTest extends AbstractCoreTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void testNotNull(){
        Assert.assertTrue(null != bankAccountRepository);
    }

}
