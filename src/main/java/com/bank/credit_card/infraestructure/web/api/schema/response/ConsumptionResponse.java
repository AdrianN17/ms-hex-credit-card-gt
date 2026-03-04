package com.bank.credit_card.infraestructure.web.api.schema.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ConsumptionResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class ConsumptionResponse {

  private String sellerName;

  private String currency;

  private BigDecimal amount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime consumptionDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime consumptionApprobationDate;

  public ConsumptionResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ConsumptionResponse(String sellerName, String currency, BigDecimal amount, OffsetDateTime consumptionDate, OffsetDateTime consumptionApprobationDate) {
    this.sellerName = sellerName;
    this.currency = currency;
    this.amount = amount;
    this.consumptionDate = consumptionDate;
    this.consumptionApprobationDate = consumptionApprobationDate;
  }

  public ConsumptionResponse sellerName(String sellerName) {
    this.sellerName = sellerName;
    return this;
  }

  /**
   * Get sellerName
   * @return sellerName
  */
  @NotNull 
  @Schema(name = "sellerName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sellerName")
  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public ConsumptionResponse currency(String currency) {
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

  public ConsumptionResponse amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @NotNull @Valid 
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public ConsumptionResponse consumptionDate(OffsetDateTime consumptionDate) {
    this.consumptionDate = consumptionDate;
    return this;
  }

  /**
   * Get consumptionDate
   * @return consumptionDate
  */
  @NotNull @Valid 
  @Schema(name = "consumptionDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("consumptionDate")
  public OffsetDateTime getConsumptionDate() {
    return consumptionDate;
  }

  public void setConsumptionDate(OffsetDateTime consumptionDate) {
    this.consumptionDate = consumptionDate;
  }

  public ConsumptionResponse consumptionApprobationDate(OffsetDateTime consumptionApprobationDate) {
    this.consumptionApprobationDate = consumptionApprobationDate;
    return this;
  }

  /**
   * Get consumptionApprobationDate
   * @return consumptionApprobationDate
  */
  @NotNull @Valid 
  @Schema(name = "consumptionApprobationDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("consumptionApprobationDate")
  public OffsetDateTime getConsumptionApprobationDate() {
    return consumptionApprobationDate;
  }

  public void setConsumptionApprobationDate(OffsetDateTime consumptionApprobationDate) {
    this.consumptionApprobationDate = consumptionApprobationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsumptionResponse consumptionResponse = (ConsumptionResponse) o;
    return Objects.equals(this.sellerName, consumptionResponse.sellerName) &&
        Objects.equals(this.currency, consumptionResponse.currency) &&
        Objects.equals(this.amount, consumptionResponse.amount) &&
        Objects.equals(this.consumptionDate, consumptionResponse.consumptionDate) &&
        Objects.equals(this.consumptionApprobationDate, consumptionResponse.consumptionApprobationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sellerName, currency, amount, consumptionDate, consumptionApprobationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsumptionResponse {\n");
    sb.append("    sellerName: ").append(toIndentedString(sellerName)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    consumptionDate: ").append(toIndentedString(consumptionDate)).append("\n");
    sb.append("    consumptionApprobationDate: ").append(toIndentedString(consumptionApprobationDate)).append("\n");
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

