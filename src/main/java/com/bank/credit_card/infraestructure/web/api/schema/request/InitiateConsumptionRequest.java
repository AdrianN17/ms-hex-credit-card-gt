package com.bank.credit_card.infraestructure.web.api.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * InitiateConsumptionRequest
 */

@JsonTypeName("initiateConsumption_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T19:49:44.015232300-05:00[America/Lima]")
public class InitiateConsumptionRequest {

    private ConsumptionRequest data;

    public InitiateConsumptionRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public InitiateConsumptionRequest(ConsumptionRequest data) {
        this.data = data;
    }

    public InitiateConsumptionRequest data(ConsumptionRequest data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     *
     * @return data
     */
    @NotNull
    @Valid
    @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("data")
    public ConsumptionRequest getData() {
        return data;
    }

    public void setData(ConsumptionRequest data) {
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
        InitiateConsumptionRequest initiateConsumptionRequest = (InitiateConsumptionRequest) o;
        return Objects.equals(this.data, initiateConsumptionRequest.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InitiateConsumptionRequest {\n");
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

