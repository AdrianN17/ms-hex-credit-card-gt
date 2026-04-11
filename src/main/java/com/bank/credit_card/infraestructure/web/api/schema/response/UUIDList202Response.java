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
 * UUIDList202Response
 */

@JsonTypeName("exchangeConsumption_202_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-11T09:59:29.040788600-05:00[America/Lima]")
public class UUIDList202Response {

    private Tracking tracking;

    @Valid
    private List<@Valid UUIDResponse> data = new ArrayList<>();

    public UUIDList202Response() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public UUIDList202Response(Tracking tracking, List<@Valid UUIDResponse> data) {
        this.tracking = tracking;
        this.data = data;
    }

    public UUIDList202Response tracking(Tracking tracking) {
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

    public UUIDList202Response data(List<@Valid UUIDResponse> data) {
        this.data = data;
        return this;
    }

    public UUIDList202Response addDataItem(UUIDResponse dataItem) {
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
    public List<@Valid UUIDResponse> getData() {
        return data;
    }

    public void setData(List<@Valid UUIDResponse> data) {
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
        UUIDList202Response UUIDList202Response = (UUIDList202Response) o;
        return Objects.equals(this.tracking, UUIDList202Response.tracking) &&
                Objects.equals(this.data, UUIDList202Response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracking, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UUIDList202Response {\n");
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

