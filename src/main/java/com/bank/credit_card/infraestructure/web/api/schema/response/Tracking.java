package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Tracking
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class Tracking {

    private UUID trackingId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime operationDate;

    public Tracking() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public Tracking(UUID trackingId, OffsetDateTime operationDate) {
        this.trackingId = trackingId;
        this.operationDate = operationDate;
    }

    public Tracking trackingId(UUID trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    /**
     * Get trackingId
     *
     * @return trackingId
     */
    @NotNull
    @Valid
    @Schema(name = "trackingId", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("trackingId")
    public UUID getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }

    public Tracking operationDate(OffsetDateTime operationDate) {
        this.operationDate = operationDate;
        return this;
    }

    /**
     * Get operationDate
     *
     * @return operationDate
     */
    @NotNull
    @Valid
    @Schema(name = "operationDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("operationDate")
    public OffsetDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(OffsetDateTime operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tracking tracking = (Tracking) o;
        return Objects.equals(this.trackingId, tracking.trackingId) &&
                Objects.equals(this.operationDate, tracking.operationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingId, operationDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Tracking {\n");
        sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
        sb.append("    operationDate: ").append(toIndentedString(operationDate)).append("\n");
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

