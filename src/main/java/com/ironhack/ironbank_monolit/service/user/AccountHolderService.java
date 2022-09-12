package com.ironhack.ironbank_monolit.service.user;

import java.util.Map;

public interface AccountHolderService {

    Map<Object, Object> findByPassAnU(String pass, String  user);
}
