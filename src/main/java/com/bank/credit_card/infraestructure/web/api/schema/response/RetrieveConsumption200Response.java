package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RetrieveConsumption200Response
 */

@JsonTypeName("retrieveConsumption_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class RetrieveConsumption200Response {

    private Tracking tracking;

    @Valid
    private List<@Valid ConsumptionResponse> data = new ArrayList<>();

    public RetrieveConsumption200Response() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public RetrieveConsumption200Response(Tracking tracking, List<@Valid ConsumptionResponse> data) {
        this.tracking = tracking;
        this.data = data;
    }

    public RetrieveConsumption200Response tracking(Tracking tracking) {
        this.tracking = tracking;
        return this;
    }

    /**
     * Get tracking
     *
     * @return tracking
     */
    @NotNull
    @Valid
    @Schema(name = "tracking", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tracking")
    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public RetrieveConsumption200Response data(List<@Valid ConsumptionResponse> data) {
        this.data = data;
        return this;
    }

    public RetrieveConsumption200Response addDataItem(ConsumptionResponse dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.add(dataItem);
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
    public List<@Valid ConsumptionResponse> getData() {
        return data;
    }

    public void setData(List<@Valid ConsumptionResponse> data) {
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
        RetrieveConsumption200Response retrieveConsumption200Response = (RetrieveConsumption200Response) o;
        return Objects.equals(this.tracking, retrieveConsumption200Response.tracking) &&
                Objects.equals(this.data, retrieveConsumption200Response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracking, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RetrieveConsumption200Response {\n");
        sb.append("    tracking: ").append(toIndentedString(tracking)).append("\n");
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

