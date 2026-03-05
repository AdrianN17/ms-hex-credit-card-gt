package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ExchangeConsumptionRequestData
 */

@JsonTypeName("exchangeConsumption_request_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class ExchangeConsumptionRequestData {

    private Integer installments;

    public ExchangeConsumptionRequestData() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ExchangeConsumptionRequestData(Integer installments) {
        this.installments = installments;
    }

    public ExchangeConsumptionRequestData installments(Integer installments) {
        this.installments = installments;
        return this;
    }

    /**
     * Get installments
     * minimum: 1
     * maximum: 36
     *
     * @return installments
     */
    @NotNull
    @Min(1)
    @Max(36)
    @Schema(name = "installments", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("installments")
    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExchangeConsumptionRequestData exchangeConsumptionRequestData = (ExchangeConsumptionRequestData) o;
        return Objects.equals(this.installments, exchangeConsumptionRequestData.installments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(installments);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExchangeConsumptionRequestData {\n");
        sb.append("    installments: ").append(toIndentedString(installments)).append("\n");
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

