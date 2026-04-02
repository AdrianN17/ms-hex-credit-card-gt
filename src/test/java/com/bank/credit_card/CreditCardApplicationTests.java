package com.bank.credit_card;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class CreditCardApplicationTests {

	@Test
	void contextLoads() {
        /*

          - changeSet:
      id: 004-create-payments
      author: liquibase
      changes:
        - createTable:
            tableName: Payments
            columns:
              - column:
                  name: paymentId
                  type: INT
                  autoIncrement: true
                  constraints:
                    primaryKey: true
                    nullable: false
              - column:
                  name: cardId
                  type: INT
              - column:
                  name: amount
                  type: DECIMAL(19,2)
              - column:
                  name: currency
                  type: SMALLINT
              - column:
                  name: paymentDate
                  type: TIMESTAMP
              - column:
                  name: paymentApprobationDate
                  type: TIMESTAMP
                  constraints:
                    nullable: true
              - column:
                  name: channel
                  type: SMALLINT
              - column:
                  name: createdDate
                  type: TIMESTAMP
              - column:
                  name: updatedDate
                  type: TIMESTAMP
              - column:
                  name: category
                  type: SMALLINT
              - column:
                  name: status
                  type: SMALLINT

  - changeSet:
      id: 005-create-consumptions
      author: liquibase
      changes:
        - createTable:
            tableName: Consumptions
            columns:
              - column:
                  name: consumptionId
                  type: INT
                  autoIncrement: true
                  constraints:
                    primaryKey: true
                    nullable: false
              - column:
                  name: cardId
                  type: INT
              - column:
                  name: sellerName
                  type: VARCHAR(255)
              - column:
                  name: currency
                  type: SMALLINT
              - column:
                  name: amount
                  type: DECIMAL(19,2)
              - column:
                  name: consumptionDate
                  type: TIMESTAMP
              - column:
                  name: consumptionApprobationDate
                  type: TIMESTAMP
                  constraints:
                    nullable: true
              - column:
                  name: createdDate
                  type: TIMESTAMP
              - column:
                  name: updatedDate
                  type: TIMESTAMP
              - column:
                  name: status
                  type: SMALLINT
         */
	}

}
