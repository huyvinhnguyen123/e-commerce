package com.e.commerce.application.utils.enums;

import lombok.Getter;

@Getter
public enum LockFlag {
    NON_LOCK(0),
    LOCKED(1);

    private final int code;

    LockFlag(int code) {
        this.code = code;
    }
}
