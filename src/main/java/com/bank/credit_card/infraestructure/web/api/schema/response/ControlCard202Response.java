package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ControlCard202Response
 */

@JsonTypeName("controlCard_202_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-02T01:39:00.900988800-05:00[America/Lima]")
public class ControlCard202Response {

    private Tracking tracking;

    public ControlCard202Response() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ControlCard202Response(Tracking tracking) {
        this.tracking = tracking;
    }

    public ControlCard202Response tracking(Tracking tracking) {
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
        ControlCard202Response controlCard202Response = (ControlCard202Response) o;
        return Objects.equals(this.tracking, controlCard202Response.tracking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracking);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ControlCard202Response {\n");
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

