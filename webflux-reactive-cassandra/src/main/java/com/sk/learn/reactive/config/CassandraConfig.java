package com.sk.learn.reactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

  @Value("${cassandra.contactpoints}")
  private String contactPoints;

  @Value("${cassandra.port}")
  private int port;

  @Value("${cassandra.keyspace}")
  private String keyspace;

  @Value("${cassandra.basepackages}")
  private String basePackages;

  @Override
  protected String getKeyspaceName() {
    return keyspace;
  }

  @Override
  protected String getContactPoints() {
    return contactPoints;
  }

  @Override
  protected int getPort() {
    return port;
  }

  @Override
  public SchemaAction getSchemaAction() {
    return SchemaAction.CREATE_IF_NOT_EXISTS;
  }

  @Override
  public String[] getEntityBasePackages() {
    return new String[] {basePackages};
  }

  /*@Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    List<CreateKeyspaceSpecification> specificationList = new ArrayList<>();
    final CreateKeyspaceSpecification specification =
        CreateKeyspaceSpecification.createKeyspace(keyspace)
            .ifNotExists()
            .with(KeyspaceOption.DURABLE_WRITES, true)
            .withSimpleReplication();
    specificationList.add(specification);
    return specificationList;
  }*/

  /*@Override
  protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
    List<DropKeyspaceSpecification> dropKeyspaceSpecifications = new ArrayList<>();
    DropKeyspaceSpecification dropKeyspaceSpecification = DropKeyspaceSpecification.dropKeyspace(keyspace);
    dropKeyspaceSpecifications.add(dropKeyspaceSpecification);
    return dropKeyspaceSpecifications;
  }*/
}
