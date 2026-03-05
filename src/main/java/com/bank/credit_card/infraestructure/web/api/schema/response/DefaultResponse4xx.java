package com.bank.credit_card.infraestructure.web.api.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DefaultResponse4xx
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class DefaultResponse4xx {

    private String description;

    @Valid
    private List<String> errors = new ArrayList<>();

    public DefaultResponse4xx() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public DefaultResponse4xx(String description, List<String> errors) {
        this.description = description;
        this.errors = errors;
    }

    public DefaultResponse4xx description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     */
    @NotNull
    @Schema(name = "description", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DefaultResponse4xx errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public DefaultResponse4xx addErrorsItem(String errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    /**
     * Get errors
     *
     * @return errors
     */
    @NotNull
    @Schema(name = "errors", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("errors")
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultResponse4xx defaultResponse4xx = (DefaultResponse4xx) o;
        return Objects.equals(this.description, defaultResponse4xx.description) &&
                Objects.equals(this.errors, defaultResponse4xx.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DefaultResponse4xx {\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

