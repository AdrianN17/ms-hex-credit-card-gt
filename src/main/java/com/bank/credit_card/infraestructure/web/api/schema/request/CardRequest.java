package com.bank.credit_card.infraestructure.web.api.schema.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CardRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class CardRequest {

  private String typeCard;

  private String categoryCard;

  private CardRequestBenefit benefit;

  private CardRequestAccount account;

  public CardRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CardRequest(String typeCard, String categoryCard, CardRequestBenefit benefit, CardRequestAccount account) {
    this.typeCard = typeCard;
    this.categoryCard = categoryCard;
    this.benefit = benefit;
    this.account = account;
  }

  public CardRequest typeCard(String typeCard) {
    this.typeCard = typeCard;
    return this;
  }

  /**
   * Get typeCard
   * @return typeCard
  */
  @NotNull @Pattern(regexp = "^(VISA|MASTERCARD)$") 
  @Schema(name = "typeCard", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("typeCard")
  public String getTypeCard() {
    return typeCard;
  }

  public void setTypeCard(String typeCard) {
    this.typeCard = typeCard;
  }

  public CardRequest categoryCard(String categoryCard) {
    this.categoryCard = categoryCard;
    return this;
  }

  /**
   * Get categoryCard
   * @return categoryCard
  */
  @NotNull @Pattern(regexp = "^(NORMAL|SILVER|GOLD|PLATINUM|BLACK|SIGNATURE|INFINITY)$") 
  @Schema(name = "categoryCard", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categoryCard")
  public String getCategoryCard() {
    return categoryCard;
  }

  public void setCategoryCard(String categoryCard) {
    this.categoryCard = categoryCard;
  }

  public CardRequest benefit(CardRequestBenefit benefit) {
    this.benefit = benefit;
    return this;
  }

  /**
   * Get benefit
   * @return benefit
  */
  @NotNull @Valid 
  @Schema(name = "benefit", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("benefit")
  public CardRequestBenefit getBenefit() {
    return benefit;
  }

  public void setBenefit(CardRequestBenefit benefit) {
    this.benefit = benefit;
  }

  public CardRequest account(CardRequestAccount account) {
    this.account = account;
    return this;
  }

  /**
   * Get account
   * @return account
  */
  @NotNull @Valid 
  @Schema(name = "account", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("account")
  public CardRequestAccount getAccount() {
    return account;
  }

  public void setAccount(CardRequestAccount account) {
    this.account = account;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardRequest cardRequest = (CardRequest) o;
    return Objects.equals(this.typeCard, cardRequest.typeCard) &&
        Objects.equals(this.categoryCard, cardRequest.categoryCard) &&
        Objects.equals(this.benefit, cardRequest.benefit) &&
        Objects.equals(this.account, cardRequest.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeCard, categoryCard, benefit, account);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardRequest {\n");
    sb.append("    typeCard: ").append(toIndentedString(typeCard)).append("\n");
    sb.append("    categoryCard: ").append(toIndentedString(categoryCard)).append("\n");
    sb.append("    benefit: ").append(toIndentedString(benefit)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
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

