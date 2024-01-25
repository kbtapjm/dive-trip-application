package io.divetrip.config.converter;

import io.divetrip.dto.request.DiverRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class DiverSortRequestConverter implements Converter<String, DiverRequestDto.Sort> {

    @Override
    public DiverRequestDto.Sort convert(String source) {
        // TODO: ENUM값을 찾을때는 대소문자 사이 '-' 구분자 연결해서 찾아야 에러 발생하지 않음
        return DiverRequestDto.Sort.valueOf(source.toUpperCase());
    }

}
