/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.jpa.producer;

//import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped; 
//import javax.enterprise.context.RequestScoped; 
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Slf4j
public class EntityManagerProducer {

  @PersistenceContext(unitName = "jpaNrmPU")
  private EntityManager nrmEntityManager;

  @PersistenceContext(unitName = "jpaGnmPU")
  private EntityManager gnmEntityManager;

  @Produces
//  @RequestScoped
  @Nrm
  public EntityManager createNrmEntityManager() {
    log.info("nrmEntityManager injected.....");
    return this.nrmEntityManager;
  }

  @Produces
//  @RequestScoped
  @Gnm
  public EntityManager createGnmEntityManager() {
    log.info("gnmEntityManager injected.....");
    return this.gnmEntityManager;
  }

  public void closeNrmEntityManager(@Disposes @Nrm EntityManager nrmEntityManager) {
    log.info("disposesing nrmEntity....");
    if (nrmEntityManager.isOpen()) {
      log.info("closing....");
      nrmEntityManager.close();
    }
  }

  public void closeGnmEntityManager(@Disposes @Gnm EntityManager gnmEntityManager) {
    log.info("disposesing gnmEntity....");
    if (gnmEntityManager.isOpen()) {
      log.info("closing...");
      gnmEntityManager.close();
    }
  }

  /**
   * Close entityManagers when bean is destroyed
   */
//  @PreDestroy
//  public void preDestroy() {
//    log.info("preDestroy - entityManager is closed");
//    if (nrmEntityManager.isOpen()) {
//      log.info("preDestroy closing...");
//      nrmEntityManager.close();
//    }
//
//    if (gnmEntityManager.isOpen()) {
//      log.info("preDestroy closing...");
//      gnmEntityManager.close();
//    }
//  }
}
