//package kz.kcell.apps.spmot.resources.repository;
//
//import kz.kcell.apps.spmot.domain.spmot.entity.StringResources;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
///**
// * @author Pavel.Terechshenkov@kcell.kz
// * @since 14 09 2015
// */
//@Repository
//public class StringResourcesRepository {
//    @PersistenceContext//(unitName = "spmot_db")
//    private EntityManager em;
//
//    public List<StringResources> getList() {
//        return em.createQuery("select p from StringResources p").getResultList();
//    }
//
//}
