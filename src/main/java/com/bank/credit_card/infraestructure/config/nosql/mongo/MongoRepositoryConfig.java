package com.bank.credit_card.infraestructure.config.nosql.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile("old")
@Configuration
@EnableMongoRepositories(
        basePackages = "com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository",
        considerNestedRepositories = true
)
public class MongoRepositoryConfig {
}
