package com.bank.credit_card.infraestructure.web.api.schema.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ExchangeConsumptionRequest
 */

@JsonTypeName("exchangeConsumption_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class ExchangeConsumptionRequest {

  private ExchangeConsumptionRequestData data;

  public ExchangeConsumptionRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ExchangeConsumptionRequest(ExchangeConsumptionRequestData data) {
    this.data = data;
  }

  public ExchangeConsumptionRequest data(ExchangeConsumptionRequestData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @NotNull @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public ExchangeConsumptionRequestData getData() {
    return data;
  }

  public void setData(ExchangeConsumptionRequestData data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeConsumptionRequest exchangeConsumptionRequest = (ExchangeConsumptionRequest) o;
    return Objects.equals(this.data, exchangeConsumptionRequest.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeConsumptionRequest {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

