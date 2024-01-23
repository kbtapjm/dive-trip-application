package io.divetrip.config.converter;

import io.divetrip.dto.request.DiverRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class DiverSortRequestConverter implements Converter<String, DiverRequestDto.Sort> {

    @Override
    public DiverRequestDto.Sort convert(String source) {
        return DiverRequestDto.Sort.valueOf(source.toUpperCase());
    }

}
