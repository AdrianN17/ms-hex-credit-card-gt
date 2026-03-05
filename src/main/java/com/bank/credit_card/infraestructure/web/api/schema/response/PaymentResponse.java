package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * PaymentResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class PaymentResponse {

    private String channel;

    private String currency;

    private BigDecimal amount;

    private String category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime paymentDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime paymentApprobationDate;

    public PaymentResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public PaymentResponse(String channel, String currency, BigDecimal amount, String category, OffsetDateTime paymentDate, OffsetDateTime paymentApprobationDate) {
        this.channel = channel;
        this.currency = currency;
        this.amount = amount;
        this.category = category;
        this.paymentDate = paymentDate;
        this.paymentApprobationDate = paymentApprobationDate;
    }

    public PaymentResponse channel(String channel) {
        this.channel = channel;
        return this;
    }

    /**
     * Get channel
     *
     * @return channel
     */
    @NotNull
    @Pattern(regexp = "^(WEB|APP)$")
    @Schema(name = "channel", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public PaymentResponse currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Get currency
     *
     * @return currency
     */
    @NotNull
    @Pattern(regexp = "^(PEN|USD)$")
    @Schema(name = "currency", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentResponse amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     *
     * @return amount
     */
    @NotNull
    @Valid
    @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentResponse category(String category) {
        this.category = category;
        return this;
    }

    /**
     * Get category
     *
     * @return category
     */
    @NotNull
    @Pattern(regexp = "^(MES|ADELANTADO|TOTAL)$")
    @Schema(name = "category", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PaymentResponse paymentDate(OffsetDateTime paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    /**
     * Get paymentDate
     *
     * @return paymentDate
     */
    @NotNull
    @Valid
    @Schema(name = "paymentDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("paymentDate")
    public OffsetDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(OffsetDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentResponse paymentApprobationDate(OffsetDateTime paymentApprobationDate) {
        this.paymentApprobationDate = paymentApprobationDate;
        return this;
    }

    /**
     * Get paymentApprobationDate
     *
     * @return paymentApprobationDate
     */
    @NotNull
    @Valid
    @Schema(name = "paymentApprobationDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("paymentApprobationDate")
    public OffsetDateTime getPaymentApprobationDate() {
        return paymentApprobationDate;
    }

    public void setPaymentApprobationDate(OffsetDateTime paymentApprobationDate) {
        this.paymentApprobationDate = paymentApprobationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentResponse paymentResponse = (PaymentResponse) o;
        return Objects.equals(this.channel, paymentResponse.channel) &&
                Objects.equals(this.currency, paymentResponse.currency) &&
                Objects.equals(this.amount, paymentResponse.amount) &&
                Objects.equals(this.category, paymentResponse.category) &&
                Objects.equals(this.paymentDate, paymentResponse.paymentDate) &&
                Objects.equals(this.paymentApprobationDate, paymentResponse.paymentApprobationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel, currency, amount, category, paymentDate, paymentApprobationDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentResponse {\n");
        sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
        sb.append("    paymentApprobationDate: ").append(toIndentedString(paymentApprobationDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

