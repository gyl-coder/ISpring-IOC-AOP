package com.yanliang.dao;

import com.yanliang.pojo.Account;

public interface AccountDao {

    /**
     * 通过卡号查询账户
     * @param cardNo
     * @return
     * @throws Exception
     */
    Account queryAccountByCardNo(String cardNo) throws Exception;

    /**
     * 更新账户信息
     * @param account
     * @return
     * @throws Exception
     */
    int updateAccountByCardNo(Account account) throws Exception;
}
