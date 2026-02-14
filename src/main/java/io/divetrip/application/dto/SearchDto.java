package io.divetrip.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchDto {
    private String sort;
    private String orderBy;

    @JsonIgnore
    @Builder.Default
    private List<Filter> filters = new ArrayList<>();

    @JsonIgnore
    public Sort getPageSort() {
        Sort sort;

        if (StringUtils.isNotEmpty(this.orderBy)) {
            sort = Sort.by(StringUtils.equals(this.orderBy, "asc") ? Sort.Direction.ASC : Sort.Direction.DESC, this.sort);
        } else {
            sort = Sort.by(Sort.Direction.DESC, "createdAt");
        }

        return sort;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Filter {
        private String searchColumn;
        private String searchValue;
    }

}
