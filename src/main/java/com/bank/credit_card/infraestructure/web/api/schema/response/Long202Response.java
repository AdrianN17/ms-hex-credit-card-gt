package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Long202Response
 */

@JsonTypeName("controlCard_202_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-11T09:59:29.040788600-05:00[America/Lima]")
public class Long202Response {

    private Tracking tracking;

    private LongResponse data;

    public Long202Response() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public Long202Response(Tracking tracking, LongResponse data) {
        this.tracking = tracking;
        this.data = data;
    }

    public Long202Response tracking(Tracking tracking) {
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

    public Long202Response data(LongResponse data) {
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
    public LongResponse getData() {
        return data;
    }

    public void setData(LongResponse data) {
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
        Long202Response long202Response = (Long202Response) o;
        return Objects.equals(this.tracking, long202Response.tracking) &&
                Objects.equals(this.data, long202Response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracking, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Long202Response {\n");
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

