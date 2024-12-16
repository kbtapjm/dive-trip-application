package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.divetrip.domain.entity.enumeration.PaymentMethod;
import io.divetrip.domain.entity.enumeration.PaymentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Payments {
        /* 결제 ID */
        private UUID paymentId;

        /* 결제 방법 */
        private PaymentMethod paymentMethod;

        /* 결제 금액 */
        private Integer paymentAmount;

        /* 결제 일시 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime paymentDate;

        /* 결제 상태 */
        private PaymentStatus paymentStatus;

        /* 결제 내용 */
        private String paymentDetails;

        /* 결제 IP */
        private String paymentIp;

        /* 입금자 명 */
        private String depositName;

        /* 카드 번호 */
        private String cardNumber;

        /* 카드사 명 */
        private String cardCompanyName;

        /* 카드 명의자 명 */
        private String cardHolderName;

        /* 등록자 */
        private String createdBy;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;

        /* 수정자 */
        private String updatedBy;

        /* 수정일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime updatedAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Payment {
        /* 결제 ID */
        private UUID paymentId;

        /* 결제 방법 */
        private PaymentMethod paymentMethod;

        /* 결제 금액 */
        private Integer paymentAmount;

        /* 결제 일시 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime paymentDate;

        /* 결제 상태 */
        private PaymentStatus paymentStatus;

        /* 결제 내용 */
        private String paymentDetails;

        /* 결제 IP */
        private String paymentIp;

        /* 입금자 명 */
        private String depositName;

        /* 카드 번호 */
        private String cardNumber;

        /* 카드사 명 */
        private String cardCompanyName;

        /* 카드 명의자 명 */
        private String cardHolderName;

        /* 등록자 */
        private String createdBy;

        /* 등록일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime createdAt;

        /* 수정자 */
        private String updatedBy;

        /* 수정일 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        private LocalDateTime updatedAt;
    }

}
