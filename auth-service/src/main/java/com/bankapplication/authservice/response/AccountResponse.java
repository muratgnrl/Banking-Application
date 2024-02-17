package com.bankapplication.authservice.response;

import com.bankapplication.authservice.dto.AccountDto;
import java.io.Serializable;

public class AccountResponse extends BaseResponse implements Serializable {
    public AccountDto account;
}
