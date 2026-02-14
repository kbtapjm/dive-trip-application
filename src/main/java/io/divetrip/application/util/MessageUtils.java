package io.divetrip.application.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtils {

    private final MessageSourceAccessor messageSourceAccessor;

    public String getMessage(String code) {
        return messageSourceAccessor.getMessage(code, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, String[] args) {
        return messageSourceAccessor.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
