package com.yanliang.service.impl;

import com.yanliang.dao.AccountDao;
import com.yanliang.dao.impl.JdbcAccountDaoImpl;
import com.yanliang.factory.BeanFactory;
import com.yanliang.pojo.Account;
import com.yanliang.service.TransferService;

public class TransferServiceImpl implements TransferService {

//    private AccountDao accountDao = new JdbcAccountDaoImpl();

//    // 改造为通过Bean工厂获取对象
//    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    // 最佳状态
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(to);
//            int i = 1/0;
            accountDao.updateAccountByCardNo(from);
    }
}
