package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * CardResponseBalance
 */

@JsonTypeName("CardResponse_balance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class CardResponseBalance {

    private BigDecimal totalAmount;

    private BigDecimal oldAmount;

    private BigDecimal availableAmount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public CardResponseBalance() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CardResponseBalance(BigDecimal totalAmount, BigDecimal oldAmount, BigDecimal availableAmount, LocalDate startDate, LocalDate endDate) {
        this.totalAmount = totalAmount;
        this.oldAmount = oldAmount;
        this.availableAmount = availableAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CardResponseBalance totalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    /**
     * Get totalAmount
     *
     * @return totalAmount
     */
    @NotNull
    @Valid
    @Schema(name = "totalAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("totalAmount")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CardResponseBalance oldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
        return this;
    }

    /**
     * Get oldAmount
     *
     * @return oldAmount
     */
    @NotNull
    @Valid
    @Schema(name = "oldAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("oldAmount")
    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    public CardResponseBalance availableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
        return this;
    }

    /**
     * Get availableAmount
     *
     * @return availableAmount
     */
    @NotNull
    @Valid
    @Schema(name = "availableAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("availableAmount")
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public CardResponseBalance startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get startDate
     *
     * @return startDate
     */
    @NotNull
    @Valid
    @Schema(name = "startDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("startDate")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public CardResponseBalance endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get endDate
     *
     * @return endDate
     */
    @NotNull
    @Valid
    @Schema(name = "endDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("endDate")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardResponseBalance cardResponseBalance = (CardResponseBalance) o;
        return Objects.equals(this.totalAmount, cardResponseBalance.totalAmount) &&
                Objects.equals(this.oldAmount, cardResponseBalance.oldAmount) &&
                Objects.equals(this.availableAmount, cardResponseBalance.availableAmount) &&
                Objects.equals(this.startDate, cardResponseBalance.startDate) &&
                Objects.equals(this.endDate, cardResponseBalance.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAmount, oldAmount, availableAmount, startDate, endDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardResponseBalance {\n");
        sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
        sb.append("    oldAmount: ").append(toIndentedString(oldAmount)).append("\n");
        sb.append("    availableAmount: ").append(toIndentedString(availableAmount)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

