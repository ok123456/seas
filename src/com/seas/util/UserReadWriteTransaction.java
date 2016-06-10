package com.seas.util;

import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "userReadWrite", rollbackFor = Exception.class)
public class UserReadWriteTransaction {

}
