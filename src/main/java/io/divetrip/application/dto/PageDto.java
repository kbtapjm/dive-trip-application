package io.divetrip.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PageDto {
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;

    @Builder
    public PageDto(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber - 1;
        this.pageSize = pageSize;
    }

    public void setPage(int pageNumber, int pageSize, long totalElements, int totalPages) {
        this.pageNumber = pageNumber + 1;
        this.pageSize = pageSize;
        this.totalElements = (int) totalElements;
        this.totalPages = totalPages;
    }

    public void setPage(long totalElements, int totalPages) {
        this.pageNumber = this.pageNumber + 1;
        this.totalElements = (int) totalElements;
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
