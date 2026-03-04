package com.bank.credit_card.infraestructure.web.api.schema.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * RetrieveBalance200Response
 */

@JsonTypeName("retrieveBalance_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class RetrieveBalance200Response {

  private CardResponse data;

  private Tracking tracking;

  public RetrieveBalance200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RetrieveBalance200Response(CardResponse data, Tracking tracking) {
    this.data = data;
    this.tracking = tracking;
  }

  public RetrieveBalance200Response data(CardResponse data) {
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
  public CardResponse getData() {
    return data;
  }

  public void setData(CardResponse data) {
    this.data = data;
  }

  public RetrieveBalance200Response tracking(Tracking tracking) {
    this.tracking = tracking;
    return this;
  }

  /**
   * Get tracking
   * @return tracking
  */
  @NotNull @Valid 
  @Schema(name = "tracking", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tracking")
  public Tracking getTracking() {
    return tracking;
  }

  public void setTracking(Tracking tracking) {
    this.tracking = tracking;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetrieveBalance200Response retrieveBalance200Response = (RetrieveBalance200Response) o;
    return Objects.equals(this.data, retrieveBalance200Response.data) &&
        Objects.equals(this.tracking, retrieveBalance200Response.tracking);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, tracking);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RetrieveBalance200Response {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    tracking: ").append(toIndentedString(tracking)).append("\n");
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

