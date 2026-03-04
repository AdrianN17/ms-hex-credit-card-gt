package com.bank.credit_card.infraestructure.web.api.schema.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CardRequestAccount
 */

@JsonTypeName("CardRequest_account")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class CardRequestAccount {

  private BigDecimal creditTotal;

  private BigDecimal debtTax;

  private String currency;

  private String paymentDate;

  public CardRequestAccount() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CardRequestAccount(BigDecimal creditTotal, BigDecimal debtTax, String currency, String paymentDate) {
    this.creditTotal = creditTotal;
    this.debtTax = debtTax;
    this.currency = currency;
    this.paymentDate = paymentDate;
  }

  public CardRequestAccount creditTotal(BigDecimal creditTotal) {
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

  public CardRequestAccount debtTax(BigDecimal debtTax) {
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

  public CardRequestAccount currency(String currency) {
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

  public CardRequestAccount paymentDate(String paymentDate) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardRequestAccount cardRequestAccount = (CardRequestAccount) o;
    return Objects.equals(this.creditTotal, cardRequestAccount.creditTotal) &&
        Objects.equals(this.debtTax, cardRequestAccount.debtTax) &&
        Objects.equals(this.currency, cardRequestAccount.currency) &&
        Objects.equals(this.paymentDate, cardRequestAccount.paymentDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditTotal, debtTax, currency, paymentDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardRequestAccount {\n");
    sb.append("    creditTotal: ").append(toIndentedString(creditTotal)).append("\n");
    sb.append("    debtTax: ").append(toIndentedString(debtTax)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
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

