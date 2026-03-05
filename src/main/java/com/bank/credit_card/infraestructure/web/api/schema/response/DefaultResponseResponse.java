package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * DefaultResponseResponse
 */

@JsonTypeName("controlCard_202_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class DefaultResponseResponse {

    private Tracking tracking;

    public DefaultResponseResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public DefaultResponseResponse(Tracking tracking) {
        this.tracking = tracking;
    }

    public DefaultResponseResponse tracking(Tracking tracking) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultResponseResponse DefaultResponseResponse = (DefaultResponseResponse) o;
        return Objects.equals(this.tracking, DefaultResponseResponse.tracking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracking);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DefaultResponseResponse {\n");
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

