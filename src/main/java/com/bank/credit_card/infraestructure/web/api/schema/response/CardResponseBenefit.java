package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CardResponseBenefit
 */

@JsonTypeName("CardResponse_benefit")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class CardResponseBenefit {

    private Boolean hasDiscount;

    private BigDecimal multiplierPoints;

    private Integer totalPoint;

    public CardResponseBenefit() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CardResponseBenefit(Boolean hasDiscount, BigDecimal multiplierPoints, Integer totalPoint) {
        this.hasDiscount = hasDiscount;
        this.multiplierPoints = multiplierPoints;
        this.totalPoint = totalPoint;
    }

    public CardResponseBenefit hasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
        return this;
    }

    /**
     * Get hasDiscount
     *
     * @return hasDiscount
     */
    @NotNull
    @Schema(name = "hasDiscount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("hasDiscount")
    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public CardResponseBenefit multiplierPoints(BigDecimal multiplierPoints) {
        this.multiplierPoints = multiplierPoints;
        return this;
    }

    /**
     * Get multiplierPoints
     * minimum: 0
     * maximum: 1
     *
     * @return multiplierPoints
     */
    @NotNull
    @Valid
    @DecimalMin("0")
    @DecimalMax("1")
    @Schema(name = "multiplierPoints", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("multiplierPoints")
    public BigDecimal getMultiplierPoints() {
        return multiplierPoints;
    }

    public void setMultiplierPoints(BigDecimal multiplierPoints) {
        this.multiplierPoints = multiplierPoints;
    }

    public CardResponseBenefit totalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
        return this;
    }

    /**
     * Get totalPoint
     *
     * @return totalPoint
     */
    @NotNull
    @Schema(name = "totalPoint", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("totalPoint")
    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardResponseBenefit cardResponseBenefit = (CardResponseBenefit) o;
        return Objects.equals(this.hasDiscount, cardResponseBenefit.hasDiscount) &&
                Objects.equals(this.multiplierPoints, cardResponseBenefit.multiplierPoints) &&
                Objects.equals(this.totalPoint, cardResponseBenefit.totalPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasDiscount, multiplierPoints, totalPoint);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardResponseBenefit {\n");
        sb.append("    hasDiscount: ").append(toIndentedString(hasDiscount)).append("\n");
        sb.append("    multiplierPoints: ").append(toIndentedString(multiplierPoints)).append("\n");
        sb.append("    totalPoint: ").append(toIndentedString(totalPoint)).append("\n");
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

