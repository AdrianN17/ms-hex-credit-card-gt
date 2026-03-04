package com.bank.credit_card.infraestructure.web.api.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * PaymentRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T16:07:27.220146400-05:00[America/Lima]")
public class PaymentRequest {

    private String channel;

    private String currency;

    private BigDecimal amount;

    private String category;

    private Integer pointsUsed;

    public PaymentRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public PaymentRequest(String channel, String currency, BigDecimal amount, String category, Integer pointsUsed) {
        this.channel = channel;
        this.currency = currency;
        this.amount = amount;
        this.category = category;
        this.pointsUsed = pointsUsed;
    }

    public PaymentRequest channel(String channel) {
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

    public PaymentRequest currency(String currency) {
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

    public PaymentRequest amount(BigDecimal amount) {
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

    public PaymentRequest category(String category) {
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

    public PaymentRequest pointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
        return this;
    }

    /**
     * Get pointsUsed
     * minimum: 0
     * maximum: 1000
     *
     * @return pointsUsed
     */
    @NotNull
    @Min(0)
    @Max(1000)
    @Schema(name = "pointsUsed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("pointsUsed")
    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentRequest paymentRequest = (PaymentRequest) o;
        return Objects.equals(this.channel, paymentRequest.channel) &&
                Objects.equals(this.currency, paymentRequest.currency) &&
                Objects.equals(this.amount, paymentRequest.amount) &&
                Objects.equals(this.category, paymentRequest.category) &&
                Objects.equals(this.pointsUsed, paymentRequest.pointsUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel, currency, amount, category, pointsUsed);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentRequest {\n");
        sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    pointsUsed: ").append(toIndentedString(pointsUsed)).append("\n");
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

