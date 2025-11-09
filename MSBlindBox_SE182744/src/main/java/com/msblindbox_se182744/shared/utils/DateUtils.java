package com.msblindbox_se182744.shared.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class DateUtils {
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
