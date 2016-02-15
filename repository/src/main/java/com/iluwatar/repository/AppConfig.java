/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * This is the same example as in {@link App} but with annotations based 
 * configuration for Spring.
 *
 */
@EnableJpaRepositories
public class AppConfig {

  /**
   * Creation of H2 db
   * 
   * @return A new Instance of DataSource
   */
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("org.h2.Driver");
    basicDataSource.setUrl("jdbc:h2:~/databases/person");
    basicDataSource.setUsername("sa");
    basicDataSource.setPassword("sa");
    return (DataSource) basicDataSource;
  }

  /**
   * Factory to create a especific instance of Entity Manager
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
    entityManager.setDataSource(dataSource());
    entityManager.setPackagesToScan("com.iluwatar");
    entityManager.setPersistenceProvider(new HibernatePersistenceProvider());
    entityManager.setJpaProperties(jpaProperties());

    return entityManager;
  }

  /**
   * Properties for Jpa
   */
  private Properties jpaProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    return properties;
  }

  /**
   * Get transaction manager
   */
  @Bean
  public JpaTransactionManager transactionManager() throws SQLException {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  /**
   * Program entry point
   * 
   * @param args
   *          command line args
   */
  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfig.class);
    PersonRepository repository = context.getBean(PersonRepository.class);

    Person peter = new Person("Peter", "Sagan", 17);
    Person nasta = new Person("Nasta", "Kuzminova", 25);
    Person john = new Person("John", "lawrence", 35);
    Person terry = new Person("Terry", "Law", 36);

    // Add new Person records
    repository.save(peter);
    repository.save(nasta);
    repository.save(john);
    repository.save(terry);

    // Count Person records
    System.out.println("Count Person records: " + repository.count());

    // Print all records
    List<Person> persons = (List<Person>) repository.findAll();
    for (Person person : persons) {
      System.out.println(person);
    }

    // Update Person
    nasta.setName("Barbora");
    nasta.setSurname("Spotakova");
    repository.save(nasta);

    System.out.println("Find by id 2: " + repository.findOne(2L));

    // Remove record from Person
    repository.delete(2L);

    // count records
    System.out.println("Count Person records: " + repository.count());

    // find by name
    Person p = repository.findOne(new PersonSpecifications.NameEqualSpec("John"));
    System.out.println("Find by John is " + p);

    // find by age
    persons = repository.findAll(new PersonSpecifications.AgeBetweenSpec(20, 40));

    System.out.println("Find Person with age between 20,40: ");
    for (Person person : persons) {
      System.out.println(person);
    }

    context.close();

  }

}
