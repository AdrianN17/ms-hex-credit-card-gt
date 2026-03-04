package com.bank.credit_card.infraestructure.web.api.schema.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CardResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-04T15:10:48.324834300-05:00[America/Lima]")
public class CardResponse {

  private String typeCard;

  private String categoryCard;

  private CardResponseBenefit benefit;

  private CardResponseBalance balance;

  private CardResponseAccount account;

  public CardResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CardResponse(String typeCard, String categoryCard, CardResponseBenefit benefit, CardResponseBalance balance, CardResponseAccount account) {
    this.typeCard = typeCard;
    this.categoryCard = categoryCard;
    this.benefit = benefit;
    this.balance = balance;
    this.account = account;
  }

  public CardResponse typeCard(String typeCard) {
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

  public CardResponse categoryCard(String categoryCard) {
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

  public CardResponse benefit(CardResponseBenefit benefit) {
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
  public CardResponseBenefit getBenefit() {
    return benefit;
  }

  public void setBenefit(CardResponseBenefit benefit) {
    this.benefit = benefit;
  }

  public CardResponse balance(CardResponseBalance balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @NotNull @Valid 
  @Schema(name = "balance", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("balance")
  public CardResponseBalance getBalance() {
    return balance;
  }

  public void setBalance(CardResponseBalance balance) {
    this.balance = balance;
  }

  public CardResponse account(CardResponseAccount account) {
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
  public CardResponseAccount getAccount() {
    return account;
  }

  public void setAccount(CardResponseAccount account) {
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
    CardResponse cardResponse = (CardResponse) o;
    return Objects.equals(this.typeCard, cardResponse.typeCard) &&
        Objects.equals(this.categoryCard, cardResponse.categoryCard) &&
        Objects.equals(this.benefit, cardResponse.benefit) &&
        Objects.equals(this.balance, cardResponse.balance) &&
        Objects.equals(this.account, cardResponse.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeCard, categoryCard, benefit, balance, account);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardResponse {\n");
    sb.append("    typeCard: ").append(toIndentedString(typeCard)).append("\n");
    sb.append("    categoryCard: ").append(toIndentedString(categoryCard)).append("\n");
    sb.append("    benefit: ").append(toIndentedString(benefit)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

