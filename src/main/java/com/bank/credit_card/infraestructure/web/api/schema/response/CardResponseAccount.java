package com.bank.credit_card.infraestructure.web.api.schema.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CardResponseAccount
 */

@JsonTypeName("CardResponse_account")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class CardResponseAccount {

  private BigDecimal creditTotal;

  private BigDecimal debtTax;

  private String currency;

  private String paymentDate;

  private String cardStatus;

  public CardResponseAccount() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CardResponseAccount(BigDecimal creditTotal, BigDecimal debtTax, String currency, String paymentDate, String cardStatus) {
    this.creditTotal = creditTotal;
    this.debtTax = debtTax;
    this.currency = currency;
    this.paymentDate = paymentDate;
    this.cardStatus = cardStatus;
  }

  public CardResponseAccount creditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
    return this;
  }

  /**
   * Get creditTotal
   * minimum: 0
   * maximum: 100000
   * @return creditTotal
  */
  @NotNull @Valid @DecimalMin("0") @DecimalMax("100000") 
  @Schema(name = "creditTotal", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creditTotal")
  public BigDecimal getCreditTotal() {
    return creditTotal;
  }

  public void setCreditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }

  public CardResponseAccount debtTax(BigDecimal debtTax) {
    this.debtTax = debtTax;
    return this;
  }

  /**
   * Get debtTax
   * minimum: 0
   * maximum: 1
   * @return debtTax
  */
  @NotNull @Valid @DecimalMin("0") @DecimalMax("1") 
  @Schema(name = "debtTax", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("debtTax")
  public BigDecimal getDebtTax() {
    return debtTax;
  }

  public void setDebtTax(BigDecimal debtTax) {
    this.debtTax = debtTax;
  }

  public CardResponseAccount currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  */
  @NotNull @Pattern(regexp = "^(PEN|USD)$") 
  @Schema(name = "currency", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public CardResponseAccount paymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
    return this;
  }

  /**
   * Get paymentDate
   * @return paymentDate
  */
  @NotNull @Pattern(regexp = "^(1|15|27)$") 
  @Schema(name = "paymentDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentDate")
  public String getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
  }

  public CardResponseAccount cardStatus(String cardStatus) {
    this.cardStatus = cardStatus;
    return this;
  }

  /**
   * Get cardStatus
   * @return cardStatus
  */
  @NotNull @Pattern(regexp = "^(OPERATIVA|SOBREGIRADA|DEUDOR)$") 
  @Schema(name = "cardStatus", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cardStatus")
  public String getCardStatus() {
    return cardStatus;
  }

  public void setCardStatus(String cardStatus) {
    this.cardStatus = cardStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardResponseAccount cardResponseAccount = (CardResponseAccount) o;
    return Objects.equals(this.creditTotal, cardResponseAccount.creditTotal) &&
        Objects.equals(this.debtTax, cardResponseAccount.debtTax) &&
        Objects.equals(this.currency, cardResponseAccount.currency) &&
        Objects.equals(this.paymentDate, cardResponseAccount.paymentDate) &&
        Objects.equals(this.cardStatus, cardResponseAccount.cardStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditTotal, debtTax, currency, paymentDate, cardStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardResponseAccount {\n");
    sb.append("    creditTotal: ").append(toIndentedString(creditTotal)).append("\n");
    sb.append("    debtTax: ").append(toIndentedString(debtTax)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
    sb.append("    cardStatus: ").append(toIndentedString(cardStatus)).append("\n");
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

