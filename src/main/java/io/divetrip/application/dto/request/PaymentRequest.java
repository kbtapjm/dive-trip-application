package io.divetrip.application.dto.request;

import io.divetrip.domain.entity.enumeration.PaymentMethod;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

public class PaymentRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @ToString
    public static class CreatePayment {
        /* 결제 방법 */
        @NotNull
        private PaymentMethod paymentMethod;

        /* 결제 금액 */
        @NotNull
        @PositiveOrZero
        private Integer paymentAmount;

        /* 결제 내용 */
        private String paymentDetails;

        /* 입금 계좌 */
        private String depositAccount;

        /* 입금자 명 */
        private String depositName;

        /* 카드 번호 */
        private String cardNumber;

        /* 카드사 명 */
        private String cardCompanyName;

        /* 카드 명의자 명 */
        private String cardHolderName;

        @AssertTrue(message = "{valid.payment.deposit-name.required}")
        public boolean isDepositName() {
            if (paymentMethod == PaymentMethod.DIRECT_TRANSFER) {
                return StringUtils.isNotEmpty(this.depositName);
            }

            return true;
        }

        @AssertTrue(message = "{valid.payment.card-number.required}")
        public boolean isCardNumber() {
            if (paymentMethod == PaymentMethod.CREDIT_CARD) {
                return StringUtils.isNotEmpty(this.cardNumber);
            }

            return true;
        }

        @AssertTrue(message = "{valid.payment.card-company-name.required}")
        public boolean isCardCompanyName() {
            if (paymentMethod == PaymentMethod.CREDIT_CARD) {
                return StringUtils.isNotEmpty(this.cardCompanyName);
            }

            return true;
        }

        @AssertTrue(message = "{valid.payment.card-holder-name.required}")
        public boolean isCardHolderName() {
            if (paymentMethod == PaymentMethod.CREDIT_CARD) {
                return StringUtils.isNotEmpty(this.cardHolderName);
            }

            return true;
        }
    }

}
